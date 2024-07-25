package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// thread local: 1. each thread will get individual copy of driver (no dead
	// lock/no wait for other threads)
	// 2.better thread management for large parallel run
	// 3.I can get TL copy of the driver anytime whenever its required
	// 4. will get correct reports
	// 5. set, get

	/**
	 * This is used to init the driver on the basis of given browser name.
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		// cross browser logic
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is :" + browserName);

		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("please pass correct browser " + browserName);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * get the local thread copy of the driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initialize the .properties file
	 * 
	 * @return this return properties (prop)
	 */
	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"

		String envName = System.getProperty("env");
		System.out.println("running test suite on env ---> " + envName);
		if (envName == null) {
			System.out.println("env name is not given, hene running it on QA environment...");
			try {
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
					break;
				case "prod":
					ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
					break;
				default:
					System.out.println("please pass the right env name.." + envName);
					throw new FrameworkException("===WRONG ENV PASSED===");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/*
	 * takes screenshots
	 */

	public static String getScreenshot(String methodName) {
//		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
//		File destination = new File(path);
//		try {
//			FileHandler.copy(scrFile, destination);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return path;

		// Get the driver instance
		TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();

		// Take the screenshot and save it to a temporary location
		File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

		// Define the path for the screenshots folder
		String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots";

		// Create the screenshots folder if it doesn't exist
		File screenshotsDir = new File(screenshotsDirPath);
		if (!screenshotsDir.exists()) {
			if (screenshotsDir.mkdirs()) {
				System.out.println("Folder 'screenshots' created successfully at: " + screenshotsDirPath);
			} else {
				System.out.println("Failed to create the folder 'screenshots' at: " + screenshotsDirPath);
			}
		}

		// Define the destination path for the screenshot
		String screenshotPath = screenshotsDirPath + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(screenshotPath);

		// Copy the screenshot to the destination path
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination.getAbsolutePath();
	}

}
