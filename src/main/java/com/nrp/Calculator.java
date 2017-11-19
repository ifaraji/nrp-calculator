package com.nrp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Calculator {
    private HashMap<String, String> operators;
    private Stack<Double> stack;
    
    private final String CLEAR = "clear";
    
    public Calculator(){
	operators = new HashMap<String, String>();
	for (String op : Arrays.asList("+", "-", "*", "/", "sqrt", "undo", "clear"))
	    operators.put(op, op);
	stack = new Stack<Double>();
    }
	
    public void calculate(String input){
	String[] tokens = input.split(" ");
	for (String token : tokens) {
	    if (!operators.containsKey(token)){
		Double number = Double.parseDouble(token);
		stack.push(number);
	    }
	    
	    //clear operator
	    if (token.equalsIgnoreCase(CLEAR))
		clearStack();
	}
    }
    
    private void clearStack(){
	stack.removeAllElements();
    }
    
    @Override
    public String toString(){
	StringBuilder builder = new StringBuilder();
	Iterator<Double> iterator = stack.iterator();
	while(iterator.hasNext()){
	    builder.append(iterator.next());
	    builder.append(" ");
	}
	return builder.toString().trim();
    }
}
