package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AXBPromotion implements Promotion{

	private Date startDate;
	private Date endDate;
	private double cost;
	private List<Attraction> obligatoryAttractions = new ArrayList<Attraction>();
	private List<Attraction> bonusAttractions = new ArrayList<Attraction>();
	private double totalAverageTime;
	
	
	
	public AXBPromotion(Date startDate, Date endDate,
			List<Attraction> obligatoryAttractions,
			List<Attraction> bonusAttractions) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.obligatoryAttractions = obligatoryAttractions;
		this.bonusAttractions = bonusAttractions;
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

	public double getCost() {
		for (Attraction attraction : obligatoryAttractions) {
			cost += attraction.getCost();
		}
		
		return cost;
	}

	public List<Attraction> getObligatoryAttractions() {
		return obligatoryAttractions;
	}
	
	public List<Attraction> getAttractionsOfPromotions(){
		List<Attraction> attractions = new ArrayList<Attraction>();
		attractions.addAll(obligatoryAttractions);
		attractions.addAll(bonusAttractions);
		
		return attractions;
		
	}

	public void setObligatoryAttractions(List<Attraction> obligatoryAttractions) {
		this.obligatoryAttractions = obligatoryAttractions;
	}

	public List<Attraction> getBonusAttractions() {
		return bonusAttractions;
	}

	public void setBonusAttractions(List<Attraction> bonusAttractions) {
		this.bonusAttractions = bonusAttractions;
	}

	public double getTotalAverageTime() {
		for (Attraction attraction : obligatoryAttractions) {
			totalAverageTime += attraction.getAverageTime();
		}
		
		return totalAverageTime;
	}

	public void setTotalAverageTime(double totalAverageTime) {
		this.totalAverageTime = totalAverageTime;
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
		
		for (Attraction attraction : obligatoryAttractions) {
			if(attraction.getAttractionType().equals(favoriteAttraction)){
				return true;
			}
		}
		
		return false;
	}
	
}