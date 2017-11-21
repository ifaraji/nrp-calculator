package com.nrp.delegates;

import java.util.Stack;

public class StackOpsDelegate<T> {
    public void pushMultiple(Stack<T> stack, T[] items){
	for (T item : items)
	    stack.push(item);
    }
    
    public void copyTo(Stack<T> stack1, Stack<T> stack2){
	
    }
    
}
