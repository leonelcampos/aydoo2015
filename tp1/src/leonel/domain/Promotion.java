package domain;

import java.util.Date;
import java.util.List;

public interface Promotion {

	boolean isAppropiateForUser(User user, Date date);
	
	Date getStartDate();

	void setStartDate(Date startDate);

	Date getEndDate();

	void setEndDate(Date endDate);

	List<Attraction> getAttractions();

	double getCost();

	double getTotalAverageTime();

	boolean isAvailable(Date date);
	
	
}
