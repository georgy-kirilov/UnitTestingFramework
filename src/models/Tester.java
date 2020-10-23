package models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import enums.TestStatus;
import validation.ExceptionValidator;
import validation.GlobalConstants;

public abstract class Tester 
{	
	private static final String ASSERT_TRUE_METHOD_NAME = "assertTrue";
	
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
		ExceptionValidator.throwIfNull(value, "currentlyInvokedTestMethod");
		this.currentlyInvokedTestMethod = value;
	}
	
	private String methodName()
	{
		ExceptionValidator.throwIfNull
			(this.getCurrentlyInvokedTestMethod(), "currentlyInvokedTestMethod");
	
		return this.getCurrentlyInvokedTestMethod().getName();
	}
	
	private boolean doesMethodNameExist()
	{
		boolean exists = this.testMethodsAndResultsPairs
							 .containsKey(this.methodName());
		return exists;
	}
	
	private void ensureMethodNameExists()
	{
		if (!this.doesMethodNameExist())
		{
			this.testMethodsAndResultsPairs
				.put(this.methodName(), new ArrayList<TestResult>());
		}
	}
	
	private void addTestResult(String testName, TestStatus status, String description)
	{
		this.testMethodsAndResultsPairs
			.get(this.methodName())
			.add(new TestResult(ASSERT_TRUE_METHOD_NAME, status, description));
	}
	
	protected void assertTrue(boolean value)
	{	
		this.ensureMethodNameExists();
		
		TestStatus status = value ? TestStatus.Passed : TestStatus.Failed;
		
		this.addTestResult(this.methodName(), status, 
					TestResult.DESCRIPTION_DEFAULT_VALUE);
	}
	
	protected void assertTrue(boolean value, String description)
	{
		this.ensureMethodNameExists();
		
		TestStatus status = value ? TestStatus.Passed : TestStatus.Failed;
		
		this.addTestResult(this.methodName(), status, description);
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
