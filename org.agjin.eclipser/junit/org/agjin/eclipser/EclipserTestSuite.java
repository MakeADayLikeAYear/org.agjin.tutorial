package org.agjin.eclipser;

import junit.framework.Test;
import junit.framework.TestSuite;

public class EclipserTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Eclipser test suite");
		
		suite.addTest(new TestSuite(OpenEclipserTest.class));
		suite.addTest(new TestSuite(AddToEclipserTest.class));
		
		return suite;
	}
}
