package org.agjin.java.tutorial.junit.step01_BasicUsage;

import static org.junit.Assert.assertEquals;

import org.agjin.java.tutorial.junit.MessageUtil;
import org.junit.Test;

public class TestJunit {
	
	String message = "Hello World";	
	MessageUtil messageUtil = new MessageUtil(message);
	
	@Test
	public void testPrintMessage() {
		assertEquals(message, messageUtil.printMessage());
	}
	
	@Test
	public void testPrintMessage2() {
		message = "New Word";
		assertEquals(message,messageUtil.printMessage());
	}

}
