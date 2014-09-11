package org.agjin.java.tutorial.generic;

public class Person2<T, S> {
	
	public T info;
	public S id;
	
	// 생성자에 적용
	public Person2 (T info, S id) {
		this.info = info;
		this.id = id;
	}
	
	// 메소드에 적용
	public <U> void printInfo(U info) {
		System.out.println(info);
	}
}
