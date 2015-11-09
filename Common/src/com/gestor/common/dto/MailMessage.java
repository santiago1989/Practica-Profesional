package com.gestor.common.dto;

import org.apache.catalina.tribes.util.Arrays;

import com.gestor.common.util.Constants;
import com.gestor.common.util.Utils;

public class MailMessage {
	
	private String remitente;
	
	private String destinatarios;
	
	private String subject;
	
	private String body;
	
	public MailMessage(String[] destinatarios,
			String subject, String body) {
		super();
		this.remitente = Constants.FROM_ADDRESS;
		this.destinatarios = Utils.removeExpression(Arrays.toString(destinatarios),new char[]{'[',']','{','}'});
		this.subject = subject;
		this.body = body;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
