package validation;

public class ExceptionValidator 
{
	public static void throwIfNull(Object value, String paramName)
	{	
		if (value == null)
		{
			String errorFormat = "%s cannot be null";
			String errorMessage = String.format(errorFormat, paramName); 
			throw new NullPointerException(errorMessage);
		}
	}
	
	public static void throwIfNullOrEmpty(String value, String paramName)
	{
		throwIfNull(value, paramName);
		
		if (value == GlobalConstants.EMPTY_STRING)
		{
			String errorFormat = "%s cannot be empty string";
			String errorMessage = String.format(errorFormat, paramName);
			throw new NullPointerException(errorMessage);
		}
	}
}
