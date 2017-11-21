package com.nrp;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;


import com.nrp.delegates.StackOpsDelegate;

public class NRPCalculator extends BasicCalculator{
    private HashMap<String, String> operators;
    
    private Stack<Double> stack;
    private Stack<Double> undoStack; //keeps the pre-operation numbers
    private Stack<String> opStack; //sequence of operations for undoing purpose
    
    private StackOpsDelegate<Double> stackDelegate;
    
    private final String CLEAR = "clear";
    private final String SQRT = "sqrt";
    private final String PLUS = "+";
    private final String MINUS = "-";
    private final String MUL = "*";
    private final String DIV = "/";
    private final String UNDO = "undo";
    private final String NUM = "NUMBER"; //control key for undoing added numbers
    
    public NRPCalculator(){
	operators = new HashMap<String, String>();
	for (String op : Arrays.asList("+", "-", "*", "/", "sqrt", "undo", "clear"))
	    operators.put(op, op);
	stack = new Stack<Double>();
	undoStack = new Stack<Double>();
	opStack = new Stack<String>();
	stackDelegate = new StackOpsDelegate<Double>();
    }
	
    public void calculate(String input){
	String[] tokens = input.split(" ");
	int pos = 1; //keeps track of the position of items in the list
	try {
	for (String token : tokens) {
	    if (!operators.containsKey(token)){
		Double number = Double.parseDouble(token);
		stack.push(number);
		opStack.push(NUM);
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
    	    	    sqrt(pos);
    	    	//plus operator
    	    	else if (token.equalsIgnoreCase(PLUS))
    	    	    add(pos);
    	    	//minus operator
    	    	else if (token.equalsIgnoreCase(MINUS))
    	    	    subtract(pos);
    	    	//multiplication operator
    	    	else if (token.equalsIgnoreCase(MUL))
    	    	    multiply(pos);
    	    	//division operator
    	    	else if (token.equalsIgnoreCase(DIV))
    	    	    divide(pos);
    	    	//undo operator
    	    	else if (token.equalsIgnoreCase(UNDO))
    	    	    undo();
	    }
	    pos += token.length();
	    pos++; //+1 to account for the spaces between tokens in the input string
	}
	} catch (RuntimeException e) {
	    System.out.println(e.getMessage());
	}
	System.out.println("Stack: " + this);
    }
    
    private void clearStack(){
	int i = stack.size();	
	stackDelegate.reverseCopy(stack, undoStack);
	undoStack.push(Double.valueOf(i)); //we need to know how many items are removed by this clear operation 
	stack.removeAllElements(); 
    }
    
    private void sqrt(int pos) {
	if (stack.size() < 1)
	    throw new RuntimeException(String.format("operator %s (position: %d): insufficient parameters", SQRT, pos ));
	Double d = stack.pop();
	stack.push(Math.sqrt(d));
	undoStack.push(d);
    }
    
    private void add(int pos){
	if (stack.size() < 2)
	    throw new RuntimeException(String.format("operator %s (position: %d): insufficient parameters", PLUS, pos ));	
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(add(d2, d1));
	stackDelegate.pushMultiple(undoStack, new Double[]{d1, d2});
    }
    
    private void subtract(int pos){
	if (stack.size() < 2)
	    throw new RuntimeException(String.format("operator %s (position: %d): insufficient parameters", MINUS, pos ));
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(subtract(d2, d1));	
	stackDelegate.pushMultiple(undoStack, new Double[]{d1, d2});
    }
    
    private void multiply(int pos){
	if (stack.size() < 2)
	    throw new RuntimeException(String.format("operator %s (position: %d): insufficient parameters", MUL, pos ));
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(multiply(d2, d1));	
	stackDelegate.pushMultiple(undoStack, new Double[]{d1, d2});
    }
    
    private void divide(int pos){
	if (stack.size() < 2)
	    throw new RuntimeException(String.format("operator %s (position: %d): insufficient parameters", DIV, pos ));
	Double d1 = stack.pop();
	Double d2 = stack.pop();
	stack.push(divide(d2, d1));	
	stackDelegate.pushMultiple(undoStack, new Double[]{d1, d2});
    }
    
    private void undo(){
	if (opStack.size() == 0) //nothing to undo
	    return;
	String lastOperator = opStack.pop();
	if (lastOperator.equalsIgnoreCase(NUM))
	    stack.pop();
	else if (lastOperator.equalsIgnoreCase(SQRT)){
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
	    else {
		DecimalFormat df2 = new DecimalFormat(".##########");
		s = df2.format(d);
	    }
	    builder.append(s);
	    builder.append(" ");
	}
	return builder.toString().trim();
    }
    
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	NRPCalculator calculator = new NRPCalculator();
	while (true) {
	    System.out.print("Enter command (type exit to end): ");
	    String cmd = sc.nextLine();
	    if (cmd.equalsIgnoreCase("exit"))
		break;
	    else
		calculator.calculate(cmd);
	}
	sc.close();
    }
}
