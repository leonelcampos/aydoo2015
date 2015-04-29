package domain;

import java.util.List;

public class SecretaryTurism {
	
	List<Promotion> promotions;
	
	List<Attraction> attractions;

	public SecretaryTurism(List<Promotion> promotions,
			List<Attraction> attractions) {
		this.promotions = promotions;
		this.attractions = attractions;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	private void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	private void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}
	
	
	
	
}
