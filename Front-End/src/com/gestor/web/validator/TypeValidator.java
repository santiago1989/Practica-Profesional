package com.gestor.web.validator;

import java.lang.reflect.Field;

import com.gestor.common.annotations.RequestMapped;

public class TypeValidator {
	
	public static boolean validate(Field field, String value){
		String regExp = field.getAnnotation(RequestMapped.class).regexp();
		return value.matches(regExp);
	}

	public static boolean validate(Field field, String[] values){
		String regExp = field.getAnnotation(RequestMapped.class).regexp();
		boolean valid = true;
		if(values != null){
			for (String value : values) {
				valid&= value.matches(regExp);
			}
		}
		return valid;
	}
	
}
