package com.rumango.median.iso.test;

public class TestCsv {
	public static void main(String[] args) {
//		String[] ary = { "a", "b", "c" };
//		System.out.println(ary);

		String css = "a,b,c";

		String[] stringAry = css.split(",");
		for (String string : stringAry) {
			System.out.println(string);
		}
	}
}
