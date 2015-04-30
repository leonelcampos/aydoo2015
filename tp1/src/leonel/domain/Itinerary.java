package domain;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
	
	private List<Attraction> attractions = new ArrayList<Attraction>();
	private double totalCost; 
	private double totalTime;
	
	public Itinerary(List<Attraction> attractions, double totalCost, double totalTime){
		
		this.attractions = attractions;
		this.totalCost = totalCost;
		this.totalTime = totalTime;
	}


	public List<Attraction> getAttractions() {
		return attractions;
	}
	
	public void addAttraction(Attraction attraction){
		attractions.add(attraction);
	}

	public double getTotalCost() {
		return this.totalCost;
	}


	public double getTotalTime() {
		return totalTime;
	}

	
}
