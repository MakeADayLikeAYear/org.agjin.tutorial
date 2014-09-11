package org.agjin.java.tutorial.generic;

public class Person3<T extends Info> {
	
	public T info;
	
	// 상속으로 구현된 생성자.
	public Person3 (T info) {
		this.info = info;
	}
	
}
