package com.gestor.backend.predicate;

import org.apache.commons.collections4.Predicate;

import com.gestor.common.interfaces.Identificable;

public class AltaPredicate implements Predicate<Identificable> {

	@Override
	public boolean evaluate(Identificable arg0) {
		return arg0.getEstadoAlta();
	}
}
