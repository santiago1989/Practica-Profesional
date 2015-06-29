package com.gestor.common.collections.predicate;

import org.apache.commons.collections4.Predicate;

import com.gestor.common.interfaces.Identificable;

public class IdentificablePredicate<T extends Identificable> implements Predicate<T> {

	private Identificable input;
	
	public IdentificablePredicate(Identificable input) {
		this.input = input;
	}
	
	@Override
	public boolean evaluate(Identificable object) {
		return input.getId().equals(object.getId());
	}

}
