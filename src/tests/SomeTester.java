package tests;

import models.*;

public class SomeTester extends Tester
{	
	@TestMethod
	public void testAddition()
	{
		String description = "Result should be ten";
		int result = 5 + 5;
		
		this.assertTrue(result == 18, description);
		this.assertTrue(result == 10, description);
	}
	
	@TestMethod
	public void testSubtraction()
	{
		String description = "Result should be 0";
		int result = 5 - 5;
		
		this.assertTrue(result == 0, description);
		this.assertTrue(result == 1);
	}
}
