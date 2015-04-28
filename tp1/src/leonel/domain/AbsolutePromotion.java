package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbsolutePromotion implements Promotion{
	
	private Date startDate;
	private Date endDate;
	private double cost;
	private List<Attraction> attractions = new ArrayList<Attraction>();
	private double totalAverageTime;
	
	public AbsolutePromotion(List<Attraction> attractions, Date startDate, Date endDate, double cost) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.attractions = attractions;
		
		this.totalAverageTime = calculateTotalAverageTime();
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
	
	private double calculateTotalAverageTime(){
		double totalAverageTime = 0;
		
		for (Attraction attraction : attractions) {
			totalAverageTime += attraction.getAverageTime();
		}
		
		return totalAverageTime;
	}
	
	private double getTotalAverageTime(){
		
		return totalAverageTime;
	}

	@Override
	public boolean isAppropiateForUser(User user, Date date) {
		
		if(isAvailable(date)){
			return(user.getMany() >= getCost()) &&
				(user.getAvailableTime() >= getTotalAverageTime()) &&
				(containsFavoriteAttractionType(user.getFavoriteAttraction()));
		}
		return false;
	}

	public boolean isAvailable(Date date) {
		return (startDate.before(date))&&(endDate.after(date));
	}

	private boolean containsFavoriteAttractionType(
			AttractionType favoriteAttraction) {
		
		for (Attraction attraction : attractions) {
			if(attraction.getAttractionType().equals(favoriteAttraction)){
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	
	
}
