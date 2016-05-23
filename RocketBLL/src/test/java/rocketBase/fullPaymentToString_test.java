package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

public class fullPaymentToString_test {

	//Method parameters: rate, pmt, monthlyPITI
	
	//Test Values:
	double rate_InvalidCreditScore = 0;
	double rate = 0.04;
	double pmt = 1500.0;
	double monthlyPITI = 1000.5;
	
	//Known Results
	String invalidCreditScoreMessage = "ERROR: Credit Score is invalid";
	String cannotAffordMortgageMessage = "ERROR: You cannot afford this Mortgage";
	String defaultMessage = "Payment is: $1500.00 at 4.0% interest";
	
	//First type of error
	@Test
	public void test_InvalidCreditScoreMessage() {
		String pmtMessage = RateBLL.fullPaymentToString(rate_InvalidCreditScore, pmt, monthlyPITI);
		assertTrue(pmtMessage == invalidCreditScoreMessage);
	}
	
	//Second Type of error
	@Test
	public void test_CannotAffordMortgageMessage(){
		String pmtMessage = RateBLL.fullPaymentToString(rate, pmt, monthlyPITI);
		assertTrue(pmtMessage == cannotAffordMortgageMessage);
	}
}
