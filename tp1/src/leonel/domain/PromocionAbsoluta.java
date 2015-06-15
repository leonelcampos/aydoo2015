package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromocionAbsoluta extends PromocionAcumulable{
	
	private Date fechaDeInicio;
	private Date fechaDeFinalizacion;
	private double costo;
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	
	public PromocionAbsoluta(List<Atraccion> atracciones, Date fechaDeInicio, Date fechaDeFinalizacion, double costo) {
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		this.costo = costo;
		this.atracciones = atracciones;
		
	}

	public double getCostoPromocion() {
		return costo;
	}

	public Date getFechaDeInicio() {
		return fechaDeInicio;
	}

	public Date getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}

	public void setFechaDeFinalizacion(Date fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		
	}
	
	public void setFechaDeInicio(Date fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
		
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}
}
