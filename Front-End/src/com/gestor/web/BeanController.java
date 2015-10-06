package com.gestor.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestor.backend.dto.BaseCriteria;
import com.gestor.backend.service.Service;
import com.gestor.backend.service.impl.ServiceImpl;
import com.gestor.backend.util.CriteriaUtils;
import com.gestor.common.interfaces.Identificable;
import com.gestor.common.util.Utils;
import com.gestor.web.dto.Popup;
import com.gestor.web.enums.PopupType;
import com.gestor.web.mapper.RequestMapper;
import com.gestor.web.utils.Constants;

@Controller
public class BeanController {

	private static final String ENTITY_NAME_REQUEST_PARAM = "entityName";
	
	private static Map<String,String> navigationMap = new HashMap<String,String>();

	private static final String TICKET_SEARCH = "/ticket/ticketsSearch";
	
	private static final String LOGIN_VIEW = "login";
	
	private static final String HOME_VIEW = "home";
	
	private static final String ERROR_VIEW = "error";
	
	private String SEARCH_BEAN_REQUEST = "searchBean";
	
	private Service service = new ServiceImpl();
	
	static{
//		ACA SE AGREGAN LAS REGLAS DE NAVEGACION DEL SITIO
		navigationMap.put("Incidencia",TICKET_SEARCH);
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request){
		String user = request.getParameter(Constants.USER_SESSION);
		String password = request.getParameter("password");
		if(!Utils.isNullOrEmpty(user) && !Utils.isNullOrEmpty(password)){
			request.getSession(true).setAttribute(Constants.USER_SESSION,user);
			return new ModelAndView(HOME_VIEW);
		}
		else{
			request.setAttribute("errorMessage","Ingrese usuario / contraseña");
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
		showPopup(request,"Prueba de popup",PopupType.INFORMATION);
		return new ModelAndView(HOME_VIEW);
	}
	
	@RequestMapping("/saveEntity")
	public ModelAndView saveEntity(HttpServletRequest request){
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		try {
			Class<?> claz = Class.forName(clazName);
			Identificable entity = new RequestMapper(claz).build(request);
			service.guardar(entity);
			String viewPath = navigationMap.get(clazName);
			return new ModelAndView(viewPath);
		} catch (ClassNotFoundException e) {
			return new ModelAndView(ERROR_VIEW);
		}
	}

	@RequestMapping("/getResults")
	public ModelAndView getResults(HttpServletRequest request,BaseCriteria baseCriteria){
		Map<String,Object> model = new HashMap<String,Object>();
		String viewPath = navigationMap.get(baseCriteria.getClazName());
		try {
			List<?> collection = service.buscar(Class.forName(baseCriteria.getClazName()), baseCriteria.getFiltros());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView(viewPath,model);
	}
	
	@RequestMapping("/searchEntity")
	public ModelAndView searchEntity(HttpServletRequest request){
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		try {
			request.setAttribute(SEARCH_BEAN_REQUEST,CriteriaUtils.getCriteriaBean(Class.forName(clazName)));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO agreggar loguer
			e.printStackTrace();
		}
		String viewPath = navigationMap.get(clazName);
		return new ModelAndView(viewPath);		
	}
	
	private void showPopup(HttpServletRequest request,String text,PopupType type){
		Popup popup = new Popup(text, type);
		request.setAttribute(Constants.POPUP_REQUEST, popup);
	}
}
