package com.gestor.backend.mapper;

import java.sql.ResultSet;

public interface Mapper<T> {
	
	T popupate(ResultSet rs);	
}
