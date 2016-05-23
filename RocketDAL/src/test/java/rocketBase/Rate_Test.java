package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	@Test
	public void testSize() {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		
		//Makes sure that all rows of the table transfered over
		assertTrue(rates.size() > 0);
	}	
	
	@Test
	public void testifInstanceofRateDomainModel(){
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		//Makes sure that the elements transfered over as type RateDomainModel
		assertTrue(rates.get(0) instanceof RateDomainModel);
		
	}
}
