package org.agjin.java.tutorial.junit.step07_Suite_Test;

import static org.junit.Assert.assertEquals;

import org.agjin.java.tutorial.junit.MessageUtil;
import org.junit.Test;

public class TestJunit2 {

	String message = "Robert";	
	MessageUtil messageUtil = new MessageUtil(message);
 
	@Test
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "Hi!" + "Robert";
		assertEquals(message,messageUtil.salutationMessage());
	}
}
