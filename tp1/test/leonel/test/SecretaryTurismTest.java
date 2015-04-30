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
import domain.Itinerary;
import domain.PercentagePromotion;
import domain.Promotion;
import domain.SecretaryTurism;
import domain.User;

public class SecretaryTurismTest {

	@Test
	public void whenAskForItinerariesThenSecretaryTurismReturnTwo() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = generatePromotionsList();
		
		SecretaryTurism secretaryTurism = new SecretaryTurism(promotions, attractions, invalidDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = secretaryTurism.getItineraries(user);
		
		Assert.assertEquals(2, itineraries.size());
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryIsCreatedByExpectedCost() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = generatePromotionsList();
		
		SecretaryTurism secretaryTurism = new SecretaryTurism(promotions, attractions, invalidDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = secretaryTurism.getItineraries(user);
		
		Assert.assertEquals(2000, itineraries.get(0).getTotalCost(), 0.1);
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryIsCreatedByFourAttractions() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = generatePromotionsList();
		
		SecretaryTurism secretaryTurism = new SecretaryTurism(promotions, attractions, invalidDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = secretaryTurism.getItineraries(user);
		
		Assert.assertEquals(4, itineraries.get(0).getAttractions().size());
		
	}

	
	private List<Promotion> generatePromotionsList(){

		Attraction museumArt = new Attraction(10, 6, 500, 10, 20, AttractionType.MUSEUM);
		Attraction lanscape = new Attraction(7,  12, 500, 12, 50, AttractionType.LANSCAPE);
		Attraction adventure = new Attraction(13, 11, 700, 7, 20, AttractionType.ADVENTURE);
		Attraction museumNatural = new Attraction(40, 7, 400, 7, 20, AttractionType.MUSEUM);
		
		List<Attraction> attractionsAbsolutePromotion = new ArrayList<Attraction>() ;
		attractionsAbsolutePromotion.add(museumNatural);
		attractionsAbsolutePromotion.add(lanscape);
		
		List<Attraction> attractionsPercentagePromotion = new ArrayList<Attraction>() ;
		attractionsPercentagePromotion.add(lanscape);
		attractionsPercentagePromotion.add(adventure);
		
		List<Attraction> attractionsAXBPromotion = new ArrayList<Attraction>() ;
		attractionsAXBPromotion.add(museumNatural);
		attractionsAXBPromotion.add(lanscape);
		
		Promotion absolutePromotion = new AbsolutePromotion(attractionsAbsolutePromotion, startDate(), endDate(), 700);
 		Promotion percentagePromotion = new PercentagePromotion(startDate(), endDate(), 25, attractionsPercentagePromotion);
 		Promotion aXBPromotion = new AXBPromotion(startDate(), endDate(), attractionsAXBPromotion, museumArt);
 		
 		List<Promotion> promotions = new ArrayList<Promotion>();
 		promotions.add(absolutePromotion);
 		promotions.add(percentagePromotion);
 		promotions.add(aXBPromotion);
 		
 		return promotions;
	}
	
	
	
	private List<Attraction> generateAtractionsList() {
		List<Attraction> attractions = new ArrayList<Attraction>();
		
		Attraction camping = new Attraction(0, 5, 200, 24, 20, AttractionType.CAMPING);
		Attraction skydiving = new Attraction(5, 9, 800, 12, 15, AttractionType.SKYDIVING);
		Attraction museumArt = new Attraction(10, 6, 500, 10, 20, AttractionType.MUSEUM);
		Attraction lanscape = new Attraction(7,  12, 500, 12, 50, AttractionType.LANSCAPE);
		Attraction adventure = new Attraction(13, 11, 700, 7, 20, AttractionType.ADVENTURE);
		Attraction museumNatural = new Attraction(40, 7, 400, 7, 20, AttractionType.MUSEUM);
		
		attractions.add(camping);
		attractions.add(skydiving);
		attractions.add(museumArt);
		attractions.add(lanscape);
		attractions.add(adventure);
		attractions.add(museumNatural);
		
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
