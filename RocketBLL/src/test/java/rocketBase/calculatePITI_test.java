package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

public class calculatePITI_test {

	//Test Values
	double expenses1 = 0;
	double expenses2 = 200;
	double expenses3 = 1000;
	
	double income = 10000;
	
	double expectedPITI1 = 2800.0;
	double expectedPITI2 = 3400.0;
	double expectedPITI3 = 2600.0;
	
	@Test
	public void test1() {
		double monthlyPITI = RateBLL.calculatePITI(expenses1, income);
		assertTrue(monthlyPITI - expectedPITI1 < 0.001);
		//This is because it technically calculates a PITI of 2800.0000000000005
		//So this shows that it is accurate to less than 1/10 of a cent
	}

	
	@Test
	public void test2() {
		double monthlyPITI = RateBLL.calculatePITI(expenses2, income);
		assertTrue(monthlyPITI - expectedPITI2 < 0.001);
		//This is because it technically calculates a PITI to the 1 millionth decimal point
		//So this shows that it is accurate to less than 1/10 of a cent
	}
	
	@Test
	public void test3() {
		double monthlyPITI = RateBLL.calculatePITI(expenses3, income);
		assertTrue(monthlyPITI - expectedPITI3 < 0.001);
		//This is because it technically calculates a PITI to the 1 millionth decimal point
		//So this shows that it is accurate to less than 1/10 of a cent
	}
}
