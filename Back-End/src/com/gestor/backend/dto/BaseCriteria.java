package com.gestor.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public abstract class BaseCriteria{
	
	private String clazName;
	
	protected List<Criterion> criteriosList = new ArrayList<Criterion>();

	public abstract Class<?> getClaz();
	
	public abstract List<Criterion> getFiltros();
	
	protected void addLike(String property,String value){
		if(value != null && !value.isEmpty()){
			criteriosList.add(Restrictions.like(property, value.concat("%")));
		}
	}

	protected void addEqualInteger(String property,Integer value){
		if(value != null && !value.equals(0)){
			criteriosList.add(Restrictions.eq(property, value));
		}
	}
	
	protected void addEqual(String property,Object value){
		if(value != null){
			criteriosList.add(Restrictions.eq(property, value));
		}
	}
	
	protected void addIn(String property,String ids) {
		if(ids != null){
			criteriosList.add(Restrictions.in(property,ids.split(",")));
		}
	}
	
	public String getClazName() {
		return clazName;
	}

	public void setClazName(String clazName) {
		this.clazName = clazName;
	}
}
