package com.nrp.delegates;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Stack;

public class StackOpsDelegate<T> {
    public void pushMultiple(Stack<T> stack, T[] items){
	for (T item : items)
	    stack.push(item);
    }
    
    /**
     * Stack-copies the stack1 into stack2. Similar to stack.push(stack1.pop())
     * while maintaining stack1 items and not actually popping them 
     * @param stack1
     * @param stack2
     */
    public void reverseCopy(Stack<T> stack1, Stack<T> stack2){
	for(int i = stack1.size() - 1; i >= 0  ; i--)
	    stack2.push(stack1.get(i));
    }
    
    public String doubleStackToString(Stack<Double> stack, int decimalPoints){
	StringBuilder builder = new StringBuilder();
	Iterator<Double> iterator = stack.iterator();
	while(iterator.hasNext()){
	    String s = "";
	    Double d = iterator.next();
	    if (d % 1 == 0) //if d does not have any floating points
		s = String.valueOf(d.intValue());
	    else {
		StringBuilder sb = new StringBuilder(".");
		for (int i = 0; i < decimalPoints; i++)
		    sb.append("#");
		DecimalFormat df2 = new DecimalFormat(sb.toString());
		s = df2.format(d);
	    }
	    builder.append(s);
	    builder.append(" ");
	}
	return builder.toString().trim();
    }
    
}
