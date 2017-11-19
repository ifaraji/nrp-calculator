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
	calc.calculate("a");
    }
    
    @Test(expected = RuntimeException.class)
    public void whenNonNumbersAreUsedThenThrowException(){
	calc.calculate("1 2 a b");
    }
    
    @Test
    public void whenValidOperatorsAreUsedThrowNoException(){
	calc.calculate("+ - * / sqrt undo clear");
	Assert.assertTrue(true);
    }
    
    @Test
    public void numbersAreStoredInStack(){
	calc.calculate("1.5 2.1 3.2");
	Assert.assertEquals("1.5 2.1 3.2", calc.toString());
    }
    
    @Test
    public void clearOperatorRemovesAllItemsFromStack(){
	calc.calculate("1.5 2.1 3.2");
	calc.calculate("clear");
	Assert.assertEquals("", calc.toString());
    }
    
    @Test
    public void sqrtOperator(){
	calc.calculate("9 sqrt");
	Assert.assertEquals("3", calc.toString());
    }
    
    @Test
    public void sqrtOperatorWorksOnTheTopItemOnly(){
	calc.calculate("9 16 sqrt");
	Assert.assertEquals("9 4", calc.toString());
    }
}
