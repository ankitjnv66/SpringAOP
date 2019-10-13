package com.example.SpringAOP.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Circle {
	
	@Value("circle name")
	private String name;

	public String getName() {
		System.out.println("this is called getter");
		
		return name;
	}

	public void setName(String name) {
		System.out.println("this is called setter");
		this.name = name;
		throw new RuntimeException(); // now @AfterExecution won't execute as setter method is throwing some exception so method won't execute successfully!!s
	}
	
	public String returningMethod() {
		System.out.println("this is called setter need to return something ");	
		return "ankit Singh";
	}
	
	

	@Override
	public String toString() {
		return "Circle [name=" + name + "]";
	}
	
	

}
