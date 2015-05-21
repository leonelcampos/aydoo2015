package domain;

import java.util.Date;
import java.util.List;

public class PromocionExtranjero extends Promocion{


	private Date fechaDeInicio;
	private Date fechaDeFinalizacion;
	
	public PromocionExtranjero(Date fechaDeInicio, Date fechaDeFinalizacion){
		
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		
	}
	
	@Override
	public Date getFechaDeInicio() {
		return this.fechaDeInicio;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCostoPromocion() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public double aplicarCostoDePromocion(Date fecha, List<Atraccion> atracciones) {
		
		double costoTotal = calcularCosto(atracciones);
		
		if(estaVigente(fecha)){
			
			costoTotal = costoTotal - (costoTotal*0.5);
			
		}
		
		return costoTotal;
		
	}
}
