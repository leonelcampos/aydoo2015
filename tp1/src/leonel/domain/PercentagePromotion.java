package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PercentagePromotion implements Promotion{

	
	private Date startDate;
	private Date endDate;
	private int percentage;
	private List<Attraction> attractions = new ArrayList<Attraction>();
	private double totalAverageTime;
	
	public PercentagePromotion(Date startDate, Date endDate, int percentage,
			List<Attraction> attractions) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.percentage = percentage;
		this.attractions = attractions;
	}
	
	public double getCost() {
		double cost = 0;
		cost = calculateCost(this.getAttractions());
		cost = cost- ((cost * percentage)/100);
		
		return cost;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public boolean isAvailable(Date date) {
		return (startDate.before(date))&&(endDate.after(date));
	}

	
	public double applyPromotion(Date date, List<Attraction> attractionsForCheck, double cost){
		if(isAvailable(date)){
			if (attractionsForCheck.containsAll(this.getAttractions())){
				cost = calculateCost(attractionsForCheck);
				cost = cost - calculateCost(this.getAttractions()) + this.getCost();
			}
		}else{
			cost = calculateCost(attractionsForCheck);
		}
		return cost;
	}
	
	private double calculateCost(List<Attraction> attractionsForItinerary){
		double cost = 0;
		for (Attraction attraction : attractionsForItinerary) {
			cost += attraction.getCost();
		}
		return cost;
	}
	
	
}
