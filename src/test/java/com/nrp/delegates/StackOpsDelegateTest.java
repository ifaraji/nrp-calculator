package com.nrp.delegates;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class StackOpsDelegateTest {
    StackOpsDelegate<String> delegate;
    
    @Before
    public void init(){
	delegate = new StackOpsDelegate<String>();
    }
    
    @Test
    public void pushFiveElementsToStack(){
	Stack<String> stack = new Stack<String>();
	delegate.pushMultiple(stack, new String[]{"a", "b", "c", "d", "e"});
	Assert.assertEquals(5, stack.size());
    }

    @Test
    public void copyStack1ToStack2(){
	Stack<String> stack1 = new Stack<String>();
	Stack<String> stack2 = new Stack<String>();
	stack1.push("a");
	stack1.push("b");
	stack1.push("c");
	stack1.push("d");
	stack1.push("e");
	delegate.copyTo(stack1, stack2);
	Assert.assertEquals(stack1.size(), stack2.size());
    }
}
