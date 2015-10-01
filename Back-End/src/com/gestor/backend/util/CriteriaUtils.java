package com.gestor.backend.util;

import java.util.HashMap;
import java.util.Map;

import com.gestor.backend.dto.Criteria;

public class CriteriaUtils {
	
	private static Map<Class<?>,Class<? extends Criteria<?>>> map = new HashMap<Class<?>,Class<? extends Criteria<?>>>();
	
	public static Criteria<?> getCriteria(Object obj) throws InstantiationException, IllegalAccessException{
		Class<? extends Criteria<?>> claz = map.get(obj.getClass());
		Criteria criteria = claz.newInstance();
		criteria.build(obj);
		return criteria;
	}
	
}
