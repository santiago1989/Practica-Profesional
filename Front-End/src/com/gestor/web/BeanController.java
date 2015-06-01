package com.gestor.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestor.web.dto.Popup;
import com.gestor.web.enums.PopupType;
import com.gestor.web.utils.Constants;

@Controller
public class BeanController {
		
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request){
		String user = request.getParameter(Constants.USER_SESSION);
		String password = request.getParameter("password");
		if(!Utils.isNullOrEmpty(user) && !Utils.isNullOrEmpty(password)){
			request.getSession(true).setAttribute(Constants.USER_SESSION,user);
			return new ModelAndView("home");			
		}
		else{
			request.setAttribute("errorMessage","Ingrese usuario / contraseña");
			return new ModelAndView("login");
		}
	}

	@RequestMapping("/homePage")
	public ModelAndView homePage(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.USER_SESSION)== null){
			return new ModelAndView("login");			
		}else {
			return new ModelAndView("home");
		}
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.USER_SESSION);
		return new ModelAndView("login");
	}
	
	@RequestMapping("/showPopup")
	public ModelAndView showPopup(HttpServletRequest request){
		showPopup(request,"Prueba de popup",PopupType.INFORMATION);
		return new ModelAndView("home");
	}
	
	private void showPopup(HttpServletRequest request,String text,PopupType type){
		Popup popup = new Popup(text, type);
		request.setAttribute(Constants.POPUP_REQUEST, popup);
	}
}
