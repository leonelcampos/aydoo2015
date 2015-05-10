package domain;

public class Usuario {
	
	private double dinero;
	private double tiempoDisponible;
	private double velocidad;
	private tipoDeAtraccion atraccionFavorita;
	
	
	public Usuario(double many, double tiempoDisponible, double speed,
			tipoDeAtraccion favoriteAttraction) {

		this.dinero = many;
		this.tiempoDisponible = tiempoDisponible;
		this.velocidad = speed;
		this.atraccionFavorita = favoriteAttraction;
	}


	public double getMany() {
		return dinero;
	}


	public void setMany(double many) {
		this.dinero = many;
	}


	public double getAvailableTime() {
		return tiempoDisponible;
	}


	public void setAvailableTime(double availableTime) {
		this.tiempoDisponible = availableTime;
	}


	public double getSpeed() {
		return velocidad;
	}


	public void setSpeed(double speed) {
		this.velocidad = speed;
	}


	public tipoDeAtraccion getFavoriteAttraction() {
		return atraccionFavorita;
	}


	public void setFavoriteAttraction(tipoDeAtraccion favoriteAttraction) {
		this.atraccionFavorita = favoriteAttraction;
	}
	
	
	
}
