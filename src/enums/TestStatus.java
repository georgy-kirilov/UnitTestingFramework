package enums;

public enum TestStatus 
{
	FAILED(0),
	PASSED(1);
	
	private final int value;
	
	private TestStatus(int value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		switch (this.value)
		{
			case 0: 
				return "Failed";
			case 1:
				return "Passed"; 
		}
		
		throw new RuntimeException("Invalid TestStatus enum value");
	}
}
