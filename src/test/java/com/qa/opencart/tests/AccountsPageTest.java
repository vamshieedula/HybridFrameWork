package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}

	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccoutsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccountsPageURL().contains(AppConstants.ACC_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accPageHeadersList = accPage.getAccountPageHeaders();
		Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST, AppError.LIST_IS_NOT_MATCHED);	
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac",1},
			{"samsung",2},
			{"Airtel",0}
		};
	}
	
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey, int resultCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchResultsCount(), resultCount, AppError.SEARCH_RESUTLS_COUNT_MISMATCH);
	}
	
	
}
