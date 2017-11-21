package com.nrp;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class BasicCalculatorTest {
    BasicCalculator calc;
    
    @Before
    public void init(){
	calc = new BasicCalculator();
    }
    
    @Test
    public void addTwoNumbers(){
	double d = calc.add(10.5, 15.6);
	Assert.assertTrue(26.1 == d);
    }
    
    @Test
    public void suntractTwoNumbers(){
	double d = calc.subtract(26.5, 12.3);
	Assert.assertTrue(14.2 == d);
    }
    
    @Test
    public void multiplyTwoNumbers(){
	double d = calc.subtract(10.5, 15.6);
	Assert.assertTrue(163.8 == d);
    }
    
    @Test
    public void divideTwoNumbers(){
	double d = calc.subtract(26.445, 12.3);
	Assert.assertTrue(2.15 == d);
    }
}
