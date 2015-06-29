package com.gestor.web.mapper;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.gestor.common.annotations.RequestMapped;
import com.gestor.common.reflect.ReflectionUtils;
import com.gestor.web.Utils;
import com.gestor.web.dto.MapperResult;
import com.gestor.web.validator.TypeValidator;

public class RequestMapper<T> {
	
	private Class<T> claz;
	
	public RequestMapper(Class<T> claz){
		this.claz = claz;
	}
	
	public T build(HttpServletRequest request){
		MapperResult<T> result = new MapperResult<T>();
		T object = null;
		boolean entityValid = true;
		try {
			object = claz.newInstance();
			Field[] fieldList = object.getClass().getDeclaredFields();
			for (Field field : fieldList) {
				RequestMapped annotation = field.getAnnotation(RequestMapped.class);
				if(annotation!=null){
					String value = request.getParameter(field.getName());
					boolean required = annotation.required();
					if(Utils.isNullOrEmpty(value)){
						if(required){
							result.addError(String.format("Ingresar campo %s.",field.getName()));							
						}
					}else{
						boolean valid = TypeValidator.validate(field,value);
						entityValid&=valid;
						if(valid){
							Object valueParsed = ReflectionUtils.parse(field.getType(),value);
							BeanUtils.setProperty(object,field.getName(),valueParsed);
						}else{
							result.addError(String.format("El campo %s es no valido.",field.getName()));
						}
					}
				}
			}
			if(entityValid){
				result.setEntity(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
}
