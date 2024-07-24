package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Utils.ExcelUtil;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
				// here on the webpage product name and header is no same so can be maintained 1
				// object for both
				{ "macbook", "MacBook Pro" }, { "imac", "iMac" }, { "samsung", "Samsung SyncMaster 941BW" },
				{ "samsung", "Samsung Galaxy Tab 10.1" }, { "canon", "Canon EOS 5D" } };
	}

//	@Test(dataProvider = "getProductData")
//	public void productHeaderTest(String serchKey, String productName) {
//		searchResultsPage = accPage.doSearch(serchKey);
//		productInfoPage = searchResultsPage.selectProduct(productName);
//		Assert.assertEquals(productInfoPage.getProductHeader(), productName, AppError.HEADER_NOT_FOUND);
//	}

	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
				// here on the webpage product name and header is no same so can be maintained 1
				// object for both
				{ "macbook", "MacBook Pro", 4 }, { "imac", "iMac", 3 }, { "samsung", "Samsung SyncMaster 941BW", 1 },
				{ "samsung", "Samsung Galaxy Tab 10.1", 7 }, { "canon", "Canon EOS 5D", 3 } };
	}
	
//	@Test(dataProvider = "getProductImageData")
//	public void productImageCountTestForRaw(String serchKey, String productName, int imageCount) {
//		searchResultsPage = accPage.doSearch(serchKey);
//		productInfoPage = searchResultsPage.selectProduct(productName);
//		productInfoPage.getProductImagesCount();
//		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount, AppError.IMAGES_COUNT_MISMATCH);
//
//	}
	
	@DataProvider
	public Object[][] getProductImageSheetData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_IMAGES_SHEET_NAME);
	}

	@Test(dataProvider = "getProductImageSheetData")
	public void productImageCountTest(String serchKey, String productName, String imageCount) {
		searchResultsPage = accPage.doSearch(serchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		productInfoPage.getProductImagesCount();
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Integer.parseInt(imageCount), AppError.IMAGES_COUNT_MISMATCH);

	}

	//test with multiple assertions
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfoMap();
		System.out.println("=====product information=====");
		System.out.println(productInfoMap);

		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00");

		softAssert.assertAll();//gives failure info

	}

	// hard assert(Assert) vs soft assert(verify - soft Assert)
	// Assert ---> methods (static)
	// SoftAssert ---> methods (non static)

}
