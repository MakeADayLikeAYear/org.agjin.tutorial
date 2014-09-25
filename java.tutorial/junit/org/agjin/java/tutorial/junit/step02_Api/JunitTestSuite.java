package org.agjin.java.tutorial.junit.step02_Api;

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class JunitTestSuite {
	
	/**
	 * 아래와 같이 하려면 TestCase를 구현한 TestJunit2 이어야한다.
	 * --> TestJunit2 extends TestCase
	 * @param a
	 */
	public static void main(String[] a) {
		// add the test's in the suite
		TestSuite suite = new TestSuite(TestJunit2.class );
		TestResult result = new TestResult();
		suite.run(result);
		System.out.println("Number of test cases = " + result.runCount());
	}
}
