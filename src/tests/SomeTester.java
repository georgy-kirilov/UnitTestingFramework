package tests;

import models.*;

public class SomeTester extends Tester
{	
	@TestMethod
	public void testAddition()
	{
		int result = 5 + 5;
		
		this.assertTrue(result == 18);
		this.assertTrue(result == 10);
	}
	
	@TestMethod
	public void testSubtraction()
	{
		int result = 5 - 5;
		
		this.assertTrue(result == 0);
		this.assertTrue(result == 1);
	}
}
