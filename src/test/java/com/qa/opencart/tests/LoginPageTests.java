package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open Cart Application with Shopping Workflow")
@Story("US 101: Design login page for Open Cart Application")
@Listeners({ExtentReportListener.class, TestAllureListener.class}) //to generate report when run from here
public class LoginPageTests extends BaseTest {
	
	@Description("checking login page title....")
	@Severity(SeverityLevel.MINOR)
	@Owner("Vamshi")
	@Feature("login page title feature")
	@Issue("Login-123")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Description("checking login page url....")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vamshi")
	@Feature("login page url feature")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}
	
	@Description("checking forgot pwd link exist on the login page....")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Vamshi")
	@Feature("login page forgot pwd link")
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotLinkExist(), AppError.ELEMENT_NOT_FOUND);
	}
	
	@Description("checking user is able to login successfully....")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Vamshi")
	@Feature("login feature")
	@Test(priority = 4)
	public void loginTest() {
		//accPage = loginPage.doLogin(prop.getProperty("username"), System.getProperty("password"));//used to pass from the mvn
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccoutsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	//act vs exp : Assert.assertEquals(act, ext)
	//Assert.assertTrue(10>5)
	//Assert.assertTrue(title.contains("Google123"))
	//Assert.assertFalse(false)
	//Assert.assertFalse(5>10) --- for negative scenarios
	
}
