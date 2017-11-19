package com.nrp;

import org.junit.Before;
import org.junit.Test;

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
}