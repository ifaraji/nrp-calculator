package com.nrp;

public class Calculator {
    public void readInput(String input){
	String[] tokens = input.split(" ");
	for (String token : tokens)
	    Double.parseDouble(token);
    }
}
