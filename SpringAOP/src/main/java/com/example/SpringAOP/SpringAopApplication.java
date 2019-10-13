package com.example.SpringAOP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.SpringAOP.service.ShapeService;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner{

	@Autowired
	private ShapeService shapeService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//shapeService.getCircle().setName("setting name as circle..");
		//System.out.println(shapeService.getCircle().getName());
		//System.out.println(shapeService.getTriangle().getName());
		//shapeService.getCircle().returningMethod();
		//shapeService.getTriangle().checkException();
		//shapeService.getTriangle().aroundCheck();
		shapeService.getTriangle().checkingCustomAnnotation();
	}

}
