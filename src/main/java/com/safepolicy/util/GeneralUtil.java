package com.safepolicy.util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;


public class GeneralUtil {

	
	public static String getNumberingFormat(long id) {

		String numberData = "#";
		/*
		 * String numberData1 = "0"; 
		 */
		String numberData2 = alpha();
		numberData = numberData+id+numberData2;
		
	
		return numberData;
	}
	
	// for randow aplhabet we use this function in quoteid creation
	private static String alpha() {

		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 5;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;
	}
	
	//for application id and booking id
	
	 	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    private static final String DIGITS = "0123456789";
	    private static final SecureRandom RANDOM = new SecureRandom();

	    private static String generateRandomString(String characterSet, int length) {
	        StringBuilder result = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            result.append(characterSet.charAt(RANDOM.nextInt(characterSet.length())));
	        }
	        return result.toString();
	    }

	    public static int generateBookingId() {
	        String bookingIdString = generateRandomString(DIGITS, 6); // Booking ID as string of digits
	        return Integer.parseInt(bookingIdString); // Convert string to integer
	    }

	    public static String generateApplicationNo() {
	        return generateRandomString(CHARACTERS, 10); // Application number with letters and digits
	    }
	    
	  
	   //for maturity date
	  

	    public static Date generateMaturityDate(Date bookingDate, int policyTerm) {
	        long bookingTimeMillis = bookingDate.getTime();
	  
	        long planTermMillis = policyTerm * 365 * 24 * 60 * 60 * 1000;
	        
	        return new Date(bookingTimeMillis + planTermMillis);
	    }

	    
	    
}
