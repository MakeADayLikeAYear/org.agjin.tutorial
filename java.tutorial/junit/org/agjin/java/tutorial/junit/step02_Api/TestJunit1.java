package org.agjin.java.tutorial.junit.step02_Api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestJunit1 {

	@Test
	public void testAdd2() {
		//test data
		int num= 5;
		String str= "Junit is working fine";

		//check for equality
		assertEquals("Junit is working fine", str);
		
		//check for false condition
		assertFalse(num > 6);

		//check for not null value
		assertNotNull(str);
	}
}