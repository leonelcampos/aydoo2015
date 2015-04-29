package domain;

import java.util.List;

public class Itinerario {
	
	List<Attraction> attractions;
	
	public Itinerario(List<Attraction> attractions){
		
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

	
	
}
