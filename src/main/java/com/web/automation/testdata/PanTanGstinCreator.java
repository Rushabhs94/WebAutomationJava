package com.web.automation.testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.web.automation.constants.ApplicationConstants;
import com.web.automation.logging.InitiateLogger;

public class PanTanGstinCreator {

	static List<String> keyList;
	static int randomIndex;

	public static String getStateCode() {
		String stateCode = null;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Jammu and Kashmir", "01");
		map.put("Himachal Pradesh", "02");
		map.put("Punjab", "03");
		map.put("Chandigarh", "04");
		map.put("Uttarakhand", "05");
		map.put("Haryana", "06");
		map.put("Delhi", "07");
		map.put("Rajasthan", "08");
		map.put("Uttar Pradesh", "09");
		map.put("Bihar", "10");
		map.put("Sikkim", "11");
		map.put("Arunachal Pradesh", "12");
		map.put("Nagaland", "13");
		map.put("Manipur", "14");
		map.put("Mizoram", "15");
		map.put("Tripura", "16");
		map.put("Meghalaya", "17");
		map.put("Assam", "18");
		map.put("West Bengal", "19");
		map.put("Jharkhand", "20");
		map.put("Odisha", "21");
		map.put("Chhattisgarh", "22");
		map.put("Madhya Pradesh", "23");
		map.put("Gujarat", "24");
		map.put("Daman and Diu Prior to 26th January 2020", "25");
		map.put("Dadra and Nagar Haveli and Daman and Diu (Newly Merged Unit)", "26");
		map.put("Maharashtra", "27");
		map.put("Andhra Pradesh (Before Division)", "28");
		map.put("Karnataka", "29");
		map.put("Goa", "30");
		map.put("Lakshadweep", "31");
		map.put("Kerala", "32");
		map.put("Tamil Nadu", "33");
		map.put("Puducherry", "34");
		map.put("Andaman and Nicobar islands", "35");
		map.put("Telangana", "36");
		map.put("Andhra Pradesh (Newly Added)", "37");
		map.put("Ladakh (Newly Added)", "38");

		List<String> valuesList = new ArrayList<String>(map.values());
		keyList = new ArrayList<String>(map.keySet());
		randomIndex = new Random().nextInt(valuesList.size());
		stateCode = valuesList.get(randomIndex);
		System.out.println("State code is generatrated for " + keyList.get(randomIndex));
		return stateCode;

	}

	public static String getPanType(String panType) {
		String panTypeLetter = null;
		switch (panType) {
		case "Trust":
			panTypeLetter = "T";
			break;
		case "Firm":
			panTypeLetter = "F";
			break;
		case "HUF":
			panTypeLetter = "H";
			break;
		case "Individual":
			panTypeLetter = "P";
			break;
		case "Company":
			panTypeLetter = "C";
			break;

		default:
			InitiateLogger.fatal("Wrong Pan Type Mentioned..");
			break;
		}
		return panTypeLetter;

	}

	/*
	 * public static HashMap<String, String> generatePanTanGstinValue(String
	 * panType) { HashMap<String, String> map = new HashMap<String, String>();
	 * 
	 * // generate Pan value String randomPan = RandomString.getAlphaString(5) +
	 * RandomString.getNumbericString(4) + RandomString.getAlphaString(1); char
	 * pantypeChar = getPanType(panType).charAt(0); String pan =
	 * randomPan.replace(randomPan.charAt(3), pantypeChar);
	 * System.out.println("Pan : " + pan); map.put("Pan", pan);
	 * 
	 * // Genrate Tan Value String tan = pan.substring(0, 4) +
	 * RandomString.getNumbericString(1) + pan.substring(5, 10);
	 * System.out.println("Tan : " + tan); map.put("Tan", tan);
	 * 
	 * // Genrate Gstin value String gstin = getStateCode() + pan + "1Z" +
	 * RandomString.getNumbericString(1); System.out.println("GSTIN : " + gstin);
	 * map.put("Gstin", gstin); map.put("State", keyList.get(randomIndex));
	 * 
	 * System.out.println(map);
	 * 
	 * return map; }
	 */

	public static HashMap<String, String> generatePanTanGstinValue(String panType) {
		HashMap<String, String> map = new HashMap<String, String>();
		/*
		 * String testvalue = "AYTFR1237G"; String pan = "AY" +
		 * RandomString.getAlphaString(1) + "FR1237G"; map.put("Pan", pan); String tan =
		 * pan.substring(0, 4) + RandomString.getNumbericString(1) + pan.substring(5,
		 * 10); map.put("Tan", tan);
		 */
		String gstinTest = "24AYTFR1237G2Z";
		gstinTest = gstinTest.replace(gstinTest.charAt(6), (RandomString.getAlphaString(1).charAt(0)));
		gstinTest = gstinTest.replace(gstinTest.charAt(4), (RandomString.getAlphaString(1).charAt(0)));
		String gstin = checkGstn(gstinTest);

		System.out.println(gstinTest);
		// System.out.println(sb);
		System.out.println(gstin);

		map.put("Gstin", gstin);

		String pan = gstin.substring(2, 12);
		map.put("Pan", pan);
		String tan = pan.substring(0, 4) + RandomString.getNumbericString(1) + pan.substring(5, 10);
		map.put("Tan", tan);

		map.put("State", "Gujarat");
		map.put("City", "Ahmedabad");
		System.out.println(map);
		return map;

	}

	public static String checkGstn(String gst) {
		int factor = 2;
		int sum = 0;
		int checkCodePoint = 0;
		int i, j, digit, mod, codePoint;
		String cpChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String inputChars = gst.trim().toUpperCase();
		mod = cpChars.length();

		for (i = inputChars.length() - 1; i >= 0; i--) {
			codePoint = cpChars.indexOf(inputChars.charAt(i));
			digit = factor * codePoint;
			factor = (factor == 2) ? 1 : 2;
			digit = (digit / mod) + (digit % mod);
			sum += Math.floor(digit);
		}
		checkCodePoint = ((mod - (sum % mod)) % mod);
		System.out.println("gst" + gst);
		return gst + cpChars.charAt(checkCodePoint);
	}

	public static void main(String[] args) {

		generatePanTanGstinValue(ApplicationConstants.PAN_TYPE_FIRM);
	}

}
