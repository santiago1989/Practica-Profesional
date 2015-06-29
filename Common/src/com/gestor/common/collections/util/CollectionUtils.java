package com.gestor.common.collections.util;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.Predicate;

public class CollectionUtils {
	
	public static <T> T find(List<T> list,Predicate<T> predicate){
		return org.apache.commons.collections4.CollectionUtils.find(list, predicate);
	}
	
	public static <T> Collection<T> predicatedCollection(List<T> list,Predicate<T> predicate){
		return org.apache.commons.collections4.CollectionUtils.predicatedCollection(list, predicate);
	}	
}
