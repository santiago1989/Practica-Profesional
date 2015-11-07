package com.gestor.backend.test;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

public class URLTest {

	@Test
	public void test() {
		System.out.println(FilenameUtils.getName("c:\\imagenes\\ticket\\ticket.jpg"));
		System.out.println(FilenameUtils.getExtension("c:\\imagenes\\ticket\\ticket.jpg"));
	}

}
