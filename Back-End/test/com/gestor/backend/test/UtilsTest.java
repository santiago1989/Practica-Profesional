package com.gestor.backend.test;

import org.junit.Test;

import com.gestor.common.util.Utils;

public class UtilsTest {

	@Test
	public void test() {
		String source = "[s,a,b,c]";
		System.out.println(Utils.removeExpression(source,new char[]{'[',']'}));
	}
	
	@Test
	public void testTildes(){
		String cadena = "personalización";
		System.out.println(Utils.normalize(cadena));
	}

}
