package domain;

import java.util.Date;

public interface Promotion {


	boolean isAppropiateForUser(User user, Date date);
	
	
}
