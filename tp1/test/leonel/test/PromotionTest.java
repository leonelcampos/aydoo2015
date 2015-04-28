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
import domain.User;

public class PromotionTest {

	@Test
	public void whenCreateAAbsolutePromotionThenIsAppropiateForUser() {
		List<Attraction> atractions = generateAtractionsList();
		Promotion absolutePromotion = new AbsolutePromotion(atractions, startDate(), endDate(), 1000);
		
		User juan = new User(3000, 72, 20, AttractionType.CAMPING);
		
		Assert.assertTrue(absolutePromotion.isAppropiateForUser(juan, validDate()));
			
	}
	
	
	@Test
	public void whenCreateAAbsolutePromotionThenPromottionIsExpired() {
		List<Attraction> attractions = generateAtractionsList();
		Promotion absolutePromotion = new AbsolutePromotion(attractions, startDate(), endDate(), 1000);
		
		User juan = new User(2000, 72, 20, AttractionType.CAMPING);
		
		Assert.assertFalse(absolutePromotion.isAppropiateForUser(juan, invalidDate()));
			
	}
	
	@Test
	public void whenCreateAAbsolutePromotionThenManyIsNotEnough() {
		List<Attraction> atractions = generateAtractionsList();
		Promotion absolutePromotion = new AbsolutePromotion(atractions, startDate(), endDate(), 1000);
		
		User juan = new User(300, 72, 20, AttractionType.CAMPING);
		
		Assert.assertFalse(absolutePromotion.isAppropiateForUser(juan, validDate()));		
	}
	
	@Test
	public void whenCreateAAxBPromotionThenIsAppropiateForUser() {
		List<Attraction> attractions = generateAtractionsList();
		Promotion aXBPromotion = new AXBPromotion(startDate(), endDate(), attractions, generateAtractionBonusForAxBPromotion());
		
		User juan = new User(3000, 72, 20, AttractionType.CAMPING);
		
		Assert.assertTrue(aXBPromotion.isAppropiateForUser(juan, validDate()));
			
	}
	
	@Test
	public void whenCreateAAxBPromotionThenManyIsNotEnough() {
		List<Attraction> attractions = generateAtractionsList();
		Promotion aXBPromotion = new AXBPromotion(startDate(), endDate(), attractions, generateAtractionBonusForAxBPromotion());
		
		User juan = new User(3000, 72, 20, AttractionType.CAMPING);
		
		Assert.assertFalse(aXBPromotion.isAppropiateForUser(juan, invalidDate()));
			
	}
	
	@Test
	public void whenCreateAAxBPromotionThenPromotionIsExpired() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion aXBPromotion = new AXBPromotion(startDate(), endDate(), attractions, generateAtractionBonusForAxBPromotion());
		
		User juan = new User(300, 72, 20, AttractionType.CAMPING);
		
		Assert.assertFalse(aXBPromotion.isAppropiateForUser(juan, validDate()));
			
	}
	
	@Test
	public void whenCreateAPercentagePromotionThenIsApropiateForUser() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion percentagePromotion = new PercentagePromotion(startDate(), endDate(), 50, attractions);
		
		User juan = new User(600, 72, 20, AttractionType.CAMPING);
		
		Assert.assertTrue(percentagePromotion.isAppropiateForUser(juan, validDate()));
			
	}
	
	@Test
	public void whenCreateAPercentagePromotionThenPromotionIsExpired() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion percentagePromotion = new PercentagePromotion(startDate(), endDate(), 50, attractions);
		
		User juan = new User(600, 72, 20, AttractionType.CAMPING);
		
		Assert.assertFalse(percentagePromotion.isAppropiateForUser(juan, invalidDate()));
			
	}
	
	@Test
	public void whenCreateAPercentagePromotionThenManyIsNotEnough() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion percentagePromotion = new PercentagePromotion(startDate(), endDate(), 50, attractions);
		
		User juan = new User(300, 72, 20, AttractionType.CAMPING);
		
		Assert.assertFalse(percentagePromotion.isAppropiateForUser(juan, validDate()));
			
	}
	
	@Test
	public void whenCreateAPercentagePromotionThenThisIsCreatedWhitDeterminatedCost() {
		
		List<Attraction> attractions = generateAtractionsList();
		Promotion percentagePromotion = new PercentagePromotion(startDate(), endDate(), 50, attractions);
		
		double costExpected = 600;
		
		Assert.assertEquals( costExpected, percentagePromotion.getCost(), 0.1);
			
	}
	
	private List<Attraction> generateAtractionBonusForAxBPromotion(){
		List<Attraction> attractions = new ArrayList<Attraction>();
		
		Attraction skydiving = new Attraction(50, 20, 500, 8, 20, AttractionType.SKYDIVING);
		attractions.add(skydiving);
		
		return attractions;
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
	
	private Date invalidDate(){
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2014,0,10);
		
		return ahoraCal.getTime();
	}
	
	private Date validDate(){
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2016,0,10);
		
		return ahoraCal.getTime();
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
