package com.nrp;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class NRPCalculatorTest {
    NRPCalculator calc;
    
    @Before
    public void init(){
	calc = new NRPCalculator();
    }
    
    /* test is not necessary anymore as error message is directed to the screen
    @Test(expected = RuntimeException.class)
    public void whenNonNumberIsUsedThenThrowException(){
	calc.calculate("a");
    }
    */
    
    /* test is not necessary anymore as error message is directed to the screen
    @Test(expected = RuntimeException.class)
    public void whenNonNumbersAreUsedThenThrowException(){
	calc.calculate("1 2 a b");
    }
    */
    
    /* 
    Conflict with operators implementation. 
    @Test 
    public void whenValidOperatorsAreUsedThrowNoException(){
	calc.calculate("+ - * / sqrt undo clear");
	Assert.assertTrue(true);
    }*/
    
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
    
    @Test
    public void plusOperator(){
	calc.calculate("10 26 +");
	Assert.assertEquals("36", calc.toString());
    }
    
    @Test
    public void minusOperator(){
	calc.calculate("15 5 -");
	Assert.assertEquals("10", calc.toString());
    }
    
    @Test
    public void multiplicationOperator(){
	calc.calculate("8 9 *");
	Assert.assertEquals("72", calc.toString());
    }
    
    @Test
    public void divisionOperator(){
	calc.calculate("20 5 /");
	Assert.assertEquals("4", calc.toString());
    }
    
    @Test
    public void undoOperatorUndoesPreviousOperation(){
	calc.calculate("20 5 /");
	calc.calculate("undo");
	Assert.assertEquals("20 5", calc.toString());
    }
    
    @Test
    public void undoOperatorAfterSqrt(){
	calc.calculate("20 5 / sqrt");
	calc.calculate("undo");
	Assert.assertEquals("4", calc.toString());
    }
    
    @Test
    public void undoOperatorAfterClear(){
	calc.calculate("20 5 clear");
	calc.calculate("undo");
	Assert.assertEquals("20 5", calc.toString());
    }
    
    @Test
    public void undoOperatorAfterClear2(){
	calc.calculate("20 5 / sqrt clear");
	calc.calculate("undo");
	Assert.assertEquals("2", calc.toString());
	calc.calculate("5 7 clear");
	calc.calculate("undo");
	Assert.assertEquals("2 5 7", calc.toString());
    }
    
    @Test
    public void doubleUndoOperation(){
	calc.calculate("20 5 / sqrt clear");
	calc.calculate("undo undo");
	Assert.assertEquals("4", calc.toString());
    }
    
    @Test
    public void undoOperationShouldWorkOnAddedNumbersAsWell(){
	calc.calculate("5 4 2 3");
	calc.calculate("undo undo *");
	Assert.assertEquals("20", calc.toString());
    }
    
    @Test
    public void numbersToBeDisplayedToTenDecimalPoints(){
	calc.calculate("10 3 /");
	Assert.assertEquals("3.3333333333", calc.toString());
    }
    
    @Test
    public void numbersToBeDisplayedToTenDecimalPoints2(){
	calc.calculate("11 2 /");
	Assert.assertEquals("5.5", calc.toString());
    }
    
    @Test
    public void whenDivNotEnoughNumbersWarningMessageIsShown(){
	String output = "";
	output = calc.calculate("11 /");
	Assert.assertEquals("operator / (position: 4): insufficient parameters", output);
    }
    
    
    @Test
    public void whenAddingNotEnoughNumbersWarningMessageIsShown(){
	String output = "";
	output = calc.calculate("11 +");
	Assert.assertEquals("operator + (position: 4): insufficient parameters", output);
    }
    
    @Test
    public void whenSubtractingNotEnoughNumbersWarningMessageIsShown(){
	String output = "";
	output = calc.calculate("11 -");
	Assert.assertEquals("operator - (position: 4): insufficient parameters", output);
    }
    
    @Test
    public void whenMultiplyingNotEnoughNumbersWarningMessageIsShown(){
	String output = "";
	output = calc.calculate("11 *");
	Assert.assertEquals("operator * (position: 4): insufficient parameters", output);
    }
    
    @Test
    public void whenSqrtNotEnoughNumbersWarningMessageIsShown(){
	String output = "";
	output = calc.calculate("sqrt");
	Assert.assertEquals("operator sqrt (position: 1): insufficient parameters", output);
    }
    
    @Test
    public void whenNotEnoughNumbersWarningMessageIsShown(){
	String output = "";
	output = calc.calculate("1 2 3 * 5 + * * 6 5");
	Assert.assertEquals("operator * (position: 15): insufficient parameters", output);
    }
    
}
