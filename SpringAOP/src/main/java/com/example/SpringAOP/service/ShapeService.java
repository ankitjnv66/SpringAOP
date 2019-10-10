package com.example.SpringAOP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringAOP.model.Circle;
import com.example.SpringAOP.model.Triangle;

@Service
public class ShapeService {
	
	@Autowired
	private Circle circle;
	
	@Autowired
	private Triangle triangle;

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Triangle getTriangle() {
		return triangle;
	}

	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
	
	
	

}
