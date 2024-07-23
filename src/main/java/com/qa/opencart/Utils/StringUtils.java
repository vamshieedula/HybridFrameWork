package com.qa.opencart.Utils;

public class StringUtils {
	
	public static String getRandomEmailId() {
		String emailId = "userauto"+ System.currentTimeMillis()+"@opencart.com";
		System.out.println("user email ID is: "+ emailId);
		return emailId;
	}

}
