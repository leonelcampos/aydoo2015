package domain;

import java.util.ArrayList;
import java.util.List;

public class Sugerencia {
	
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private double costoXPersona; 
	private double tiempoTotal;
	private double costoXCantidadDeEntradas;
	
	public Sugerencia(List<Atraccion> atracciones, double tiempoTotal, double costoXPersona, double costoXCantidadDeEntradas){
		this.atracciones = atracciones;
		this.costoXPersona = costoXPersona;
		this.tiempoTotal = tiempoTotal;
		this.costoXCantidadDeEntradas = costoXCantidadDeEntradas;
	}
	
	public double getCostoXCantidadDeEntradas() {
		return costoXCantidadDeEntradas;
	}

	public void setCostoXCantidadDeEntradas(double costoXCantidadDeEntradas) {
		this.costoXCantidadDeEntradas = costoXCantidadDeEntradas;
	}




	public List<Atraccion> getAtracciones() {
		return atracciones;
	}
	
	public void addAtraccion(Atraccion atraccion){
		atracciones.add(atraccion);
	}
	

	public double getCostoXPersona() {
		return costoXPersona;
	}


	public void setCostoXPersona(double costoXPersona) {
		this.costoXPersona = costoXPersona;
	}


	public void setTiempoTotal(double tiempoTotal) {
		
		this.tiempoTotal = tiempoTotal;
	}

	public double getTiempoTotal(){
		return this.tiempoTotal;
	}
	
	

}
