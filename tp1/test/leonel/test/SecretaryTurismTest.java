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
		List<Promotion> promotions = generatePromotionsList(attractions);
		
		SecretaryTurism tierraMedia = new SecretaryTurism(promotions, attractions, invalidDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = tierraMedia.getItineraries(user);
		
		Assert.assertEquals(2, itineraries.size());
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryIsCreatedByExpectedCost() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = generatePromotionsList(attractions);
		
		SecretaryTurism tierraMedia = new SecretaryTurism(promotions, attractions, invalidDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = tierraMedia.getItineraries(user);
		
		Assert.assertEquals(2000, itineraries.get(0).getTotalCost(), 0.1);
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryIsCreatedByFourAttractions() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = generatePromotionsList(attractions);
		
		SecretaryTurism tierraMedia = new SecretaryTurism(promotions, attractions, invalidDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = tierraMedia.getItineraries(user);
		
		Assert.assertEquals(4, itineraries.get(0).getAttractions().size());
		
	}
	
	@Test
	public void whenAskForItinerariesThenTwoItineraryIsCreatedByTwoAttractions() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = generatePromotionsList(attractions);
		
		SecretaryTurism tierraMedia = new SecretaryTurism(promotions, attractions, invalidDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = tierraMedia.getItineraries(user);
		
		Assert.assertEquals(2, itineraries.get(1).getAttractions().size());
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryHasAbsolutePromotionAvailableAndApply() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(createAbsolutePromotion(attractions));
		
		SecretaryTurism tierraMedia = new SecretaryTurism(promotions, attractions, validDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = tierraMedia.getItineraries(user);
		
		Assert.assertEquals(1700, itineraries.get(0).getTotalCost(), 0.1);
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryHasPercentagePromotionAvailableAndApply() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(createPercentagePromotion(attractions));
		
		SecretaryTurism tierraMedia = new SecretaryTurism(promotions, attractions, validDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = tierraMedia.getItineraries(user);
		
		Assert.assertEquals(1750, itineraries.get(0).getTotalCost(), 0.1);
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryHasAXBPromotionAvailableAndApply() {
		List<Attraction> attractions = generateAtractionsList() ;
		List<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(createAXBPromotion(attractions));
				
		SecretaryTurism tierraMedia = new SecretaryTurism(promotions, attractions, validDate());
		
		User user = new User(2000, 72, 20, AttractionType.CAMPING);
		
		List<Itinerary> itineraries = tierraMedia.getItineraries(user);
		
		Assert.assertEquals(1500, itineraries.get(0).getTotalCost(), 0.1);
	}

	
	private List<Promotion> generatePromotionsList(List<Attraction> attractions){
		
		Promotion absolutePromotion = createAbsolutePromotion(attractions);
 		Promotion percentagePromotion = createPercentagePromotion(attractions);
 		Promotion aXBPromotion = createAXBPromotion(attractions);
 		
 		List<Promotion> promotions = new ArrayList<Promotion>();
 		promotions.add(absolutePromotion);
 		promotions.add(percentagePromotion);
 		promotions.add(aXBPromotion);
 		
 		return promotions;
	}

	private AXBPromotion createAXBPromotion(List<Attraction> attractions) {
		List<Attraction> attractionsAXBPromotion = new ArrayList<Attraction>() ;
		attractionsAXBPromotion.add(attractions.get(1));
		attractionsAXBPromotion.add(attractions.get(3));
		
		return new AXBPromotion(startDate(), endDate(), attractionsAXBPromotion, attractions.get(2));
	}

	private PercentagePromotion createPercentagePromotion(
			List<Attraction> attractions) {
		List<Attraction> attractionsPercentagePromotion = new ArrayList<Attraction>() ;
		attractionsPercentagePromotion.add(attractions.get(2));
		attractionsPercentagePromotion.add(attractions.get(3));
		
		return new PercentagePromotion(startDate(), endDate(), 25, attractionsPercentagePromotion);
	}

	private AbsolutePromotion createAbsolutePromotion(
			List<Attraction> attractions) {
		List<Attraction> attractionsAbsolutePromotion = new ArrayList<Attraction>() ;
		attractionsAbsolutePromotion.add(attractions.get(2));
		attractionsAbsolutePromotion.add(attractions.get(3));
		
		return new AbsolutePromotion(attractionsAbsolutePromotion, startDate(), endDate(), 700);
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
