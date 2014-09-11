package org.agjin.java.tutorial.generic;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.Test;

/**
 * http://opentutorials.org/module/516/6237
 */
public class GenericTest {
	
	/**
	 * 기본적인 Generic Test 
	 */
	@Test
	public void TypeTest() {
		Person<String> p1 = new Person<String>();
		Person<StringBuffer> p2 = new Person<StringBuffer>();
		
		p1.info = "a";
		p2.info = new StringBuffer("aa");
		
		assertThat(p1.info, instanceOf(String.class));
		assertThat(p2.info, instanceOf(StringBuffer.class));
	}
	
	/**
	 * 인자가 2개인 Generic
	 * 메소드에 있는 Generic
	 * Test
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void TypeTest2() {
		EmployeeInfo e = new EmployeeInfo(1);
		Integer i = new Integer(10);
		
		Person2<EmployeeInfo, Integer> p1 = new Person2<EmployeeInfo, Integer>(e, i);
		assertThat(p1.info, instanceOf(EmployeeInfo.class));
		assertThat(p1.id, instanceOf(Integer.class));
		
		/**
		 * 제네릭 생략 가능하다.
		 */
		@SuppressWarnings("rawtypes")
		Person2 p2 = new Person2(e, i);
		assertThat(p2.info, instanceOf(EmployeeInfo.class));
		assertThat(p2.id, instanceOf(Integer.class));
		
		/**
		 * 메소드에 적용된 제네릭 호출.
		 */
		p1.<EmployeeInfo>printInfo(e);
		assertThat(p2.info, instanceOf(EmployeeInfo.class));
		
		/** 
		 * 제네릭을 생략하면 메소드도 생략해야한다.
		 */
		//p2.<EmployeeInfo>printInfo(e);  --> Error 발생.
		p2.printInfo(e);
		
	}
	
	/**
	 * 상속 Generic Test
	 */
	@Test
	public void extendsTest() {
		 Person3<EmployeeInfo> p1 = new Person3<EmployeeInfo>(new EmployeeInfo(1));
		 
		 /**
		  * Person3<String> p2 = new Person3<String>("부장");
		  * Error --> Info 를 상속한 Class가 오지 않았으므로 
		  */
		 assertThat(p1.info, instanceOf(Info.class));
	}
}
