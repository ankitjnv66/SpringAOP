package com.example.SpringAOP.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.SpringAOP.aspect.CustomAnnotation;

@Component
public class Triangle {
	
	@Value("triangle name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void checkException() {
		throw new NullPointerException();
	}
	
	
	public Object aroundCheck() {
		System.out.println("around check method is being called..");
		//throw new RuntimeException();
		return new String("Returning String Object!!");
	}
	
	@CustomAnnotation
	public void checkingCustomAnnotation() {
		System.out.println("Checking custom annotation..!!!");
	}
	

}
