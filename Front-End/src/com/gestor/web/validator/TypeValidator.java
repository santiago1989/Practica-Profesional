package com.gestor.web.validator;

import java.lang.reflect.Field;

import com.gestor.common.annotations.RequestMapped;

public class TypeValidator {
	
	public static boolean validate(Field field, String value){
		String regExp = field.getAnnotation(RequestMapped.class).regexp();
		return regExp.matches(regExp);
	}
}
