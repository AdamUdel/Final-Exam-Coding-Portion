package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;
import rocketBase.RateBLL;

public class test_rate {

	//I renamed this file because there was another file called rate_test and I didnt want to confuse myself
	
	
	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	
	public int testCreditScore = 700;
	public double expectedRate = 4.0;
	@Test
	public void test_getRate() throws RateException {
		
		double rate = RateBLL.getRate(testCreditScore);
		assertTrue(rate == expectedRate);
		
	}
	
	@Test
	public void test_RateException(){
		int veryLowCreditScore = 0;
		try{
			double rate = RateBLL.getRate(veryLowCreditScore);
			fail("Expected a RateException, no exception thrown");
		}
		catch(Exception e){
			boolean isRateException = e instanceof RateException;
			assertTrue(isRateException);
		}
	}
	
}
