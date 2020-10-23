package models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import enums.TestStatus;
import validation.ExceptionValidator;
import validation.GlobalConstants;

public abstract class Tester 
{	
	private Method currentlyInvokedTestMethod;
	
	private Map<String, ArrayList<TestResult>> testMethodsAndResultsPairs; 
	
	public Tester()
	{
		this.testMethodsAndResultsPairs = new HashMap<String, ArrayList<TestResult>>();
	}
	
	private Method getCurrentlyInvokedTestMethod()
	{
		return this.currentlyInvokedTestMethod;
	}
	
	private void setCurrentlyInvokedTestMethod(Method value)
	{
		ExceptionValidator.throwIfNull(value, "currentlyInvokedMethod");
		this.currentlyInvokedTestMethod = value;
	}
	
	protected void assertTrue(boolean value)
	{
		String methodName = this.getCurrentlyInvokedTestMethod().getName();
		boolean exists = this.testMethodsAndResultsPairs.containsKey(methodName);
		
		if (!exists)
		{
			this.testMethodsAndResultsPairs.put(methodName, new ArrayList<TestResult>());
		}
		
		TestStatus status = TestStatus.Failed;
		
		if (value)
		{
			status = TestStatus.Passed;			
		}
		
		this.testMethodsAndResultsPairs
					.get(methodName)
					.add(new TestResult("assertTrue", status));
	}
	
	public void runAllTests() 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		
		Method[] methods = this.getClass().getDeclaredMethods();
		
		for (Method method : methods)
		{	
			if (method.isAnnotationPresent(TestMethod.class))
			{
				this.setCurrentlyInvokedTestMethod(method);
				method.invoke(this);
			}
		}
	}
	
	public String toString()
	{
		String tabulationString = "- ";
		String result = this.getClass().getTypeName() + " tests results:" + 
						GlobalConstants.NEW_LINE;
		
		for (Map.Entry<String, ArrayList<TestResult>> entry :
						this.testMethodsAndResultsPairs.entrySet())
		{
			result += tabulationString + entry.getKey() + "()" + 
							GlobalConstants.NEW_LINE;
			
			for (TestResult testResult : entry.getValue())
				result += testResult;
			
			result += GlobalConstants.NEW_LINE;
		}
		
		return result;
	}
}
