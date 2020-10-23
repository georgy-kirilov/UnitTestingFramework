package tests;

import java.lang.reflect.InvocationTargetException;

import models.Tester;

public class Program {

	public static void main(String[] args) 
			throws NoSuchMethodException, SecurityException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{	
		Tester tester = new SomeTester();
		tester.runAllTests();
		
		System.out.println(tester.toString());
	}

}
