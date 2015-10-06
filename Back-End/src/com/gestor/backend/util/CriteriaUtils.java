package com.gestor.backend.util;

import java.util.HashMap;
import java.util.Map;

import com.gestor.backend.dto.BaseCriteria;
import com.gestor.backend.dto.CriteriaIncidencia;
import com.gestor.backend.dto.CriteriaUsuario;
import com.gestor.entidades.Incidencia;
import com.gestor.entidades.TipoIncidencia;
import com.gestor.web.seguridad.Usuario;

public class CriteriaUtils {
	
	private static Map<Class<?>,Class<? extends BaseCriteria>> map = new HashMap<Class<?>,Class<? extends BaseCriteria>>();
	
	static{
		map.put(Usuario.class,CriteriaUsuario.class);
		map.put(Incidencia.class,CriteriaIncidencia.class);
		map.put(TipoIncidencia.class,CriteriaUsuario.class);
	}
	
	public static BaseCriteria getCriteria(Object obj) throws InstantiationException, IllegalAccessException{
		Class<? extends BaseCriteria> claz = map.get(obj.getClass());
		BaseCriteria criteria = claz.newInstance();
		return criteria;
	}
	
	public static BaseCriteria getCriteriaBean(Class<?> claz) throws InstantiationException, IllegalAccessException{
		return map.get(claz).newInstance();
	}
	
}
