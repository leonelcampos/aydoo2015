package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sugerencia {
	
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private double costoTotal; 
	
	public Sugerencia(List<Atraccion> atracciones){
		
		this.atracciones = atracciones;
	}


	public List<Atraccion> getAtracciones() {
		return atracciones;
	}
	
	public void addAtraccion(Atraccion attraction){
		atracciones.add(attraction);
	}

	public double getCostoTotal(List<Promocion> promociones, Date fecha) {
		return calcularCostoTotalConPromociones(atracciones, promociones, fecha);
	}


	public double getTiempoTotal(List<Atraccion> atracciones, double velocidadDelUsuario) {
		return calcularTiempoTotal(atracciones, velocidadDelUsuario);
	}

	private double calcularTiempoTotal(List<Atraccion> atracciones, double velocidadDelUsuario){
		double tiempoTotal = 0;
		for (Atraccion attraction : atracciones) {
			tiempoTotal += attraction.getTiempoPromedio();
		}
		tiempoTotal += calcularTiempoDeViaje(atracciones, velocidadDelUsuario);
		
		return tiempoTotal;
	}
	
	private double calcularTiempoDeViaje(List<Atraccion> atracciones, double velocidadDelUsuario){
		double distance = 0;
		for (int i= 0; i< atracciones.size()-1;i++) {
			
			if(atracciones.size()>1){
				distance += calcularDistancia(atracciones.get(i), atracciones.get(i+1));
			}
		}
		return distance/velocidadDelUsuario;
	}
	
	

	private double calcularDistancia(Atraccion atraccion1, Atraccion atraccion2){
		
		double x= Math.pow(atraccion1.getPosicionX() - atraccion2.getPosicionX(), 2);
		double y= Math.pow(atraccion1.getPosicionY() - atraccion2.getPosicionY(), 2);
		double distance= Math.sqrt(x+y);
		
		return distance;
		
	}
	
	public double calcularCostoTotalConPromociones(List<Atraccion> atracciones, List<Promocion> promociones, Date fecha) {
		double costo = calcularCostoAtracciones(atracciones);
		
		for (Promocion promocion : promociones) {		
			
			costo = promocion.aplicarCostoDePromocion(fecha, atracciones, costo);
		}
		
		return costo;
		
	}
	
	
	private double calcularCostoAtracciones(List<Atraccion> attractionsForItinerary){
		double cost = 0;
		for (Atraccion attraction : attractionsForItinerary) {
			cost += attraction.getCosto();
		}
		return cost;
	}
}
