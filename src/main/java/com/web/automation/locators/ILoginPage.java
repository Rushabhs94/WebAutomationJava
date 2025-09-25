package com.web.automation.locators;

public interface ILoginPage {
	public static final String USERNAME ="email";
	public static final String PASSWORD ="password";
	public static final String SIGNIN ="next";
	
	public static final String PROFILE_ICON ="//div[@class=\"right-icon-sub mobile-view-none\"]//span[@class=\"profile-user-icons\"]";
	public static final String LOGOUT ="//span[contains(text(),\"Logout\")]";

}
