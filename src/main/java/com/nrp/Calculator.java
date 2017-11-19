package com.nrp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Calculator {
    private HashMap<String, String> operators;
    private Stack<Double> stack;
    
    private final String CLEAR = "clear";
    private final String SQRT = "sqrt";
    
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
	    
	    //sqrt operator
	    if (token.equalsIgnoreCase(SQRT))
		sqrt();
	}
    }
    
    private void clearStack(){
	stack.removeAllElements();
    }
    
    private void sqrt() {
	Double d = stack.pop();
	stack.push(Math.sqrt(d));
    }
    
    @Override
    public String toString(){
	StringBuilder builder = new StringBuilder();
	Iterator<Double> iterator = stack.iterator();
	while(iterator.hasNext()){
	    String s = "";
	    Double d = iterator.next();
	    if (d % 1 == 0) //if d does not have any floating points
		s = String.valueOf(d.intValue());
	    else
		s = String.valueOf(d);
	    builder.append(s);
	    builder.append(" ");
	}
	return builder.toString().trim();
    }
}
