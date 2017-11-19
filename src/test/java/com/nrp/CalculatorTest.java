package com.nrp;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class CalculatorTest {
    Calculator calc;
    
    @Before
    public void init(){
	calc = new Calculator();
    }
    
    @Test(expected = RuntimeException.class)
    public void whenNonNumberIsUsedThenThrowException(){
	calc.readInput("a");
    }
    
    @Test(expected = RuntimeException.class)
    public void whenNonNumbersAreUsedThenThrowException(){
	calc.readInput("1 2 a b");
    }
    
    @Test
    public void whenValidOperatorsAreUsedThrowNoException(){
	calc.readInput("+ - * / sqrt undo clear");
	Assert.assertTrue(true);
    }
    
    @Test
    public void numbersAreStoredInStack(){
	calc.readInput("1.5 2.1 3.2");
	Assert.assertEquals("1.5 2.1 3.2", calc.toString());
    }
}
