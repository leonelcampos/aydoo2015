package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecretaryTurism {
	
	List<Promotion> promotions;
	
	List<Attraction> attractions;
	
	Date date;

	public SecretaryTurism(List<Promotion> promotions,
			List<Attraction> attractions, Date date) {
		this.promotions = promotions;
		this.attractions = attractions;
		this.date = date;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public List<Itinerary> getItineraries(User user) {
		List<Itinerary> itineraries = new ArrayList<>();
		List<Attraction> attractionsForItinerary = new ArrayList<>();
		List<Attraction> attractionsAppropiateForUser = getAttractionsAppropiateForUser(user);
		
		
		for (int i= 0; i< attractionsAppropiateForUser.size(); i++) {
			if(verifiAttractionDisponibility(attractions.get(i))){
				if(verifyAttractionIsAppropiateForUser(attractionsForItinerary, attractions.get(i), user)){
					
					attractionsForItinerary.add(attractions.get(i));
				}else{	
					itineraries.add(createItinerary(attractionsForItinerary, user.getSpeed()));
				
				}
			}
		}
		return itineraries;
		
	}

	private Itinerary createItinerary(List<Attraction> attractionsForItinerary, double speed) {
		double cost = calculateTotalCostWithPromotions(attractionsForItinerary);
		double time = calculateTotalTime(attractionsForItinerary, speed);
		return new Itinerary(attractionsForItinerary, cost, time);
	}

	

	private List<Attraction> getAttractionsAppropiateForUser(User user) {
		List<Attraction> attractionsAppropiateForUser = new ArrayList<Attraction>();
		
		for (Attraction attraction : attractions) {
			if(verifiAttractionDisponibility(attraction) && verifyCost(user, attraction.getCost()) && verifyTime(user, attraction.getAverageTime())){
				attractionsAppropiateForUser.add(attraction);
			}
		}
		return attractionsAppropiateForUser;
		
	}

	private boolean verifyAttractionIsAppropiateForUser(
			List<Attraction> attractionsForItinerary, Attraction attraction,
			 User originUser) {
		
		boolean isAppropiateForUser = false;
		attractionsForItinerary.add(attraction);
		
		double totalCost = calculateTotalCostWithPromotions(attractionsForItinerary);
		double totalTime = calculateTotalTime(attractionsForItinerary, originUser.getSpeed());
		
		if(verifyCost(originUser, totalCost) && verifyTime(originUser, totalTime)){
			attractionsForItinerary.remove(attraction);
			isAppropiateForUser = true;
		}else{
			attractionsForItinerary.remove(attraction);
		}
			
		
		return isAppropiateForUser;
	
	}

	private boolean verifyTime(User user, double totalTime) {
		return user.getAvailableTime() >= totalTime;
	}

	private boolean verifyCost(User user, double totalCost) {
		return user.getMany() >= totalCost;
	}



	

	private double calculateTotalCostWithPromotions(List<Attraction> attractionsForItinerary) {
		double cost = 0;
		
		for (Promotion promotion : promotions) {		
			
			cost = promotion.applyPromotion(date, attractionsForItinerary);
		}
		
		return cost;
		
	}
	


	private boolean verifiAttractionDisponibility(Attraction attraction) {	
			return (attraction.getDisponibility() > 0);
	}
	
	private double calculateTotalTime(List<Attraction> attractionsForItinerary, double speed){
		double totalTime = 0;
		for (Attraction attraction : attractionsForItinerary) {
			totalTime += attraction.getAverageTime();
		}
		totalTime += calculateTravelTime(attractionsForItinerary, speed);
		
		return totalTime;
	}
	
	private double calculateTravelTime(List<Attraction> attractionsForItinerary, double speed){
		double distance = 0;
		for (int i= 0; i< attractionsForItinerary.size()-1;i++) {
			
			if(attractionsForItinerary.size()>1){
				distance += calculateDistance(attractionsForItinerary.get(i), attractionsForItinerary.get(i+1));
			}
		}
		return distance/speed;
	}
	
	

	private double calculateDistance(Attraction attraction1, Attraction attraction2){
		
		double x= Math.pow(attraction1.getPositionX() - attraction2.getPositionX(), 2);
		double y= Math.pow(attraction1.getPositionY() - attraction2.getPositionY(), 2);
		double distance= Math.sqrt(x+y);
		
		return distance;
		
	}

	
	
	
	
	
}
