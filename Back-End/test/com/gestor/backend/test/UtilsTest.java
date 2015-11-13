package com.gestor.backend.test;

import java.text.Normalizer;
import java.util.regex.Pattern;

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
		String normalize = Normalizer.normalize(cadena, Normalizer.Form.NFD);
		System.out.println(normalize);
//		System.out.println(Pattern.compile("\\p{ASCII}+").matcher(normalize).replaceAll(""));
	}

}
