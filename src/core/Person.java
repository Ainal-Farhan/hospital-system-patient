package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class Person {
	private String firstname;
	private String lastname;
	private String ic;
	private String gender;
	
	protected Person() {}
	
	protected Person(String fn, String ln, String icNo, String g){
		firstname = fn;
		lastname  = ln;
		ic = icNo;
		gender    = g;
	}
	
	protected String getFirstname() { 
		return firstname;
	}
	
	protected String getLastname() { 
		return lastname;
	}
	
	protected String getIC() { 
		return ic;
	}
	
	protected String getGender() { 
		return gender;
	}
}
