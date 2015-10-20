package com.gestor.web.mapper;

import java.lang.reflect.Field;
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
							if(annotation.customType()){
								if(Collection.class.isAssignableFrom(field.getType())){
									Set<?> collection = new HashSet<>(service.findIds(annotation.customTypeClass(),"code",value));
									BeanUtils.setProperty(object,field.getName(),collection);
								}else{
									Object objectPersisted = service.get(field.getType(), value);
									BeanUtils.setProperty(object,field.getName(),objectPersisted);
								}
							}else{
								Object valueParsed = ReflectionUtils.parse(field.getType(),value);
								BeanUtils.setProperty(object,field.getName(),valueParsed);
							}
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
		return result;
	}
	
}
