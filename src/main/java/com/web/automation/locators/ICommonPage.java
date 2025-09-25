package com.web.automation.locators;

public interface ICommonPage {

	public static final String CAL_MONTH_YEAR_HEADING = "//kendo-calendar-header/span[1]";
	public static final String CAL_PREVIOUS_NAVIGATION_BTN = "//kendo-calendar-header/span[3]/button[1]/span";
	public static final String CAL_NEXT_NAVIGATION_BTN = "//kendo-calendar-header/span[3]/button[2]/span";
	public static final String CAL_YEAR_MONTH_DATE_OPTION = "//kendo-calendar-horizontal/table/tbody/tr/td/span";

	public static final String UPLOAD_BTN = "//div[contains(@style,\"upload\")]/ancestor::button";
	public static final String FILE_NAME_INPUTBOX = "//input[@type=\"file\"]";
	public static final String BROWSE_FILE_BTN = "//div[@class=\"dropzone\"]//label[contains(@class,\"browse\")]";
	public static final String UPLOAD_POPUP_CLOSE_BTN = "//a[@class=\"close_btn\"]";
	public static final String FILE_NAME_LIST_ON_UPLOAD_HISTORY_TAB = "//div[contains(@class,\"name-elipsis\")]//span";
	public static final String STATUS_LIST_ON_UPLOAD_HISTORY_TAB = "//div[contains(@class,\"name-elipsis\")]//span";

	// toast message
    public static final String TOAST_MESSASGE = "//div[@id=\"toast-container\"]";
	public static final String FILE_UPLOAD_LOADER="//bdo_gbl_upload_file";
	
	//pagination
	public static final String PAGINATION_NEXT_BTN="//span[@aria-label=\"Go to the next page\"]";
	public static final String PAGINATION_PREVIOUS_BTN="//span[@aria-label=\"Go to the previous page\"]";
	public static final String NUMBER_OF_PAGES="//span[contains(@aria-label,\"Page\")]";
	
}
