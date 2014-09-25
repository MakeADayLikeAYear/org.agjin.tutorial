package org.agjin.eclipser.jface;

public class Person {
	/**
	 * @uml.property  name="firstName"
	 */
	public String firstName = "John";
	/**
	 * @uml.property  name="lastName"
	 */
	public String lastName = "Doe";
	/**
	 * @uml.property  name="age"
	 */
	public int age = 37;
	
	/**
	 * @uml.property  name="children"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	public Person[] children = new Person[0];
	/**
	 * @uml.property  name="parent"
	 * @uml.associationEnd  
	 */
	public Person parent;
	
	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public Person(String firstName, String lastName, int age, Person[] children) {
		this(firstName, lastName, age);
		this.children = children;
		
		for (int i=0, size=children.length; i<size; i++) {
			children[i].parent = this;
		}
	}
	
	public static Person[] example() {
		return new Person[]{
			new Person("Dan", "Rubel", 41, new Person[]{
				new Person("Beth", "Rubel", 11),	
				new Person("David", "Rubel", 6)}),
			new Person("Eric", "Clayberg", 42, new Person[]{
				new Person("Lauren", "Clayberg", 9),	
				new Person("Lee", "Clayberg", 7)}),
			new Person("Mike", "Taylor", 55)
		};
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}

}
