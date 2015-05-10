package domain;

public class Atraccion {
	private int posicionX;
	private int posicionY;
	private double costo;
	private double tiempoPromedio;
	private tipoDeAtraccion tipoAtraccion;
	private int disponibilidad;
	
	public Atraccion(int posicionX, int posicionY, double costo,
			double tiempoPromedio, int disponibilidad,  tipoDeAtraccion tipoDeAtraccion ) {

		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.costo = costo;
		this.tiempoPromedio = tiempoPromedio;
		this.disponibilidad = disponibilidad;
		this.tipoAtraccion = tipoDeAtraccion;
	}

	public int getPosicionX() {
		return posicionX;
	}


	public int getPosicionY() {
		return posicionY;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double cost) {
		this.costo = cost;
	}

	public double getTiempoPromedio() {
		return tiempoPromedio;
	}

	public void setTiempoPromedio(double tiempoPromedio) {
		this.tiempoPromedio = tiempoPromedio;
	}

	public int getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public tipoDeAtraccion getTipoDeAtraccion() {
		return tipoAtraccion;
	}

	public void setTimpoDeAtraccion(tipoDeAtraccion tipoDeAtraccion) {
		this.tipoAtraccion = tipoDeAtraccion;
	}
	
	
	
	
	
}
