package com.gestor.backend.dto;

import java.text.ParseException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.gestor.common.util.Utils;

public abstract class BaseCriteria{
	
	private String clazName;
	
	public abstract Class<?> getClaz();
	
	public abstract Criteria getCriteria();
	
	protected void addLike(String property,String value,Criteria criteria){
		if(value != null && !value.isEmpty()){
			criteria.add(Restrictions.like(property, value.concat("%")));
		}
	}

	protected void addEqualInteger(String property,Integer value,Criteria criteria){
		if(value != null && !value.equals(0)){
			criteria.add(Restrictions.eq(property, value));
		}
	}
	
	protected void addEqual(String property,Object value,Criteria criteria){
		if(value != null){
			criteria.add(Restrictions.eq(property, value));
		}
	}
	
	protected void addEqual(String property,String value,Criteria criteria){
		if(!Utils.isNullOrEmpty(value)){
			criteria.add(Restrictions.eq(property, value));
		}
	}
	
	protected void addGreaterThanDate(String property,String value,Criteria criteria) throws ParseException{
		if(!Utils.isNullOrEmpty(value)){
			criteria.add(Restrictions.gt(property, Utils.formatDate(value)));
		}
	}
	
	protected void addIn(String property,String ids,Criteria criteria) {
		if(!Utils.isNullOrEmpty(ids)){
			criteria.add(Restrictions.in(property,ids.split(",")));
		}
	}
	protected void addAliasFilter(Criteria criteria,String property,String alias,String propertyName,Object[] value){
		if(value != null){
			criteria.createAlias(property, alias).add(Restrictions.in(alias.concat(".").concat(propertyName), value));
		}
	}
		
	public String getClazName() {
		return clazName;
	}

	public void setClazName(String clazName) {
		this.clazName = clazName;
	}
}
