package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.Attraction;
import domain.AttractionType;
import domain.Itinerary;
import domain.User;

public class ItineraryTest {

	@Test
	public void test() {
		List<Attraction> attractions = generateAtractionsList();
		
		Itinerary itinerary = new Itinerary(attractions);
		User juan = new User(300, 72, 20, AttractionType.CAMPING);
		
		Assert.assertEquals(0.8, itinerary.calculateTravelTime(juan),0.1);
		
	}
	
	private List<Attraction> generateAtractionsList() {
		List<Attraction> attractions = new ArrayList<Attraction>();
		
		Attraction camping = new Attraction(0, 5, 200, 24, 20, AttractionType.CAMPING);
		Attraction museum = new Attraction(10, 6, 500, 10, 20, AttractionType.MUSEUM);
		Attraction lanscape = new Attraction(7,  12, 500, 12, 50, AttractionType.LANSCAPE);
		
		attractions.add(camping);
		attractions.add(museum);
		attractions.add(lanscape);
		
		return attractions;
	}

}
