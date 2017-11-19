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
    private final String PLUS = "+";
    private final String MINUS = "-";
    private final String MUL = "*";
    private final String DIV = "/";
    
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
	    else if (token.equalsIgnoreCase(SQRT))
		sqrt();
	    //plus operator
	    else if (token.equalsIgnoreCase(PLUS))
		add();
	    //plus operator
	    else if (token.equalsIgnoreCase(MINUS))
		subtract();
	    //plus operator
	    else if (token.equalsIgnoreCase(MUL))
		mul();
	    //plus operator
	    else if (token.equalsIgnoreCase(DIV))
		div();
	}
    }
    
    private void clearStack(){
	stack.removeAllElements();
    }
    
    private void sqrt() {
	Double d = stack.pop();
	stack.push(Math.sqrt(d));
    }
    
    private void add(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 + d1);	
    }
    
    private void subtract(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 - d1);	
    }
    
    private void mul(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 * d1);	
    }
    
    private void div(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 / d1);	
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
