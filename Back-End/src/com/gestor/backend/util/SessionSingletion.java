package com.gestor.backend.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionSingletion {

	private static final SessionSingletion INSTANCE = new SessionSingletion();

	private Session session;
	
	public SessionSingletion(){
		init();
	}
	
	private void init(){
		SessionFactory sessionFactory = new Configuration().configure("src/com/gestor/backend/persistencia/conf/hibernate.cfg.xml").buildSessionFactory();
		this.session = sessionFactory.openSession();
	}
	
	public static SessionSingletion getInstance(){
		return INSTANCE;
	}
	
	public Session getSession(){
		return session;
	}
}
