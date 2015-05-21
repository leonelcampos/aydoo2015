package domain;

public class Atraccion {
	private Posicion posicion;
	private double costo;
	private double tiempoPromedio;
	private TipoDeAtraccion tipoAtraccion;
	private int disponibilidad;
	
	public Atraccion(Posicion posicion, double costo,
			double tiempoPromedio, int disponibilidad,  TipoDeAtraccion tipoDeAtraccion ) {

		this.posicion = posicion;
		this.costo = costo;
		this.tiempoPromedio = tiempoPromedio;
		this.disponibilidad = disponibilidad;
		this.tipoAtraccion = tipoDeAtraccion;
	}

	public Posicion getPosicion(){
		return posicion;
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

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoAtraccion;
	}

	public void setTimpoDeAtraccion(TipoDeAtraccion tipoDeAtraccion) {
		this.tipoAtraccion = tipoDeAtraccion;
	}
	
	
	
	
	
}
