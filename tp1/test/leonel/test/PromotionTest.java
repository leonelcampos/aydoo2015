package test;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.AXBPromotion;
import domain.AbsolutePromotion;
import domain.Attraction;
import domain.AttractionType;
import domain.PercentagePromotion;
import domain.Promotion;

public class PromotionTest {
	
	@Test
	public void whenCreateAbsolutePromotionThenThisIsCreatedWhitDeterminatedCost() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion absolutePromotion = new AbsolutePromotion(attractions,startDate(), endDate(), 200);
		
		double costExpected = 200;
		
		Assert.assertEquals( costExpected, absolutePromotion.getCost(), 0.1);
			
	}
	
	@Test
	public void whenCreateAAXBPromotionThenThisIsCreatedWhitDeterminatedCost() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion absolutePromotion = new AXBPromotion(startDate(), endDate(), attractions, generateAtractionBonusForAxBPromotion());
		
		double costExpected = 1200;
		
		Assert.assertEquals( costExpected, absolutePromotion.getCost(), 0.1);
			
	}

	@Test
	public void whenCreateAPercentagePromotionThenThisIsCreatedWhitDeterminatedCost() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion percentagePromotion = new PercentagePromotion(startDate(), endDate(), 50, attractions);
		
		double costExpected = 600;
		
		Assert.assertEquals( costExpected, percentagePromotion.getCost(), 0.1);
			
	}
	
	private Attraction generateAtractionBonusForAxBPromotion(){
		
		
		Attraction skydiving = new Attraction(50, 20, 500, 8, 20, AttractionType.SKYDIVING);
		
		return skydiving;
	}
	
	private List<Attraction> generateAtractionsList() {
		List<Attraction> attractions = new ArrayList<Attraction>();
		
		Attraction camping = new Attraction(0, 5, 200, 24, 20, AttractionType.CAMPING);
		Attraction museum = new Attraction(20, 6, 500, 10, 20, AttractionType.MUSEUM);
		Attraction lanscape = new Attraction(27,  12, 500, 12, 50, AttractionType.LANSCAPE);
		
		attractions.add(lanscape);
		attractions.add(camping);
		attractions.add(museum);
		
		return attractions;
	}

	private Date endDate() {
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2016,2,10);
		
		return ahoraCal.getTime();
		
	}

	private Date startDate() {
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2015,10,10);
		
		return ahoraCal.getTime();
	}
	
	

}
