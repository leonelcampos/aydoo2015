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
	private double cost;
	
	public PercentagePromotion(Date startDate, Date endDate, int percentage,
			List<Attraction> attractions) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.percentage = percentage;
		this.attractions = attractions;
	}
	
	public double getCost() {
		for (Attraction attraction : attractions) {
			cost += attraction.getCost();
		}
		cost -= (cost * percentage)/100;
		
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

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}



	public double getTotalAverageTime() {
		for (Attraction attraction : attractions) {
			totalAverageTime += attraction.getAverageTime();
		}
		
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
