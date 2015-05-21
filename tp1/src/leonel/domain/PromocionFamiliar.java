package domain;

import java.util.Date;
import java.util.List;

public class PromocionFamiliar extends Promocion{

	private Date fechaInicio;
	
	private Date fechaFin;
	
	public PromocionFamiliar(Date fechaInicio, Date fechaFin){
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
	}
			
	@Override
	public Date getFechaDeInicio() {
		return fechaInicio;
	}

	@Override
	public void setFechaDeInicio(Date fechaDeInicio) {
		this.fechaInicio = fechaDeInicio;
		
	}

	@Override
	public Date getFechaDeFinalizacion() {
		return fechaFin;
	}

	@Override
	public void setFechaDeFinalizacion(Date fechaDeFinalizacion) {
		this.fechaFin = fechaDeFinalizacion;		
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
	
	public double aplicarCostoDePromocion(Date fecha,
			List<Atraccion> atracciones, double costoTotalAcumulado, int cantidadDeEntradas) {
		
		if(estaVigente(fecha)){
			if (cantidadDeEntradas == 4){		
				costoTotalAcumulado = costoTotalAcumulado*4 - (costoTotalAcumulado*4*10)/100;
				
			}else if(cantidadDeEntradas >4){
				double descuentoACuatroEntradas = costoTotalAcumulado*4 - (costoTotalAcumulado*4*0.1);
				int entradasConOtroDescuento = cantidadDeEntradas - 4;
				costoTotalAcumulado = descuentoACuatroEntradas + (costoTotalAcumulado - (costoTotalAcumulado*0.3))*entradasConOtroDescuento;
				
			}
		}else{
			costoTotalAcumulado = costoTotalAcumulado * cantidadDeEntradas;
		}
		
		return costoTotalAcumulado;
	}

}
