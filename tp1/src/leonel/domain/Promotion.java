package domain;

import java.util.Date;
import java.util.List;

public interface Promotion {
	
	Date getStartDate();

	void setStartDate(Date startDate);

	Date getEndDate();

	void setEndDate(Date endDate);

	List<Attraction> getAttractions();

	double getCost();

	boolean isAvailable(Date date);
	
	double applyPromotion(Date date, List<Attraction> attractionsForCheck);
	
}
