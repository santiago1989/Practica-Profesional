package com.gestor.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestor.backend.dto.BaseCriteria;
import com.gestor.backend.dto.BeanResults;
import com.gestor.backend.dto.CriteriaIncidencia;
import com.gestor.backend.dto.CriteriaTipoIncidencia;
import com.gestor.backend.dto.CriteriaUsuario;
import com.gestor.backend.dto.ReportResult;
import com.gestor.backend.service.MailService;
import com.gestor.backend.service.ReportService;
import com.gestor.backend.service.Service;
import com.gestor.backend.service.impl.MailServiceImpl;
import com.gestor.backend.service.impl.ReportServiceImpl;
import com.gestor.backend.service.impl.ServiceImpl;
import com.gestor.backend.util.CriteriaUtils;
import com.gestor.common.enums.ContentType;
import com.gestor.common.enums.RolType;
import com.gestor.common.interfaces.Identificable;
import com.gestor.common.util.Utils;
import com.gestor.entidades.Adjunto;
import com.gestor.entidades.Incidencia;
import com.gestor.entidades.Nota;
import com.gestor.entidades.TipoIncidencia;
import com.gestor.web.dto.CollectionsBean;
import com.gestor.web.dto.IncidenciaCollectionsBean;
import com.gestor.web.dto.MapperResult;
import com.gestor.web.dto.Popup;
import com.gestor.web.dto.TipoIncidenciaCollectionsBean;
import com.gestor.web.dto.UsuarioCollectionsBean;
import com.gestor.web.enums.PopupType;
import com.gestor.web.io.utils.ServletIOUtils;
import com.gestor.web.mapper.RequestMapper;
import com.gestor.web.seguridad.Usuario;
import com.gestor.web.utils.Constants;

@Controller
public class BeanController {

	private static Service service = new ServiceImpl();
	
	private static MailService mailService = new MailServiceImpl();
	
	private static ReportService reportService = new ReportServiceImpl();
	
	private static Map<Class<?>,String> searchNavigationMap = new HashMap<Class<?>,String>();
	
	private static Map<Class<?>,String> loadNavigationMap = new HashMap<Class<?>,String>();	
	
	private static Map<Class<?>,CollectionsBean> collectionsBeanMap = new HashMap<Class<?>, CollectionsBean>();
	
	private static Map<Class<?>,String> popupTextMap = new HashMap<Class<?>,String>();

	private static 	final String UPDATE_FLAG = "update";
	
	private static 	final String READ_FLAG = "read";
	
	private static final String READ_BEAN = "bean";
	
	private static final String ENTITY_NAME_REQUEST_PARAM = "entityName";
		
	private static final String TICKET_SEARCH = "/ticket/ticketsSearch";

	private static final String USER_SEARCH = "/users/usersSearch";

	private static final String USER_DATA_LOAD = "/users/users";
	
	private static final String TICKET_DATA_LOAD = "/ticket/ticket";
	
	private static final String TIPO_TICKET_DATA_LOAD = "/tipoIncidencia/tipoIncidencia";
	
	private static final String TIPO_TICKET_SEARCH = "/tipoIncidencia/tipoIncidenciaSearch";

	private static final String LOGIN_VIEW = "login";
	
	private static final String HOME_VIEW = "home";
	
	private static final String ERROR_VIEW = "error";
	
	private static final String ENTITY_ID = "entityId";
	
	private static final String SEARCH_BEAN_REQUEST = "searchBean";
		
	private static final String POPUP_TYPE = "popuptype";
	
	private static final String VIEW_FROM = "viewfrom";

	private static final String COLLECTION_PREFIX = "collection";

	private static final String SEARCH_RESULTS_PREFIX = "results";
	
	private static final String COLLECTION_USUARIO = COLLECTION_PREFIX.concat(Usuario.class.getSimpleName());
	
	private static final String COLLECTION_INCIDENCIA = COLLECTION_PREFIX.concat(Incidencia.class.getSimpleName());

	private static final String COLLECTION_TIPO_INCIDENCIA = COLLECTION_PREFIX.concat(TipoIncidencia.class.getSimpleName());
	
	static{
//		ACA SE AGREGAN LAS REGLAS DE NAVEGACION DEL SITIO
		searchNavigationMap.put(Incidencia.class,TICKET_SEARCH);
		searchNavigationMap.put(Usuario.class,USER_SEARCH);
		searchNavigationMap.put(TipoIncidencia.class,TIPO_TICKET_SEARCH);
		
		loadNavigationMap.put(Usuario.class,USER_DATA_LOAD);
		loadNavigationMap.put(Incidencia.class,TICKET_DATA_LOAD);
		loadNavigationMap.put(TipoIncidencia.class,TIPO_TICKET_DATA_LOAD);

		
		collectionsBeanMap.put(Usuario.class,new UsuarioCollectionsBean(service));
		collectionsBeanMap.put(Incidencia.class,new IncidenciaCollectionsBean(service));
		collectionsBeanMap.put(TipoIncidencia.class,new TipoIncidenciaCollectionsBean(service));

		popupTextMap.put(Usuario.class,"Se dio de alta correctamente el usuario, con legajo: ");
		popupTextMap.put(Incidencia.class,"Se dio de alta correctamente la incidencia, con número: ");
		popupTextMap.put(TipoIncidencia.class,"Se dio de alta correctamente el tipoIncidencia con númmero");
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
			request.getSession().setAttribute("administrativo",RolType.ADMINISTRATIVO.getCode());
			request.getSession().setAttribute("superusuario",RolType.SUPERUSUARIO.getCode());
			request.getSession().setAttribute("responsable",RolType.RESPONSABLE.getCode());
			request.getSession().setAttribute(COLLECTION_USUARIO,getCollectionsBean(Usuario.class));
			request.getSession().setAttribute(COLLECTION_INCIDENCIA,getCollectionsBean(Incidencia.class));
			request.getSession().setAttribute(COLLECTION_TIPO_INCIDENCIA,getCollectionsBean(TipoIncidencia.class));
			request.setAttribute(VIEW_FROM,HOME_VIEW);
			return new ModelAndView(HOME_VIEW);
		}else{
			showPopup(request,"Usuario/Contraseña incorrectos",PopupType.ERROR);
			return new ModelAndView(LOGIN_VIEW);
		}
	}

	@RequestMapping("/homePage")
	public ModelAndView homePage(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.USER_SESSION)== null){
			request.setAttribute(VIEW_FROM,HOME_VIEW);
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
		String viewPath = null;
		try {
			Class<?> claz = Class.forName(clazName);
			MapperResult result = new RequestMapper(claz).build(request);
			if(result.getErrorMessages().isEmpty()){
				service.guardar(result.getEntity());
				viewPath = searchNavigationMap.get(claz);
				mailService.sendMail(result.getEntity().getMailMessage());
				showPopup(request,popupTextMap.get(claz).concat(String.valueOf(result.getEntity().getId())),PopupType.INFORMATION);
			}
			else{
				viewPath = loadNavigationMap.get(claz);
				showPopup(request,Arrays.toString(result.getErrorMessages().toArray()),PopupType.ERROR);
				request.getSession().setAttribute(COLLECTION_PREFIX.concat(claz.getSimpleName()),getCollectionsBean(claz));
				model.put(READ_BEAN,result.getEntity());
			}
		} catch (ClassNotFoundException e) {
			return new ModelAndView(ERROR_VIEW);
		} 
		catch(MessagingException e){
			e.printStackTrace();
		}
		request.setAttribute(VIEW_FROM,viewPath);
		return new ModelAndView(viewPath,model);
	}

	private CollectionsBean getCollectionsBean(Class<?> claz) {
		final CollectionsBean collectionBean = collectionsBeanMap.get(claz);
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
				viewPath = searchNavigationMap.get(claz);
			}else{
				viewPath = loadNavigationMap.get(claz);
				showPopup(request,Arrays.toString(result.getErrorMessages().toArray()),PopupType.ERROR);
				request.getSession().setAttribute(COLLECTION_PREFIX.concat(claz.getSimpleName()),getCollectionsBean(claz));
			}
		} catch (ClassNotFoundException e) {
			return new ModelAndView(ERROR_VIEW);
		}
		request.setAttribute(VIEW_FROM,viewPath);
		return new ModelAndView(viewPath, model);
	}
	
	
	private ModelAndView removeEntity(HttpServletRequest request,Class<?> claz){
		Map<String,Object> model = new HashMap<String, Object>();
		String viewPath = searchNavigationMap.get(claz);
		Integer id = Integer.parseInt(request.getParameter(ENTITY_ID));
		try {
			Identificable object = (Identificable)service.get(claz, id);
			service.eliminar(object);
			model.put(SEARCH_BEAN_REQUEST,CriteriaUtils.getCriteriaBean(claz));
			request.getSession().setAttribute(COLLECTION_PREFIX.concat(claz.getSimpleName()),getCollectionsBean(claz));
			showPopup(request,"Eliminación realizada correctamente",PopupType.INFORMATION);
			request.setAttribute(VIEW_FROM,viewPath);
			return new ModelAndView(viewPath);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
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
		String viewPath = searchNavigationMap.get(claz);
		List<?> collection = (List<?>) service.buscar(claz, criteria.getCriteria());
		request.getSession().setAttribute(claz.getName().toLowerCase(),collection);
		request.getSession().setAttribute(SEARCH_RESULTS_PREFIX.concat(claz.getSimpleName()),new BeanResults(collection, criteria));
		request.getSession().setAttribute(COLLECTION_PREFIX.concat(claz.getSimpleName()),getCollectionsBean(claz));
		if(collection.isEmpty()){
			showPopup(request, "No se han encontrado resultados, para la busqueda realizada.",PopupType.INFORMATION);
		}
		request.setAttribute(VIEW_FROM,viewPath);
		return new ModelAndView(viewPath,model);		
	}
	
	@RequestMapping("/searchEntity")
	public ModelAndView searchEntity(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		try {
			Class<?> claz = Class.forName(clazName);
			model.put(SEARCH_BEAN_REQUEST,CriteriaUtils.getCriteriaBean(claz));
			request.getSession().setAttribute(COLLECTION_PREFIX.concat(claz.getSimpleName()),getCollectionsBean(claz));
			String viewPath = searchNavigationMap.get(claz);
			request.setAttribute(VIEW_FROM,viewPath);
			return new ModelAndView(viewPath,model);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
			return new ModelAndView(ERROR_VIEW);
		}
	}

	@RequestMapping("/newEntity")
	public ModelAndView newEntity(HttpServletRequest request){
		String clazName = request.getParameter(ENTITY_NAME_REQUEST_PARAM);
		try {
			Class<?> claz = Class.forName(clazName);
			String viewPath = loadNavigationMap.get(claz);
			request.getSession().setAttribute(COLLECTION_PREFIX.concat(claz.getSimpleName()),getCollectionsBean(claz));
			request.setAttribute(VIEW_FROM,viewPath);
			return new ModelAndView(viewPath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new ModelAndView(ERROR_VIEW);
		}
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
		request.getSession().setAttribute(COLLECTION_PREFIX.concat(claz.getSimpleName()),getCollectionsBean(claz));
		String view = loadNavigationMap.get(claz);
		request.setAttribute(VIEW_FROM,view);
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
		request.setAttribute(VIEW_FROM,TICKET_DATA_LOAD);
		return new ModelAndView(TICKET_DATA_LOAD,model);
	}
	
	@RequestMapping("/eliminarComentario")
	public ModelAndView eliminarComentario(HttpServletRequest request){
		Integer numeroNota = Integer.parseInt(request.getParameter("nota"));
		Nota nota = service.get(Nota.class,numeroNota);
		service.eliminar(nota);
		Incidencia incidencia = nota.getIncidencia();
		incidencia.removeNota(nota);
		return returnToUpdateTicket(incidencia, request);	
	}
	
	@RequestMapping("/adjuntar")
	public ModelAndView adjuntar(HttpServletRequest request){
		Integer numeroIncidencia = Integer.parseInt(request.getParameter("incidencia"));
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			Incidencia incidencia = service.get(Incidencia.class,numeroIncidencia);
			List<FileItem> files = upload.parseRequest(request);
			List<Adjunto> adjuntos = ServletIOUtils.writeFiles(files, incidencia);
			service.guardar(adjuntos);
			incidencia.addAdjuntos(adjuntos);
			return returnToUpdateTicket(incidencia, request);
		} catch (FileUploadException | IOException e) {
			e.printStackTrace();
			return new ModelAndView(ERROR_VIEW);
		}
	}
	
	@RequestMapping("/descargar")
	public void descargar(HttpServletRequest request,HttpServletResponse response){
		String url = request.getParameter("url");
		try{
			String contentType = ContentType.lookUp(FilenameUtils.getExtension(url)).getMimeType();
			response.setContentType(contentType);
			InputStream input = new FileInputStream(url);
			OutputStream output = response.getOutputStream();
			IOUtils.copy(input, output);
			response.flushBuffer();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	
	@RequestMapping("/descargarReporte")
	public void descargarReporte(HttpServletRequest request,HttpServletResponse response){
		String reportType = request.getParameter("jmesaTag_e_");
		String clazName = request.getParameter("clazName");
		String templateName = clazName.toLowerCase().concat(".jrxml");
		String templatePath = request.getServletContext().getRealPath("/WEB-INF/jasper/".concat(templateName));
		List<?> collection = ((BeanResults) request.getSession().getAttribute(SEARCH_RESULTS_PREFIX.concat(clazName))).getResults();
		try {
			ReportResult bean = reportService.writeReport(reportType,templatePath,collection);
			OutputStream output = response.getOutputStream();
			response.setContentType(bean.getContentType().getMimeType());
			IOUtils.copy(bean.getInputStream(), output);
			response.flushBuffer();
		} catch (IOException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("changePassword")
	public ModelAndView changePassword(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		Integer legajo = Integer.parseInt(request.getParameter("legajo"));
		Usuario usuario = service.get(Usuario.class,legajo);
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String newPassConf = request.getParameter("newPassConf");
		String viewFrom = request.getParameter("viewFrom");
		if(usuario.getContrasena().equals(oldPass) && !Utils.isNullOrEmpty(newPass) && newPass.equals(newPassConf)){
			usuario.setContrasena(newPass);
			service.guardar(usuario);
			showPopup(request,"Contraseña cambiada correctamente",PopupType.INFORMATION);
		}else{
			showPopup(request, "Las contraseñas no coinciden.",PopupType.ERROR);
		}
		model.put(READ_BEAN,usuario);
		model.put(UPDATE_FLAG,Boolean.TRUE);
		model.put(READ_FLAG,Boolean.FALSE);
		return new ModelAndView(viewFrom, model);

	}
	
	private ModelAndView returnToUpdateTicket(Incidencia incidencia,HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		model.put(READ_BEAN,incidencia);
		model.put(UPDATE_FLAG,Boolean.TRUE);
		model.put(READ_FLAG,Boolean.FALSE);
		request.getSession().setAttribute(COLLECTION_INCIDENCIA,getCollectionsBean(Incidencia.class));
		request.setAttribute(VIEW_FROM,TICKET_DATA_LOAD);
		return new ModelAndView(TICKET_DATA_LOAD,model);
	}
}
