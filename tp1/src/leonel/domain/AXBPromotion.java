package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AXBPromotion implements Promotion{

	private Date startDate;
	private Date endDate;
	private double cost;
	private List<Attraction> obligatoryAttractions;
	private Attraction bonusAttraction;
	
	
	
	public AXBPromotion(Date startDate, Date endDate,
			List<Attraction> obligatoryAttractions,
			Attraction bonusAttractions) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.obligatoryAttractions = obligatoryAttractions;
		this.bonusAttraction = bonusAttractions;
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
	
	public Attraction getBonusAttraction() {
		return bonusAttraction;
	}
	

	public boolean isAvailable(Date date) {
		return (startDate.before(date))&&(endDate.after(date));
	}

	
	
	@Override
	public List<Attraction> getAttractions() {
		List<Attraction> attractions = new ArrayList<Attraction>();
		attractions.addAll(obligatoryAttractions);
		attractions.add(bonusAttraction);
		
		return attractions;
	}
	
	public double applyPromotion(Date date, List<Attraction> attractionsForCheck, double cost){
		if(isAvailable(date)){
			if(attractionsForCheck.containsAll(this.getObligatoryAttractions())){
				cost = calculateCost(attractionsForCheck);
				
				if(attractionsForCheck.contains(this.getBonusAttraction())){
					
					cost = cost - this.getBonusAttraction().getCost();
				}else{
					attractionsForCheck.add(this.getBonusAttraction());
				}
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