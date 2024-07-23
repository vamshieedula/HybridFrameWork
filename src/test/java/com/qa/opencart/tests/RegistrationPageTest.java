package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Utils.CSVUtil;
import com.qa.opencart.Utils.ExcelUtil;
import com.qa.opencart.Utils.StringUtils;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] userRegTestData() {
		return new Object[][] {
			{"shivam", "automation", "987654321", "arti@123", "yes"},
			{"ranga", "automation", "987654321", "praful@123", "no"},
			{"vamshi", "selenium", "987654321", "vamshi@123", "yes"},
		};
	}
	
	@DataProvider
	public Object[][] userRegTestDataFromSheet() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] userRegTestDataFromCSV() {
		return CSVUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "userRegTestDataFromCSV")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue
			(regPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone, password, subscribe), 
					AppError.USER_REG_NOT_SUCCESSFUL);
	}
	
	
	

}
