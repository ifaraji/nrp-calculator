package com.nrp;

import java.util.Arrays;
import java.util.HashMap;

public class Calculator {
    private HashMap<String, String> operators;
    public Calculator(){
	operators = new HashMap<String, String>();
	for (String op : Arrays.asList("+", "-", "*", "/", "sqrt", "undo", "clear"))
	    operators.put(op, op);
    }
	
    public void readInput(String input){
	String[] tokens = input.split(" ");
	for (String token : tokens)
	    if (!operators.containsKey(token))
		Double.parseDouble(token);
    }
}
