package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImgCount = By.cssSelector("div#content a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private Map<String, String> productMap;
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.doGetText(productHeader);
		System.out.println("product header=== " + header);
		return header;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElementsLocated(productImgCount, TimeUtil.DEFAULT_MEDIUM_TIME)
				.size();
		System.out.println("total images == " + imagesCount);
		return imagesCount;
	}
	
	public Map<String, String> getProductInfoMap() {
		//productMap = new HashMap<String, String>();//no insertion order
		//productMap = new LinkedHashMap<String, String>();//with same insertion order
		productMap = new TreeMap<String, String>();//with alphabetical(sorted key) insertion order

		productMap.put("productname", getProductHeader());
		productMap.put("productiamgescount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaList) {
			String metaData =e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
	
//	$2,000.00
//	Ex Tax: $2,000.0
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String productPrice = priceList.get(0).getText();
		String exTacprice =priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productPrice);
		productMap.put("exTaxPrice", exTacprice);
		
	}

}
