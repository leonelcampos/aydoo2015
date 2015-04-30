package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbsolutePromotion implements Promotion{
	
	private Date startDate;
	private Date endDate;
	private double cost;
	private List<Attraction> attractions = new ArrayList<Attraction>();
	
	public AbsolutePromotion(List<Attraction> attractions, Date startDate, Date endDate, double cost) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.attractions = attractions;
		
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

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}


	public boolean isAvailable(Date date) {
		return (startDate.before(date))&&(endDate.after(date));
	}

	
	public double applyPromotion(Date date, List<Attraction> attractionsForCheck){
		double cost = 0;
		if(isAvailable(date)){
			if (attractionsForCheck.containsAll(this.getAttractions())){
				cost = calculateCost(attractionsForCheck);
				cost -= calculateCost(this.getAttractions()) + this.getCost();
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
