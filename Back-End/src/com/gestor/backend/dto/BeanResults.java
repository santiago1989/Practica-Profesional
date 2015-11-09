package com.gestor.backend.dto;

import java.util.List;

public class BeanResults {

	private List<?> results;

	private BaseCriteria criteria;
	
	public BeanResults(List<?> results, BaseCriteria criteria) {
		super();
		this.results = results;
		this.criteria = criteria;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public BaseCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(BaseCriteria criteria) {
		this.criteria = criteria;
	}
}
