package com.gestor.common.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ReflectionUtils {
	
	public static <T> T parse(Class<T> type,String value) throws Exception{
		if(type.isPrimitive() || Number.class.isAssignableFrom(type)){
			return type.getConstructor(String.class).newInstance(value);
		}
		return type.cast(value);
	}
	
	public static Field findAnnotationFields(Class<?> claz,Class<? extends Annotation> annotation){
		for (Field field : claz.getFields()) {
			if(field.getAnnotation(annotation) != null){
				return field;
			}
		}
		return null;
	}
}