package com.web.automation.testdata;

import java.util.Random;

public class RandomString {

	public static String getAlphaString(int length) {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYX";
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (alpha.length() * Math.random());

			// add Character one by one in end of sb
			stringBuilder.append(alpha.charAt(index));
		}
		// System.out.println("AlphaString :"+stringBuilder.toString());
		return stringBuilder.toString();

	}

	public static String getNumbericString(int length) {
		String alpha = "0123456789";
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (alpha.length() * Math.random());

			// add Character one by one in end of sb
			stringBuilder.append(alpha.charAt(index));
		}
		// System.out.println("NumericString :"+stringBuilder.toString());
		return stringBuilder.toString();

	}

	public static String getAlphaNumbericString(int length) {

		// choose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}
		// System.out.println("AlphaNumericString :"+sb.toString());
		return sb.toString();
	}

	public static int generatRandomNumberInRange(int minNumber , int maxNumber) {
		Random r = new Random();
        int randomNumber = r.nextInt((maxNumber - minNumber) + 1) + minNumber;

        System.out.println("Random number: " + randomNumber);
		return randomNumber;
	}
}
