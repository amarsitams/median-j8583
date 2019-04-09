/**
 * 
 */
package com.rumango.median.soap.utils;

/**
 * @author lei2o
 *
 */
public class AddStringAtParticularIndex {
	public String insertString(String originalString, String stringToBeInserted, int index) {
		String newString = new String();
		for (int i = 0; i < originalString.length(); i++) {
			newString += originalString.charAt(i);
			if (i == index) {
				newString += stringToBeInserted;
			}
		}
		return newString;
	}
}
