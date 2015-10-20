package com.gestor.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestor.backend.dto.BaseCriteria;
import com.gestor.backend.dto.CriteriaIncidencia;
import com.gestor.backend.dto.CriteriaTipoIncidencia;
import com.gestor.backend.dto.CriteriaUsuario;
import com.gestor.backend.service.Service;
import com.gestor.backend.service.impl.ServiceImpl;
import com.gestor.backend.util.CriteriaUtils;
import com.gestor.common.enums.RolType;
import com.gestor.entidades.EstadoIncidencia;
import com.gestor.entidades.Incidencia;
import com.gestor.entidades.PrioridadIncidencia;
import com.gestor.entidades.TipoIncidencia;
import com.gestor.web.dto.CollectionsBean;
import com.gestor.web.dto.IncidenciaCollectionsBean;
import com.gestor.web.dto.MapperResult;
import com.gestor.web.dto.Popup;
import com.gestor.web.dto.UsuarioCollectionsBean;
import com.gestor.web.enums.PopupType;
import com.gestor.web.mapper.RequestMapper;
import com.gestor.web.seguridad.Rol;
import com.gestor.web.seguridad.Usuario;
import com.gestor.web.utils.Constants;

@Controller
public class BeanController {

	private static final String COLLECTION = "collection";
	
	private static final String ENTITY_NAME_REQUEST_PARAM = "entityName";
	
	private static Map<String,String> searchNavigationMap = new HashMap<String,String>();

	private static Map<String,String> loadNavigationMap = new HashMap<String,String>();	

	private static Map<String,CollectionsBean> collectionsBeanMap = new HashMap<String, CollectionsBean>();
	
	private static Map<String,String> popupTextMap = new HashMap<String,String>();
	
	private static final String TICKET_SEARCH = "/ticket/ticketsSearch";

	private static final String USER_SEARCH = "/users/usersSearch";

	private static final String USER_DATA_LOAD = "/users/users";
	
	private static final String TICKET_DATA_LOAD = "/ticket/ticket";
	
	private static final String LOGIN_VIEW = "login";
	
	private static final String HOME_VIEW = "home";
	
	private static final String ERROR_VIEW = "error";
	
	
	private String SEARCH_BEAN_REQUEST = "searchBean";
	
	private String COLLECTIONS_BEAN_REQUEST = "collectionsBean";
	
	private static final String POPUP_TYPE = "popuptype";

	private static Service service = new ServiceImpl();
	
	private static CriteriaUsuario criteriaResponsable = new CriteriaUsuario(RolType.RESPONSABLE.getCode());
	
	static{
//		ACA SE AGREGAN LAS REGLAS DE NAVEGACION DEL SITIO
		searchNavigationMap.put(Incidencia.class.getSimpleName(),TICKET_SEARCH);
		searchNavigationMap.put(Usuario.class.getName(),USER_SEARCH);
		
		loadNavigationMap.put(Usuario.class.getSimpleName(),USER_DATA_LOAD);
		loadNavigationMap.put(Incidencia.class.getSimpleName(),TICKET_DATA_LOAD);

		
		collectionsBeanMap.put(Usuario.class.getSimpleName(),new UsuarioCollectionsBean(service.findAll(Rol.class)));
//		TODO agregar el filro en la busqueda de responsables.
		collectionsBeanMap.put(Incidencia.class.getSimpleName(),new IncidenciaCollectionsBean(service.buscar(Usuario.class,criteriaResponsable.getFiltros()),service.findAll(TipoIncidencia.class),service.findAll(EstadoIncidencia.class),service.findAll(PrioridadIncidencia.class)));
		popupTextMap.put(Usuario.class.getSimpleName(),"Se dio de alta correctamente el usuario, con legajo: ");
		popupTextMap.put(Incidencia.class.getSimpleName(),"Se dio de alta correctamente la incidencia, con número: ");
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request){
		Integer legajo = Integer.parseInt(request.getParameter(Constants.USER_SESSION));
		String PASSWORD = "password";
		String password = request.getParameter(PASSWORD);
		Usuario usuario = (Usuario)service.get(Usuario.class,legajo);
		if(usuario != null && usuario.getContrasena().equals(password)){
			request.getSession(true).setAttribute(Constants.USER_SESSION,usuario);
			return new ModelAndView(HOME_VIEW);	
		}else{
			showPopup(request,"Usuario/Contraseña incorrectos",PopupType.ERROR);
			return new ModelAndView(LOGIN_VIEW);
		}
	}

	@RequestMapping("/homePage")
	public ModelAndView homePage(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.USER_SESSION)== null){
			return new ModelAndView(LOGIN_VIEW);
		}else {
			return new ModelAndView(HOME_VIEW);
		}
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.USER_SESSION);
		return new ModelAndView(LOGIN_VIEW);
	}
	
	@RequestMapping("/showPopup")
	public ModelAndView showPopup(HttpServletRequest request){
		String desc = request.getParameter(POPUP_TYPE);
		PopupType popupType = PopupType.lookUp(desc);
		showPopup(request,"Prueba de popup",popupType == null? PopupType.INFORMATION:popupType);
		return new ModelAndView(HOME_VIEW);
	}
	
	@RequestMapping("/saveEntity")
	public ModelAndView saveEntity(HttpServletRequest request){
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		try {
			Class<?> claz = Class.forName(clazName);
			MapperResult result = new RequestMapper(claz).build(request);
			if(result.getErrorMessages().isEmpty()){
				service.guardar(result.getEntity());
				String viewPath = searchNavigationMap.get(clazName);
				showPopup(request,popupTextMap.get(claz.getSimpleName()).concat(String.valueOf(result.getEntity().getId())),PopupType.INFORMATION);
				return new ModelAndView(viewPath);
			}
			else{
				String viewPath = loadNavigationMap.get(claz.getSimpleName());
				showPopup(request,Arrays.toString(result.getErrorMessages().toArray()),PopupType.ERROR);
				request.setAttribute(COLLECTIONS_BEAN_REQUEST,collectionsBeanMap.get(clazName));
				return new ModelAndView(viewPath);
			}
		} catch (ClassNotFoundException e) {
			return new ModelAndView(ERROR_VIEW);
		}
	}

	@RequestMapping("/getUsersResults")
	public ModelAndView getUsersResults(HttpServletRequest request,CriteriaUsuario searchBean){
		return getResults(request,searchBean, Usuario.class);
	}
	
	@RequestMapping("/getTicketsResults")
	public ModelAndView getTicketsResults(HttpServletRequest request,CriteriaIncidencia searchBean){
		return getResults(request,searchBean, Incidencia.class);
	}

	@RequestMapping("/getTicketsTypeResults")
	public ModelAndView getTicketTypeResults(HttpServletRequest request,CriteriaTipoIncidencia searchBean){
		return getResults(request,searchBean, TipoIncidencia.class);
	}
	
	private ModelAndView getResults(HttpServletRequest request,BaseCriteria criteria,Class<?> claz){
		Map<String,Object> model = new HashMap<String,Object>();
		String viewPath = searchNavigationMap.get(claz.getName());
		List collection = (List) service.buscar(claz, criteria.getFiltros());
		model.put(COLLECTION,collection);
		model.put(SEARCH_BEAN_REQUEST,criteria);
		model.put(COLLECTIONS_BEAN_REQUEST,collectionsBeanMap.get(claz.getSimpleName()));
		if(collection.isEmpty()){
			showPopup(request, "No se han encontrado resultados, para la busqued realizada.",PopupType.INFORMATION);
		}
		return new ModelAndView(viewPath,model);		
	}
	
	@RequestMapping("/searchEntity")
	public ModelAndView searchEntity(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		try {
			Class<?> claz = Class.forName(clazName);
			model.put(SEARCH_BEAN_REQUEST,CriteriaUtils.getCriteriaBean(claz));
			model.put(COLLECTIONS_BEAN_REQUEST,collectionsBeanMap.get(claz.getSimpleName()));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO agreggar loguer
			e.printStackTrace();
		}
		String viewPath = searchNavigationMap.get(clazName);
		return new ModelAndView(viewPath,model);
	}

	@RequestMapping("/newEntity")
	public ModelAndView newEntity(HttpServletRequest request){
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		String viewPath = loadNavigationMap.get(clazName);
		request.setAttribute(COLLECTIONS_BEAN_REQUEST,collectionsBeanMap.get(clazName));
		return new ModelAndView(viewPath);
	}

	
	private void showPopup(HttpServletRequest request,String text,PopupType type){
		Popup popup = new Popup(text, type);
		request.setAttribute(Constants.POPUP_REQUEST, popup);
	}
}
