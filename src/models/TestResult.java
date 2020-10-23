package models;

import enums.TestStatus;
import validation.ExceptionValidator;

public class TestResult 
{	
	private String testName;
	private TestStatus status;
	private String description;
	
	public TestResult(String testName, TestStatus status)
	{
		this.setTestName(testName);
		this.setStatus(status);
	}
	
	public TestResult(String testName, TestStatus status, String description)
	{
		this(testName, status);
		this.setDescription(description);
	}
	
	public String getTestName()
	{
		return this.testName;
	}
	
	public TestStatus getStatus()
	{
		return this.status;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	private void setTestName(String value)
	{
		ExceptionValidator.throwIfNullOrEmpty(value, "testName");
		this.testName = value;
	}
	
	private void setStatus(TestStatus value)
	{
		ExceptionValidator.throwIfNull(value, "status");
		this.status = value;
	}
	
	private void setDescription(String value)
	{
		ExceptionValidator.throwIfNullOrEmpty(value, "description");
		this.description = value;
	}
	
	public String toString()
	{	
		String result = this.getTestName() + "() -> " + this.getStatus();
		
		String descriptionValue = this.getDescription() == null ? 
										"N/A" : this.getDescription();
		
		result += "\n" + "description: " + descriptionValue; 
		
		return result;
	}
}
