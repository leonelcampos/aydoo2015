package domain;

public class User {
	
	private double many;
	private double availableTime;
	private double speed;
	private AttractionType favoriteAttraction;
	
	
	public User(double many, double availableTime, double speed,
			AttractionType favoriteAttraction) {

		this.many = many;
		this.availableTime = availableTime;
		this.speed = speed;
		this.favoriteAttraction = favoriteAttraction;
	}


	public double getMany() {
		return many;
	}


	public void setMany(double many) {
		this.many = many;
	}


	public double getAvailableTime() {
		return availableTime;
	}


	public void setAvailableTime(double availableTime) {
		this.availableTime = availableTime;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public AttractionType getFavoriteAttraction() {
		return favoriteAttraction;
	}


	public void setFavoriteAttraction(AttractionType favoriteAttraction) {
		this.favoriteAttraction = favoriteAttraction;
	}
	
	
	
}
