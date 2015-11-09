package com.gestor.backend.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.gestor.common.dto.MailMessage;

public interface MailService {
	
	void sendMail(MailMessage mail)throws AddressException, MessagingException;
	
}
