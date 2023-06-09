package com.student.core;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * An object that represent the database operations on the student
 */
// @JsonInclude -- Serialized. Without annotation property values are always included, 
// but by using this annotation one can specify simple exclusion rules to reduce amount 
// of properties to write out.
// @XmlRootElement -- Maps clases/enums to xml
@JsonInclude
@XmlRootElement
public class Student {
	 
	private long id;
 
	private String firstName;
	 
	private String surname;
 
	private String dept;
	 
	private Double fees;
	
	public Student() {
		super();
	}
	public Student(String firstName, String surname, String dept, Double fees) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.dept = dept;
		this.fees = fees;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", dept=" + dept + ", fees="
				+ fees + "]";
	}
	
	
}
