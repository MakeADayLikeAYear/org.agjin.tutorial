package org.agjin.java.tutorial.collections.comparator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Arrays;

import org.junit.Test;

/**
 * http://www.java2s.com/Tutorial/Java/0140__Collections/GenericComparable.htm
 */
public class ComparatorTest {
	
	Person[] authors = {
		new Person("A", "S"), 
		new Person("J", "G"),
		new Person("T", "C"), 
		new Person("C", "S"),        
		new Person("P", "C"),
		new Person("T", "X"),
		new Person("B", "B") };
	
	public void log() {
		for (Person author : authors) {
			System.out.println(author);
		}
	}
	
	@Test
	public void comparableTest() {
		System.out.println("comparableTest---------------------------------------------");
		System.out.println("Original order:");
		log();
		
		Arrays.sort(authors);
		System.out.println("\nOrder after sorting using Comparable method:");
		log();
	}
	
	@Test
	public void comparatorTest() {
		System.out.println("comparatorTest---------------------------------------------");
		System.out.println("Original order:");
		log();
		
		Arrays.sort(authors, new ComparePersons()); // Sort using comparator
		System.out.println("\nOrder after sorting using comparator:");
		log();
	}
	
	@Test
	public void check() {
		Arrays.sort(authors, new ComparePersons());
		Person[] authors2 = authors.clone();
		Arrays.sort(authors);
		assertThat(authors2, equalTo(authors));
	}
}
