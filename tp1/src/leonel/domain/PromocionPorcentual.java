package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromocionPorcentual extends Promocion{

	
	private Date fechaDeInicio;
	private Date fechaDeFinalizacion;
	private int porcentaje;
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	
	public PromocionPorcentual(Date fechaDeInicio, Date fechaDeFinalizacion, int porcentaje,
			List<Atraccion> atracciones) {
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		this.porcentaje = porcentaje;
		this.atracciones = atracciones;
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
		return atracciones;
	}

	@Override
	public double getCostoPromocion() {
		double cost = calcularCosto(this.getAtracciones());
		cost = cost- ((cost * porcentaje)/100);
		
		return cost;
	}

	@Override
	public boolean estaVigente(Date fecha) {
		return (fechaDeInicio.before(fecha))&&(fechaDeFinalizacion.after(fecha));
	}
	
}