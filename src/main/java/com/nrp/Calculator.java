package com.nrp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    private HashMap<String, String> operators;
    private Stack<Double> stack; 
    
    public Calculator(){
	operators = new HashMap<String, String>();
	for (String op : Arrays.asList("+", "-", "*", "/", "sqrt", "undo", "clear"))
	    operators.put(op, op);
	stack = new Stack<Double>();
    }
	
    public void readInput(String input){
	String[] tokens = input.split(" ");
	for (String token : tokens)
	    if (!operators.containsKey(token)){
		Double number = Double.parseDouble(token);
		stack.push(number);
	    }
    }
}
