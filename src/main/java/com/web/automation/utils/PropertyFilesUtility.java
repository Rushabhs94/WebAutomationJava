package com.web.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.web.automation.constants.PathConstants;

public class PropertyFilesUtility {

	private static Properties readDataFromPropertyFile(String filePath) {

		Properties properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(new File(filePath));
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static Properties readEnvProperties() {
		String envFilePath = PathConstants.ENV_PROPERTIES_PATH;
		return readDataFromPropertyFile(envFilePath);

	}

	public static Properties readConfigProperties() {
		String configFilePath = PathConstants.CONFIG_PROPERTIES_PATH.replace("*env*",
				readEnvProperties().getProperty("env"));
		return readDataFromPropertyFile(configFilePath);

	}
	
	public static Properties readCommonDataProperties() {
		String commonDataFilePath = PathConstants.COMMON_DATA_PROPERTIES_FILE;
		return readDataFromPropertyFile(commonDataFilePath);

	}

	public static void updateDataInPropertyFile(String filePath, String property, String value) {

		FileOutputStream out;
		FileInputStream in;
		File file = new File(filePath);
		try {
			in = new FileInputStream(file);
			Properties props = new Properties();
			props.load(in);
			//in.close();
			props.setProperty(property, value);
			out = new FileOutputStream(file);
			props.store(out, null);
			out.close();
			System.out.println("Values updated properly");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		updateDataInPropertyFile(PathConstants.COMMON_DATA_PROPERTIES_FILE, "company.name", "Auto company");
		updateDataInPropertyFile(PathConstants.COMMON_DATA_PROPERTIES_FILE, "company.pan", "123456789");
		
	}

}
