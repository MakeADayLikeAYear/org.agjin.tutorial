package org.agjin.java.tutorial.junit.step10_Exceptions_Test;

import static org.junit.Assert.assertEquals;

import org.agjin.java.tutorial.junit.MessageUtil;
import org.junit.Test;

public class TestJunit {

	String message = "Robert";	
	MessageUtil messageUtil = new MessageUtil(message);
	
	@Test(expected = ArithmeticException.class)
	public void testPrintMessage() {	
		System.out.println("Inside testPrintMessage()");	  
		messageUtil.printMessage();	
	}
	
	@Test(expected = ArithmeticException.class)
	public void testPrintMessage2() {	
		System.out.println("Inside testPrintMessage()");	  
		messageUtil.printMessage();	
		throw new ArithmeticException("Xx");
	}

	@Test
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "Hi!" + "Robert";
		assertEquals(message,messageUtil.salutationMessage());
	}
}
