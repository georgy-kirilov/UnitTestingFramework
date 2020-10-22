package models;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Program {

	public static void main(String[] args) 
			throws NoSuchMethodException, SecurityException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		// Get current method name
		String nameofCurrMethod = new Throwable() 
                .getStackTrace()[0]
                .getMethodName();

		System.out.println("Name of current method: "
				+ nameofCurrMethod); 
		
		Tester tester = new Tester();
		tester.runAllTests();
		
		/*
		Method[] methods = Tester.class.getDeclaredMethods();
		
		for (Method m : methods)
		{	
			Annotation[] annotations = m.getAnnotations();
			System.out.println(annotations.length);
			for (Annotation a : annotations)
			{
				if (a.annotationType() == TestMethod.class)
				{
					m.invoke(tester);					
				}
			}
		}*/

	}

}
