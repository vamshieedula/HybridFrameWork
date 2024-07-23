package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResult = By.cssSelector("div.product-thumb");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getSearchResultsCount() {
		List<WebElement> resultsList = eleUtil.waitForVisibilityOfElementsLocated(searchResult, TimeUtil.DEFAULT_MEDIUM_TIME);
		int resultCount = resultsList.size();
		System.out.println("product serarch result count ==="+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.doClickWithWait(By.linkText(productName), TimeUtil.DEFAULT_TIME);
		//bylocator is not maintained at class level on the it is created
		//if not we should maintain no.of private By locators
		return new ProductInfoPage(driver);
	}

}
