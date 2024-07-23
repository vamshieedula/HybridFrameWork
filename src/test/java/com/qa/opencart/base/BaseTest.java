package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.Accountspage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected Accountspage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage regPage;

	
	protected SoftAssert softAssert;
	
	@Step("setup for the test, initializing browser: {0}")
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) {//@optinal is used to run the test cases from individual test classes
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
//		prop.getProperty("browser"); //same can be done by call by reference
//		prop.getProperty("url");
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}
	
	@Step("closing browser...")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
