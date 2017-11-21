package com.nrp.delegates;

import java.util.Stack;

public class StackOpsDelegate<T> {
    public void pushMultiple(Stack<T> stack, T[] items){
	for (T item : items)
	    stack.push(item);
    }
    
    public void copyTo(Stack<T> stack1, Stack<T> stack2){
	for(int i = 0; i < stack1.size(); i++)
	    stack2.push(stack1.get(i));
    }
    
}
