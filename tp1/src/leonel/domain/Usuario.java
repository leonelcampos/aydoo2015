package domain;

public class Usuario {
	
	private double dinero;
	private double tiempoDisponible;
	private double velocidad;
	private TipoDeAtraccion atraccionFavorita;
	private Posicion posicion;
	
	public Usuario(Posicion posicion, double many, double tiempoDisponible, double speed,
			TipoDeAtraccion favoriteAttraction) {
		
		this.posicion = posicion;
		this.dinero = many;
		this.tiempoDisponible = tiempoDisponible;
		this.velocidad = speed;
		this.atraccionFavorita = favoriteAttraction;
	}


	public double getDinero() {
		return dinero;
	}


	public void setDinero(double dinero) {
		this.dinero = dinero;
	}


	public double getTiempoDisponible() {
		return tiempoDisponible;
	}


	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}


	public double getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}


	public TipoDeAtraccion getAtraccionFavorita() {
		return atraccionFavorita;
	}


	public void setAtraccionFavorita(TipoDeAtraccion atraccionFavorita) {
		this.atraccionFavorita = atraccionFavorita;
	}


	public Posicion getPosicion() {
		return posicion;
	}


	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}


	
	
	
	
}
