package com.gestor.common.collections.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.Predicate;

public class CollectionUtils {
	
	public static <T> T find(List<T> list,Predicate<T> predicate){
		return org.apache.commons.collections4.CollectionUtils.find(list, predicate);
	}
	
	public static <T> Collection<T> predicatedCollection(Collection<T> list,Predicate<T> predicate) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return org.apache.commons.collections4.CollectionUtils.predicatedCollection(list, predicate);
	}	

	public static <T> Set<T> predicatedCollection(Set<T> list,Predicate<T> predicate) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return new HashSet<T>(org.apache.commons.collections4.CollectionUtils.predicatedCollection(list, predicate));
	}	

}
