package com.nrp.client;

import java.util.Scanner;

import com.nrp.NRPCalculator;

public class ClientApp {
    
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	NRPCalculator calculator = new NRPCalculator();
	while (true) {
	    System.out.print("Enter command (type exit to end): ");
	    String cmd = sc.nextLine();
	    if (cmd.equalsIgnoreCase("exit"))
		break;
	    else {
		String str = calculator.calculate(cmd);
		if (str.length() > 0)
		    System.out.println(str);
		System.out.println(calculator);
	    }
	}
	sc.close();
    }
}
