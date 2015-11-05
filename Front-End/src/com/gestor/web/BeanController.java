package com.gestor.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import com.gestor.common.interfaces.Identificable;
import com.gestor.common.util.Utils;
import com.gestor.entidades.Incidencia;
import com.gestor.entidades.Nota;
import com.gestor.entidades.TipoIncidencia;
import com.gestor.web.dto.CollectionsBean;
import com.gestor.web.dto.IncidenciaCollectionsBean;
import com.gestor.web.dto.MapperResult;
import com.gestor.web.dto.Popup;
import com.gestor.web.dto.UsuarioCollectionsBean;
import com.gestor.web.enums.PopupType;
import com.gestor.web.mapper.RequestMapper;
import com.gestor.web.seguridad.Usuario;
import com.gestor.web.utils.Constants;

@Controller
public class BeanController {

	private static Service service = new ServiceImpl();
	
	private static Map<String,String> searchNavigationMap = new HashMap<String,String>();
	
	private static Map<String,String> loadNavigationMap = new HashMap<String,String>();	
	
	private static Map<String,CollectionsBean> collectionsBeanMap = new HashMap<String, CollectionsBean>();
	
	private static Map<String,String> popupTextMap = new HashMap<String,String>();

	private static 	final String UPDATE_FLAG = "update";
	
	private static 	final String READ_FLAG = "read";
	
	private static final String READ_BEAN = "bean";
	
	private static final String COLLECTION = "collection";
	
	private static final String ENTITY_NAME_REQUEST_PARAM = "entityName";
		
	private static final String TICKET_SEARCH = "/ticket/ticketsSearch";

	private static final String USER_SEARCH = "/users/usersSearch";

	private static final String USER_DATA_LOAD = "/users/users";
	
	private static final String TICKET_DATA_LOAD = "/ticket/ticket";
	
	private static final String LOGIN_VIEW = "login";
	
	private static final String HOME_VIEW = "home";
	
	private static final String ERROR_VIEW = "error";
	
	private static final String ENTITY_ID = "entityId";
	
	private static final String SEARCH_BEAN_REQUEST = "searchBean";
	
	private static final String COLLECTIONS_BEAN_REQUEST = "collectionsBean";
	
	private static final String POPUP_TYPE = "popuptype";
	
	static{
//		ACA SE AGREGAN LAS REGLAS DE NAVEGACION DEL SITIO
		searchNavigationMap.put(Incidencia.class.getName(),TICKET_SEARCH);
		searchNavigationMap.put(Usuario.class.getName(),USER_SEARCH);
		
		loadNavigationMap.put(Usuario.class.getSimpleName(),USER_DATA_LOAD);
		loadNavigationMap.put(Incidencia.class.getSimpleName(),TICKET_DATA_LOAD);

		
		collectionsBeanMap.put(Usuario.class.getSimpleName(),new UsuarioCollectionsBean(service));
//		TODO agregar el filro en la busqueda de responsables.
		collectionsBeanMap.put(Incidencia.class.getSimpleName(),new IncidenciaCollectionsBean(service));
		popupTextMap.put(Usuario.class.getSimpleName(),"Se dio de alta correctamente el usuario, con legajo: ");
		popupTextMap.put(Incidencia.class.getSimpleName(),"Se dio de alta correctamente la incidencia, con número: ");
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request){
		String legajoStr = request.getParameter(Constants.USER_SESSION);
		Integer legajo = Integer.parseInt(!Utils.isNullOrNoDigit(legajoStr)? legajoStr:"0");
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
		Map<String,Object> model = new HashMap<String, Object>();
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
				model.put(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(clazName));
				model.put(READ_BEAN,result.getEntity());
				return new ModelAndView(viewPath,model);
			}
		} catch (ClassNotFoundException e) {
			return new ModelAndView(ERROR_VIEW);
		}
	}

	private CollectionsBean getCollectionsBean(String clazName) {
		final CollectionsBean collectionBean = collectionsBeanMap.get(clazName);
		collectionBean.refreshCollections();
		return collectionBean;
	}
	
	@RequestMapping("/updateEntity")
	public ModelAndView updateEntity(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String, Object>();
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		String viewPath = null;
		try {
			Class<?> claz = Class.forName(clazName);
			MapperResult result = new RequestMapper(claz).build(request);
			if(result.getErrorMessages().isEmpty()){
				Identificable entity = result.getEntity();
				Identificable oldEntity = (Identificable) service.get(claz,entity.getId());
				oldEntity.copyFrom(entity);
				service.actualizar(oldEntity);
				showPopup(request,"Se actualizo correctamente",PopupType.INFORMATION);
				viewPath = searchNavigationMap.get(clazName);
				return new ModelAndView(viewPath, model);
			}else{
				viewPath = loadNavigationMap.get(claz.getSimpleName());
				showPopup(request,Arrays.toString(result.getErrorMessages().toArray()),PopupType.ERROR);
				model.put(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(clazName));
				return new ModelAndView(viewPath, model);				
			}
		} catch (ClassNotFoundException e) {
			return new ModelAndView(ERROR_VIEW);
		}
	}
	
	
	private ModelAndView removeEntity(HttpServletRequest request,Class<?> claz){
		Map<String,Object> model = new HashMap<String, Object>();
		String viewPath = searchNavigationMap.get(claz.getName());
		Integer id = Integer.parseInt(request.getParameter(ENTITY_ID));
		try {
			Identificable object = (Identificable)service.get(claz, id);
			service.eliminar(object);
			model.put(SEARCH_BEAN_REQUEST,CriteriaUtils.getCriteriaBean(claz));
			model.put(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(claz.getSimpleName()));
			showPopup(request,"Eliminación realizada correctamente",PopupType.INFORMATION);
			return new ModelAndView(viewPath);
		} catch (InstantiationException | IllegalAccessException e) {
			return new ModelAndView(ERROR_VIEW);
		}
	}
	
	@RequestMapping("/removeUser")
	public ModelAndView removeUsuario(HttpServletRequest request){
		return removeEntity(request,Usuario.class);
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
		List collection = (List) service.buscar(claz, criteria.getCriteria());
		model.put(COLLECTION,collection);
		model.put(SEARCH_BEAN_REQUEST,criteria);
		model.put(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(claz.getSimpleName()));
		if(collection.isEmpty()){
			showPopup(request, "No se han encontrado resultados, para la busqueda realizada.",PopupType.INFORMATION);
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
			model.put(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(claz.getSimpleName()));
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
		request.setAttribute(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(clazName));
		return new ModelAndView(viewPath);
	}

	
	@RequestMapping("/readOrUpdateUsuario")
	public ModelAndView readOrUpdateUsuario(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		Integer id = Integer.parseInt(request.getParameter(ENTITY_ID));
		return update(request, model, id,Usuario.class);
	}

	private ModelAndView update(HttpServletRequest request,Map<String, Object> model, Serializable id,Class<?> claz) {
		Boolean update = Boolean.parseBoolean(request.getParameter(UPDATE_FLAG)  == null? "false":request.getParameter(UPDATE_FLAG));
		Boolean read = Boolean.parseBoolean(request.getParameter(READ_FLAG) == null? "false":request.getParameter(READ_FLAG));
		Identificable bean = (Identificable) service.get(claz,id);
		model.put(READ_BEAN,bean);
		model.put(UPDATE_FLAG,update);
		model.put(READ_FLAG,read);
		request.setAttribute(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(claz.getSimpleName()));
		String view = loadNavigationMap.get(claz.getSimpleName());
		return new ModelAndView(view, model);
	}
	
	@RequestMapping("/readOrUpdateTicket")
	public ModelAndView readOrUpdateTicket(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		Integer id = Integer.parseInt(request.getParameter(ENTITY_ID));
		return update(request, model, id, Incidencia.class);
	}
	
	@RequestMapping("/readOrUpdateTicketType")
	public ModelAndView readOrUpdateTicketType(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		Integer id = Integer.parseInt(request.getParameter(ENTITY_ID));
		return update(request, model, id, TipoIncidencia.class);
	}
	
	private void showPopup(HttpServletRequest request,String text,PopupType type){
		Popup popup = new Popup(text, type);
		request.setAttribute(Constants.POPUP_REQUEST, popup);
	}
	
	@RequestMapping("/comentar")
	public ModelAndView comentar(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		Integer numeroIncidencia = Integer.parseInt(request.getParameter("incidencia"));
		Integer numeroUsuario = Integer.parseInt(request.getParameter("usuario"));
		String comentario = request.getParameter("comentario");
		Incidencia incidencia = service.get(Incidencia.class,numeroIncidencia);
		Usuario usuario = service.get(Usuario.class,numeroUsuario);
		Nota nota = new Nota(comentario, usuario);
		incidencia.addNota(nota);
		service.guardar(nota);
		model.put(READ_BEAN,incidencia);
		model.put(UPDATE_FLAG,Boolean.TRUE);
		model.put(READ_FLAG,Boolean.FALSE);
		request.setAttribute(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(Incidencia.class.getSimpleName()));
		return new ModelAndView(TICKET_DATA_LOAD,model);
	}
	
	@RequestMapping("/eliminarComentario")
	public ModelAndView eliminarComentario(HttpServletRequest request){
		Integer numeroNota = Integer.parseInt(request.getParameter("nota"));
		Nota nota = service.get(Nota.class,numeroNota);
		service.eliminar(nota);
		Incidencia incidencia = nota.getIncidencia();
		incidencia.removeNota(nota);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put(READ_BEAN,incidencia);
		model.put(UPDATE_FLAG,Boolean.TRUE);
		model.put(READ_FLAG,Boolean.FALSE);
		request.setAttribute(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(Incidencia.class.getSimpleName()));
		return new ModelAndView(TICKET_DATA_LOAD,model);
	}
	
	@RequestMapping("adjuntar")
	public ModelAndView adjuntar(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute(COLLECTIONS_BEAN_REQUEST,getCollectionsBean(Incidencia.class.getSimpleName()));
		return new ModelAndView(TICKET_DATA_LOAD,model);
	}
}
