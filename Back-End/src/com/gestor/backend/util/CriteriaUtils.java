package com.gestor.backend.util;

import java.util.HashMap;
import java.util.Map;

import com.gestor.backend.dto.Criteria;
import com.gestor.backend.dto.CriteriaIncidencia;
import com.gestor.backend.dto.CriteriaUsuario;
import com.gestor.entidades.Incidencia;
import com.gestor.entidades.TipoIncidencia;
import com.gestor.web.seguridad.Usuario;

public class CriteriaUtils {
	
	private static Map<Class<?>,Class<? extends Criteria<?>>> map = new HashMap<Class<?>,Class<? extends Criteria<?>>>();
	
	static{
		map.put(Usuario.class,CriteriaUsuario.class);
		map.put(Incidencia.class,CriteriaIncidencia.class);
		map.put(TipoIncidencia.class,CriteriaUsuario.class);
	}
	
	public static Criteria<?> getCriteria(Object obj) throws InstantiationException, IllegalAccessException{
		Class<? extends Criteria<?>> claz = map.get(obj.getClass());
		Criteria criteria = claz.newInstance();
		criteria.build(obj);
		return criteria;
	}
	
	public static Criteria getCriteriaBean(Class claz) throws InstantiationException, IllegalAccessException{
		return map.get(claz).newInstance();
	}
	
}
