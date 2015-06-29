package com.gestor.common.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.gestor.common.reflect.ReflectionUtils;

public class PropertiesHelper {

	private static final PropertiesHelper INSTANCE = new PropertiesHelper();
	
	private Properties prop = new Properties();
	
	private static final String PATH = "C:\\aplicaciones\\gestor\\properties\\aplication.properties";
	
	private PropertiesHelper(){
		try {
			prop.load(new FileInputStream(PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key){
		return getProperty(key,null);
	}

	public String getProperty(String key,String defaultValue){
		return prop.getProperty(key,defaultValue);		
	}
	
	public <T extends Number> T getProperty(Class<T> type,String key) throws Exception{
		return getProperty(type,key,null);
	}

	public <T extends Number> T getProperty(Class<T> type,String key,String defaultValue) throws Exception{
		String propertyValue = getProperty(key, defaultValue);
		return ReflectionUtils.parse(type, propertyValue);
	}
	
	public static PropertiesHelper getInstance(){
		return INSTANCE;
	}
}
