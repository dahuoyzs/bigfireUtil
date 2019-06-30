package com.bigfire.imgdown.controller;

import com.bigfire.imgdown.util.StrUtil;

public class Test {
	public static void main(String[] args) {
//		testURLMub();
//		String src = "ad1owdhsaion1aaaaad1asd1a";
//		String countStr = "aa";
//		System.out.println(count(src, countStr));
	}
	public static int count(String src,String countStr) {
		int count = 0;
		while(src.contains(countStr)) {
			int index = src.indexOf(countStr);
			src = src.substring(index+countStr.length(), src.length());
			count++;
		}
		return count;
	}
	public static void testURLMub() {
		String url = "h?ttp://?";
		System.out.println(url.indexOf("?"));
		System.out.println(url.lastIndexOf("?"));
//		String mainUrl = url.substring(0,index);
//		System.out.println(mainUrl);
		
	}
	public static void testURLSub() {
		String url = "https://www.easyicon.net/iconsearch/icon/?s=addtime_DESC";
		System.out.println(url.substring(0,url.indexOf("?")));
		
	}
}
