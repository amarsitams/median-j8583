package com.rumango.median.iso.test;

public class Time30Days {
	public static void main(String[] args) {
		long current = System.currentTimeMillis();
//		long after30 = 30 * 24 * 60 * 60 * 1000;
		long days30 = 2592000000L;

		System.err.println(current - days30);
	}
}
