package com.gestor.web.mapper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.gestor.backend.service.Service;
import com.gestor.backend.service.impl.ServiceImpl;
import com.gestor.common.annotations.RequestMapped;
import com.gestor.common.interfaces.Identificable;
import com.gestor.common.reflect.ReflectionUtils;
import com.gestor.common.util.Utils;
import com.gestor.web.dto.MapperResult;
import com.gestor.web.validator.TypeValidator;

public class RequestMapper {
	
	private Service service;
	
	private Class claz;
	
	public RequestMapper(Class claz){
		this.claz = claz;
		this.service = new ServiceImpl();
	}
	
	public MapperResult build(HttpServletRequest request){
		MapperResult result = new MapperResult();
		Identificable object = null;
		boolean entityValid = true;
		try {
			object = (Identificable) claz.newInstance();
			Field[] fieldList = object.getClass().getDeclaredFields();
			for (Field field : fieldList) {
				RequestMapped annotation = field.getAnnotation(RequestMapped.class);
				if(annotation!=null){
					entityValid = processField(request, result, object,
							entityValid, field, annotation);
				}
			}
			if(entityValid){
				result.setEntity(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean processField(HttpServletRequest request,
			MapperResult result, Identificable object, boolean entityValid,
			Field field, RequestMapped annotation)
			throws IllegalAccessException, InvocationTargetException, Exception {
		boolean required = annotation.required();
		if(Collection.class.isAssignableFrom(field.getType())){
			String[] values = request.getParameterValues(field.getName());
			processValues(result, required, object, field, annotation, values);
		}else{			
			String value = request.getParameter(field.getName());
			if(Utils.isNullOrEmpty(value)){
				if(required){
					result.addError(String.format("Ingresar campo %s.",field.getName()));							
				}
			}else{
				entityValid = processValidValue(result, object, entityValid, field,
						annotation, value);
			}
		}
		return entityValid;
	}

	private boolean processValidValue(MapperResult result,
			Identificable object, boolean entityValid, Field field,
			RequestMapped annotation, String value)
			throws IllegalAccessException, InvocationTargetException, Exception {
		boolean valid = TypeValidator.validate(field,value);
		entityValid&=valid;
		if(valid){
			if(annotation.customType()){
				processCustomType(object, field, annotation, value);
			}else{
				Object valueParsed = ReflectionUtils.parse(field.getType(),value);
				BeanUtils.setProperty(object,field.getName(),valueParsed);
			}
		}else{
			result.addError(String.format("El campo %s es no valido.",field.getName()));
		}
		return entityValid;
	}
	
	private boolean processValues(MapperResult result,boolean required,Identificable object,
			Field field,RequestMapped annotation,String[] values) throws IllegalAccessException, InvocationTargetException{
		boolean valid = TypeValidator.validate(field, values);
		if(values == null && required){
			result.addError(String.format("Ingrese el campo %",field.getName()));
		}else if(values != null){
			if(valid){
				processCustomTypeCollection(object, field, annotation, values);
			}else{
				result.addError(String.format("El campo %s es no valido.",field.getName()));
			}
		}
		return valid;
	}

	private void processCustomType(Identificable object, Field field,
			RequestMapped annotation, String value)
			throws Exception {
		Object objectPersisted = service.get(field.getType(),(Serializable)ReflectionUtils.parse(annotation.idClass(), value));
		BeanUtils.setProperty(object,field.getName(),objectPersisted);
	}
	
	private void processCustomTypeCollection(Identificable object, Field field,
			RequestMapped annotation, String[] value) throws IllegalAccessException, InvocationTargetException{
		Set<?> collection = new HashSet<>(service.findIds(annotation.customTypeClass(),annotation.idName(),value));
		BeanUtils.setProperty(object,field.getName(),collection);
	}
	
}
