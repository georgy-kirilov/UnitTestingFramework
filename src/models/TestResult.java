package models;

import enums.TestStatus;
import validation.ExceptionValidator;

public class TestResult 
{
	// TODO: add description field 
	
	private String testName;
	private TestStatus status;
	
	public TestResult(String testName, TestStatus status)
	{
		this.setTestName(testName);
		this.setStatus(status);
	}
	
	public String getTestName()
	{
		return this.testName;
	}
	
	private void setTestName(String value)
	{
		ExceptionValidator.throwIfNullOrEmpty(value, "testName");
		this.testName = value;
	}
	
	public TestStatus getStatus()
	{
		return this.status;
	}
	
	private void setStatus(TestStatus value)
	{
		ExceptionValidator.throwIfNull(value, "status");
		this.status = value;
	}
}
