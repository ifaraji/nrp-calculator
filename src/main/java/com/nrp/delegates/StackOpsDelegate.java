package com.nrp.delegates;

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
    
}
