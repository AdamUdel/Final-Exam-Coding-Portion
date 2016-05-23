package rocketBase;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;
import rocketData.LoanRequest;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		ArrayList<RateDomainModel> RDMList = RateDAL.getAllRates();
		//gets a list of all the rates in the forms of RateDomainModel
		
		double myRate = 0;
		for(int j = 0; j<RDMList.size(); j++){
			if(GivenCreditScore >= RDMList.get(j).getiMinCreditScore()){
				myRate = 0.01*RDMList.get(j).getdInterestRate(); //makes a percent
			}
		}
		
		if(myRate == 0){
			RateException noRate = new RateException(RDMList.get(0));
			return 0; //a rate of 0 means invalid credit score --> comes into play with display message on app
		}
		return myRate;
		
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)//, double monthlyIncome, double otherLoans) throws Exception
	{		
		
		//double maxPayment = getMaxPayment(monthlyIncome, otherLoans);
		double pmt = -1*(FinanceLib.pmt(r/12.0, 12.0*n, p, f, t));
		
		//if(maxPayment< pmt){
		//	Exception e = new Exception("User cannot afford to pay back this loan");
		//	throw(e);
		//}
		
		return pmt;
	}
	
	//calculates the monthly PITI - used to determine if a person can afford a loan due 
	public static double calculatePITI(double expenses, double income){
		double monthlyPITI = 0;
		if(expenses==0){
			monthlyPITI = income*0.28;
		}
		else{
			monthlyPITI = (income*0.36)-expenses;
		}
		return monthlyPITI;
	}
	
	//formulates the message that appears on the screen after the payment is calculated
	public static String fullPaymentToString(double rate, double pmt, double monthlyPITI){
		String rateAsPercentage = ((100*rate))+"%";
		String resultMessage;
		if(rate==0){
			resultMessage = "ERROR: Credit Score is invalid";
		}
		else if(monthlyPITI>=pmt){
			String payment = String.format("%.2f", pmt);
			resultMessage = "Payment is: $"+payment+" at "+rateAsPercentage+" interest";
		}
		else{
			resultMessage = "ERROR: You cannot afford this Mortgage";
		}
		return resultMessage;
	}
}

