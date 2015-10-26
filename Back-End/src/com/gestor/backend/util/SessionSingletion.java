package com.gestor.backend.util;

import org.hibernate.FlushMode;
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
		String hibernateConfLocation = "com/gestor/backend/persistencia/conf/hibernate.cfg.xml";
		SessionFactory sessionFactory = new Configuration().configure(hibernateConfLocation).buildSessionFactory();
		this.session = sessionFactory.openSession();
		this.session.setFlushMode(FlushMode.COMMIT);
	}
	
	public static SessionSingletion getInstance(){
		return INSTANCE;
	}
	
	public Session getSession(){
		return session;
	}
}
