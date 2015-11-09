package com.gestor.backend.test;

import javax.mail.MessagingException;

import org.junit.Test;

import com.gestor.backend.service.MailService;
import com.gestor.backend.service.impl.MailServiceImpl;
import com.gestor.common.dto.MailMessage;

public class MailTest {
	
	private MailService mailService = new MailServiceImpl();
	
	@Test
	public void test() {
		MailMessage message = new MailMessage(new String[]{"santiagohernangonzalez@gmail.com"},"asunto de ejemplo","mail de prueba");
		try{
			mailService.sendMail(message);
			System.out.println("Mensaje enviado");
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}

}
