package com.nrp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Calculator {
    private HashMap<String, String> operators;
    private Stack<Double> stack;
    private Stack<Double> undoStack;
    private Stack<String> opStack; //sequence of operations for undoing purpose
    
    private final String CLEAR = "clear";
    private final String SQRT = "sqrt";
    private final String PLUS = "+";
    private final String MINUS = "-";
    private final String MUL = "*";
    private final String DIV = "/";
    private final String UNDO = "undo";
    
    public Calculator(){
	operators = new HashMap<String, String>();
	for (String op : Arrays.asList("+", "-", "*", "/", "sqrt", "undo", "clear"))
	    operators.put(op, op);
	stack = new Stack<Double>();
	undoStack = new Stack<Double>();
	opStack = new Stack<String>();
    }
	
    public void calculate(String input){
	String[] tokens = input.split(" ");
	for (String token : tokens) {
	    if (!operators.containsKey(token)){
		Double number = Double.parseDouble(token);
		stack.push(number);
	    } else {
		//tracking operators for undoing purpose
		//no need to track undo itself
		if (!token.equalsIgnoreCase(UNDO))
		    opStack.push(token);
    	    	
		//clear operator
    	    	if (token.equalsIgnoreCase(CLEAR))
    	    	    clearStack(); 
    	    	//sqrt operator
    	    	else if (token.equalsIgnoreCase(SQRT))
    	    	    sqrt();
    	    	//plus operator
    	    	else if (token.equalsIgnoreCase(PLUS))
    	    	    add();
    	    	//minus operator
    	    	else if (token.equalsIgnoreCase(MINUS))
    	    	    subtract();
    	    	//multiplication operator
    	    	else if (token.equalsIgnoreCase(MUL))
    	    	    mul();
    	    	//division operator
    	    	else if (token.equalsIgnoreCase(DIV))
    	    	    div();
    	    	//undo operator
    	    	else if (token.equalsIgnoreCase(UNDO))
    	    	    undo();
	    }
	}
    }
    
    private void clearStack(){
	//stack.removeAllElements(); 
	int i = 0; 
	while(!stack.isEmpty()){
	    i++;
	    undoStack.push(stack.pop());
	}
	//we need to know how many items were removed by this clear operation
	undoStack.push(Double.valueOf(i));
    }
    
    private void sqrt() {
	Double d = stack.pop();
	stack.push(Math.sqrt(d));
	undoStack.push(d);
    }
    
    private void add(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 + d1);
	undoStack.push(d1);
	undoStack.push(d2);
    }
    
    private void subtract(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 - d1);	
	undoStack.push(d1);
	undoStack.push(d2);
    }
    
    private void mul(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 * d1);	
	undoStack.push(d1);
	undoStack.push(d2);
    }
    
    private void div(){
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(d2 / d1);	
	undoStack.push(d1);
	undoStack.push(d2);
    }
    
    private void undo(){
	String lastOperator = opStack.pop();
	if (lastOperator.equalsIgnoreCase(SQRT)){
	    stack.pop();
	    stack.push(undoStack.pop());
	} else if (lastOperator.equalsIgnoreCase(CLEAR)) {
	    double d = undoStack.pop();
	    for (int i = 0; i < d; i++)
		stack.push(undoStack.pop());
	} else {
	    stack.pop();
	    stack.push(undoStack.pop());
	    stack.push(undoStack.pop());	    
	}
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
