package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Itinerary {
	
	List<Attraction> attractions;
	
	public Itinerary(List<Attraction> attractions){
		
		this.attractions = attractions;
		
	}


	public List<Attraction> getAttractions() {
		return attractions;
	}


	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}

	public double getTotalCost() {
		double totalCost = 0;
		for (Attraction attraction : attractions) {
			totalCost += attraction.getCost();
		}
		return totalCost;
	}

	public double getTotalTime(User user){
		double totalTime = 0;
		for (Attraction attraction : attractions) {
			totalTime += attraction.getAverageTime();
		}
		totalTime += calculateTravelTime(user);
		
		return totalTime;
	}
	
	public double calculateTravelTime(User user){
		double distance = 0;
		for (int i= 0; i< attractions.size()-1;i++) {
			
			if(attractions.size()>1){
				distance += calculateDistance(attractions.get(i), attractions.get(i+1));
			}
		}
		return distance/user.getSpeed();
	}
	
	

	private double calculateDistance(Attraction attraction1, Attraction attraction2){
		
		double x= Math.pow(attraction1.getPositionX() - attraction2.getPositionX(), 2);
		double y= Math.pow(attraction1.getPositionY() - attraction2.getPositionY(), 2);
		double distance= Math.sqrt(x+y);
		
		return distance;
		
	}
}
