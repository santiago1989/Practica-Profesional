package com.gestor.backend.util;

import java.sql.Connection;

public class SingletonConnection {
	
	private static final SingletonConnection INSTANCE = new SingletonConnection();
	
	private Connection connection;
	
	private SingletonConnection(){
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
	}
	
	public Connection getConnection(){
		return connection;
	}

	public static SingletonConnection getInstance(){
		return INSTANCE;
	}
}
