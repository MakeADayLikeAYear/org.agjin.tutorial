package org.agjin.java.tutorial.junit.step12_Plug_with_ANT;

import static org.junit.Assert.assertEquals;

import org.agjin.java.tutorial.junit.MessageUtil;
import org.junit.Test;

public class TestMessageUtil {

	String message = "Robert";	
	MessageUtil messageUtil = new MessageUtil(message);
	
	@Test
	public void testPrintMessage() {	
		System.out.println("Inside testPrintMessage()");	  
		assertEquals(message,messageUtil.printMessage());
	}

	@Test
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "Hi!" + "Robert";
		assertEquals(message,messageUtil.salutationMessage());
	}
}