package com.gestor.common.util;

import java.util.regex.Pattern;

public class FileUtils {

	private static final Pattern patternName = Pattern.compile("(\\w+\\.)+\\w+$");

	private static final Pattern patternExtension = Pattern.compile("[0-9a-z]+$");

	public static String getFileName(String url){
		return patternName.matcher(url).group(0);
	}

	public static String getFileExtension(String url){
		return patternExtension.matcher(url).group(0);
	}
}
