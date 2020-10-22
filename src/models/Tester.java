package models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tester 
{
	public Tester()
	{
		
	}
	
	public void runAllTests() 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Method[] methods = Tester.class.getDeclaredMethods();
		
		for (Method m : methods)
		{
			if (m.isAnnotationPresent(TestMethod.class))
			{
				m.invoke(this);
			}
		}
	}

	@TestMethod
	public void assertException()
	{	
		System.out.println("assert Exception called");
	}
	
	@TestMethod
	public void assertTrue()
	{
		System.out.println("assert TRUE called");
	}
}
