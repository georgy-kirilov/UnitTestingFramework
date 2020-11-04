package models;

import enums.TestStatus;
import validation.ExceptionValidator;
import validation.GlobalConstants;

public class TestResult 
{	
	public static final String DESCRIPTION_DEFAULT_VALUE = "N/A";
	
	private String testName;
	private TestStatus status;
	private String description;
	
	public TestResult(String testName, TestStatus status)
	{
		this(testName, status, DESCRIPTION_DEFAULT_VALUE);
	}
	
	public TestResult(String testName, TestStatus status, String description)
	{
		this.setTestName(testName);
		this.setStatus(status);
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
		String tabulationString = "-- ";
		String separationString = " -> ";
		
		StringBuilder result = new StringBuilder();
		result.append(tabulationString);
		result.append(this.getTestName());
		result.append(separationString);
		result.append(this.getStatus());
		
		if (this.getStatus() == TestStatus.FAILED)
		{
			result.append(separationString);
			result.append(this.getDescription());
		}
		
		result.append(GlobalConstants.NEW_LINE);
		
		return result.toString();
	}
}
