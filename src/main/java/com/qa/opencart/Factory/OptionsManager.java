package com.qa.opencart.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====Running tests on headless mode====");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====Running tests on incognito window====");
			co.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			//co.setCapability("enableVNC", true);

		}
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====Running tests on headless mode====");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====Running tests on incognito window====");
			fo.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			//fo.setCapability("enableVNC", true);
		}
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====Running tests on headless mode====");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====Running tests in private window====");
			eo.addArguments("--inPrivate");
		}
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
			//eo.setCapability("enableVNC", true);
		}
		return eo;
	}

}
