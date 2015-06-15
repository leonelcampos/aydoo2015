package domain;

import java.util.ArrayList;
import java.util.List;

public class Sugerencia {
	
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private double costoTotal; 
	private double tiempoTotal;
	
	public Sugerencia(List<Atraccion> atracciones, double tiempoTotal, double costoTotal){
		this.atracciones = atracciones;
		this.costoTotal = costoTotal;
		this.tiempoTotal = tiempoTotal;
	}
	
	public List<Atraccion> getAtracciones() {
		return atracciones;
	}
	
	public double getCostoTotal() {
		return costoTotal;
	}


	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}


	public void setTiempoTotal(double tiempoTotal) {
		
		this.tiempoTotal = tiempoTotal;
	}

	public double getTiempoTotal(){
		return this.tiempoTotal;
	}

	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}
	
}
