package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

public class getPayment_test {

	//Given test values from the Final Coding Portion word doc
	public double pv = 300000;
	public double fv = 0;
	public double numOfPayments = 30;
	public int creditScore = 720;
	public int compounding = 0;
	public double truePMT = 1427.48;
	
	//Monthly income and otherLoan Test Values:
	public double monthlyIncome1 = 5200;
	public double otherLoans1 = 0;
	
	public double monthlyIncome2 = 2000;
	public double otherLoans2 = 200;
	
	@Test
	public void test_getPayment() throws Exception {
		double rate = RateBLL.getRate(creditScore);
		double pmt = RateBLL.getPayment(rate, numOfPayments, pv, fv, false);
		//Hard to test exact values for doubles, so testing to see if difference is less than one cent
		assertTrue(pmt - truePMT < 0.01);
	}
}
