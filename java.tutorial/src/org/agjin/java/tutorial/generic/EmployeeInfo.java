package org.agjin.java.tutorial.generic;

abstract class Info{
	public abstract int getLevel();
}

public class EmployeeInfo extends Info {
	public int rank;
	
	EmployeeInfo(int rank){
		this.rank = rank;
	}
	
	public int getLevel(){
		return this.rank;
	}
}
