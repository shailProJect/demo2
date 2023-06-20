package com.example.demo;
import static spark.Spark.*;  

public class SparkTesting {

	public static void main(String[] args) {
		
		  get("/hello", (req, res) -> "Hello World");  
	}
}
