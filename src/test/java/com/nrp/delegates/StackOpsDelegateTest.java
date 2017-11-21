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

}
