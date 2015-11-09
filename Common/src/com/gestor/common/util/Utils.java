package com.gestor.common.util;

import org.apache.commons.lang.StringUtils;

public class Utils {

	private static final String NUMERIC_EXPRESSION = "[0-9]+";
	
	public static boolean isNullOrEmpty(String str){
		return (str ==null) || (str.isEmpty());
	}
	public static boolean isNullOrNoDigit(String str){
		return isNullOrEmpty(str) || !str.matches(NUMERIC_EXPRESSION);
	}
	public static String removeExpression(String source,char[] chars){
		for (char ch : chars) {
			source = StringUtils.remove(source,ch);
		}
		return source;
	}
	
}
