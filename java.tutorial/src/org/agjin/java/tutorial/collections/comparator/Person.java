package org.agjin.java.tutorial.collections.comparator;

public class Person implements Comparable<Person> {
	
	private String firstName;
	private String surname;
	
	public Person(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSurname() {
		return surname;
	}
	public String toString() {
		return firstName + " " + surname;
	}
	public int compareTo(Person person) {
		int result = surname.compareTo(person.surname);
		return result == 0 ? firstName.compareTo(((Person) person).firstName) : result;
	}
	
	public static void main(String[] args) {
		String a = "XA";
		String b = "CD";
		
		System.out.println(a.compareTo(b));
				
	}
}