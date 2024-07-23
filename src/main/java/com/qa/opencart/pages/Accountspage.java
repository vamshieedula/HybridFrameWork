package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

public class Accountspage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public Accountspage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public String getAccoutsPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Accounts page url : "+title);
		return title;
	}
	
	public String getAccountsPageURL() {
		String url = eleUtil.waitForUrlContains(AppConstants.ACC_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("Accounts page url : "+url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	
	public List<String> getAccountPageHeaders() {
		
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElementsLocated(headers, TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> headerValList = new ArrayList<String>();
		for(WebElement e : headersList) {
			String text = e.getText();
			headerValList.add(text);
		}	
		return headerValList;
	}
	
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(search);
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("searching : "+search);
		
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		else {
			System.out.println("search field is not present on the page");
			return null;
		}
	}
	
	
}
