package org.agjin.java.tutorial.junit.step09_Time_Test;

import static org.junit.Assert.assertEquals;

import org.agjin.java.tutorial.junit.MessageUtil;
import org.junit.Test;

public class TestJunit {

	String message = "Robert";	
	MessageUtil messageUtil = new MessageUtil(message);
	
	@Test(timeout=1000)
	public void testPrintMessage() {	
		System.out.println("Inside testPrintMessage()");
		messageUtil.printMessage();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		
		}
	}

	@Test
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "Hi!" + "Robert";
		assertEquals(message,messageUtil.salutationMessage());
	}
}