package domain;

public class Attraction {
	private int positionX;
	private int positionY;
	private double cost;
	private double averageTime;
	private AttractionType attractionType;
	private int disponibility;
	
	public Attraction(int positionX, int positionY, double cost,
			double averageTime, int disponibility,  AttractionType attractionType ) {

		this.positionX = positionX;
		this.positionY = positionY;
		this.cost = cost;
		this.averageTime = averageTime;
		this.disponibility = disponibility;
		this.attractionType = attractionType;
	}

	public int getPositionX() {
		return positionX;
	}


	public int getPositionY() {
		return positionY;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(double averageTime) {
		this.averageTime = averageTime;
	}

	public int getDisponibility() {
		return disponibility;
	}

	public void setDisponibility(int disponibility) {
		this.disponibility = disponibility;
	}

	public AttractionType getAttractionType() {
		return attractionType;
	}

	public void setAttractionType(AttractionType attractionType) {
		this.attractionType = attractionType;
	}
	
	
	
	
	
}
