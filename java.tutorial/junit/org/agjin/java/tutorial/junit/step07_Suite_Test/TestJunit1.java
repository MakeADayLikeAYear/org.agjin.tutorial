package org.agjin.java.tutorial.junit.step07_Suite_Test;

import static org.junit.Assert.assertEquals;

import org.agjin.java.tutorial.junit.MessageUtil;
import org.junit.Test;

public class TestJunit1 {

	String message = "Robert";	
	MessageUtil messageUtil = new MessageUtil(message);
	
	@Test
	public void testPrintMessage() {	
		System.out.println("Inside testPrintMessage()");
		assertEquals(message, messageUtil.printMessage());
	}
}
