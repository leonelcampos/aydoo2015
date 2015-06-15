package domain;

import java.util.Date;
import java.util.List;

public class PromocionFamiliar extends PromocionAcumulable{

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
	
	@Override
	public double aplicarCostoDePromocion(Date fecha,
			List<Atraccion> atracciones, double costoTotalAcumulado, Integer cantidadDeEntradas) {
		
		if(estaVigente(fecha)){
			if (cantidadDeEntradas == 4){		
				costoTotalAcumulado = costoTotalAcumulado - (costoTotalAcumulado*0.1);
				
			}else if(cantidadDeEntradas >4){
				double costoDeCuatroEntradas = calcularCostoDeCuatroEntradas(costoTotalAcumulado, cantidadDeEntradas);
				double descuentoACuatroEntradas = costoDeCuatroEntradas - (costoDeCuatroEntradas*0.1);
				int entradasConOtroDescuento = cantidadDeEntradas - 4;
				costoTotalAcumulado = descuentoACuatroEntradas + ((costoTotalAcumulado/cantidadDeEntradas)
								- ((costoTotalAcumulado/cantidadDeEntradas)*0.3))*entradasConOtroDescuento;
				
			}
		}
		
		return costoTotalAcumulado;
	}

	private double calcularCostoDeCuatroEntradas(double costoTotalAcumulado,
			Integer cantidadDeEntradas) {
		
		return ((4*costoTotalAcumulado)/cantidadDeEntradas);
	}

}
