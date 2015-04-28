package domain;

public class Attraction {
	private int positionX;
	private int positionY;
	private double cost;
	private double averageTime;
	private AttractionType attractionType;
	private int share;
	
	public Attraction(int positionX, int positionY, double cost,
			double averageTime, int share,  AttractionType attractionType ) {

		this.positionX = positionX;
		this.positionY = positionY;
		this.cost = cost;
		this.averageTime = averageTime;
		this.share = share;
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

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public AttractionType getAttractionType() {
		return attractionType;
	}

	public void setAttractionType(AttractionType attractionType) {
		this.attractionType = attractionType;
	}
	
	
	
	
	
}
