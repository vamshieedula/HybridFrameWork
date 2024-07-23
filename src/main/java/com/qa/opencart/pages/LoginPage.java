package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.Page Objects: By locators

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2.public constructor of the page
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.public page actions
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("login page url : "+title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("login page url : "+url);
		return url;
	}
	
	@Step("getting the stste of forgot pwd link exist...")
	public boolean checkForgotLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("login to application with username: {0} and password: {1}")
	public Accountspage doLogin(String userName, String pwd) {
		eleUtil.doSendKeysWithWait(emailId, userName, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
//		driver.findElement(emailId).sendKeys(userName);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		
//		String title = driver.getTitle();
//		System.out.println(title);
//		return title;
		
		return new Accountspage(driver);
	}
	
	@Step("navigating to register page...")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClickWithWait(registerLink, TimeUtil.DEFAULT_TIME);
		return new RegisterPage(driver);
	}
	
	
	
}
