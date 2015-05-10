package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromocionAXB extends Promocion{

	private Date fechaDeInicio;
	private Date fechaDeFinalizacion;
	private List<Atraccion> atraccionesObligatorias;
	private Atraccion atraccionBonificada;
	
	
	
	public PromocionAXB(Date fechaDeInicio, Date fechaDeFinalizacion,
			List<Atraccion> atraccionesObligatorias,
			Atraccion atraccionBonificada) {
		
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		this.atraccionesObligatorias = atraccionesObligatorias;
		this.atraccionBonificada = atraccionBonificada;
	}



	@Override
	public Date getFechaDeInicio() {
		return fechaDeInicio;
	}



	@Override
	public void setFechaDeInicio(Date fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
		
	}



	@Override
	public Date getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}



	@Override
	public void setFechaDeFinalizacion(Date fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		
	}



	@Override
	public List<Atraccion> getAtracciones() {
		List<Atraccion> attractions = new ArrayList<Atraccion>();
		attractions.addAll(atraccionesObligatorias);
		attractions.add(atraccionBonificada);
		
		return attractions;
	}



	@Override
	public double getCostoPromocion() {
		int costo = 0;
		for (Atraccion attraction : atraccionesObligatorias) {
			costo += attraction.getCosto();
		}
		
		return costo;
	}



	@Override
	public boolean estaVigente(Date fecha) {
		return (fechaDeInicio.before(fecha))&&(fechaDeFinalizacion.after(fecha));
	}



	@Override
	public double aplicarCostoDePromocion(Date fecha,
			List<Atraccion> atracciones, double costoTotalAcumulado) {
		if(estaVigente(fecha)){
			if(atracciones.containsAll(atraccionesObligatorias)){
				if(atracciones.contains(atraccionBonificada)){
					
					costoTotalAcumulado = costoTotalAcumulado - atraccionBonificada.getCosto();
				}else{
					atracciones.add(atraccionBonificada);
				}
			}		
		}
		return costoTotalAcumulado;
	}
	

	
	public Atraccion getAtraccionBonificada() {
		return atraccionBonificada;
	}
	
}