package com.gestor.common.util;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class Utils {

	private static final String NUMERIC_EXPRESSION = "[0-9]+";

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
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
	
	public static Date formatDate(String dateStr) throws ParseException{
		return sdf.parse(dateStr);
	}	
	
	public static String normalize(String cadena){
		return isNullOrEmpty(cadena)? StringUtils.EMPTY: Normalizer.normalize(cadena.toLowerCase(), Normalizer.Form.NFC).replace('ó','o').replace('é','e')
				.replace('í','i').replace('á','a').replace('ú','u').replace("ñ","ni");
	}
}
