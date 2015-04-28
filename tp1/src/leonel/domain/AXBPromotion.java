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
	@Override
	public Date getStartDate() {
		return startDate;
	}
	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Override
	public Date getEndDate() {
		return endDate;
	}
	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public double getCost() {
		for (Attraction attraction : obligatoryAttractions) {
			cost += attraction.getCost();
		}
		
		return cost;
	}
	
	public List<Attraction> getObligatoryAttractions() {
		return obligatoryAttractions;
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
	@Override
	public double getTotalAverageTime() {
		for (Attraction attraction : obligatoryAttractions) {
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
		
		for (Attraction attraction : obligatoryAttractions) {
			if(attraction.getAttractionType().equals(favoriteAttraction)){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public List<Attraction> getAttractions() {
		List<Attraction> attractions = new ArrayList<Attraction>();
		attractions.addAll(obligatoryAttractions);
		attractions.addAll(bonusAttractions);
		
		return attractions;
	}
	
	
}