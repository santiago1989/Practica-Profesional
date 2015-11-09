package com.gestor.backend.service.impl;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.gestor.backend.authenticator.Authenticator;
import com.gestor.backend.service.MailService;
import com.gestor.common.dto.MailMessage;
import com.gestor.common.util.Constants;

public class MailServiceImpl implements MailService {

	private Session session;
	
	private static final String SMTP_SERVER = "smtp.gmail.com";

	private static final String SMTP_PORT = "587";
	private static final String SMTP_PORT_SOCKET_FACTORY = "465";
	
	private static final String SMTP_SERVER_CONSTANT = "mail.smtp.host";
	
	private static final String SMTP_PORT_CONSTANT = "mail.smtp.port";
	private static final String SMTP_PORT_SOCKET_FACTORY_CONSTANT = "mail.smtp.socketFactory.port";
	
	private static final String SMTP_SOCKET_FACTORY_CLASS = "javax.net.ssl.SSLSocketFactory";
	private static final String SMTP_SOCKET_FACTORY_CLASS_CONSTANT = "mail.smtp.socketFactory.class";

	private static final String SMTP_AUTHENTICATION_CONSTANT = "mail.smtp.auth";
	
	private static final String SMTP_STARTTLS_CONSTANT = "mail.smtp.starttls.enable";
	
	public MailServiceImpl() {
		Properties properties = System.getProperties();
		properties.put(SMTP_SERVER_CONSTANT, SMTP_SERVER);
		properties.put(SMTP_PORT_CONSTANT, SMTP_PORT);
		properties.put(SMTP_SOCKET_FACTORY_CLASS_CONSTANT,SMTP_SOCKET_FACTORY_CLASS);
		properties.put(SMTP_PORT_SOCKET_FACTORY_CONSTANT,SMTP_PORT_SOCKET_FACTORY);
		properties.put(SMTP_AUTHENTICATION_CONSTANT,String.valueOf(Boolean.TRUE));
		properties.put(SMTP_STARTTLS_CONSTANT,String.valueOf(Boolean.TRUE));
		session = Session.getInstance(properties,new Authenticator(Constants.FROM_ADDRESS,Constants.FROM_ADDRESS_PASSWORD));
	}
	
	@Override
	public void sendMail(MailMessage mail) throws AddressException, MessagingException {
		if(null != mail){
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getRemitente()));
			if(mail.getDestinatarios().contains(",")){
				message.setReplyTo(InternetAddress.parse(mail.getDestinatarios()));
			}else{
				message.setRecipient(RecipientType.TO, new InternetAddress(mail.getDestinatarios()));
			}
			message.setSubject(mail.getSubject());
			message.setText(mail.getBody());
			Transport.send(message);
		}
	}
}
