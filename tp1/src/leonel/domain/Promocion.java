package domain;

import java.util.Date;
import java.util.List;

public abstract class Promocion {
	
	public abstract Date getFechaDeInicio();

	public abstract void setFechaDeInicio(Date fechaDeInicio);

	public abstract Date getFechaDeFinalizacion();

	public abstract void setFechaDeFinalizacion(Date fechaDeFinalizacion);

	public abstract List<Atraccion> getAtracciones();

	public abstract double getCostoPromocion();

	public boolean estaVigente(Date fecha) {
		return (getFechaDeInicio().before(fecha))&&(getFechaDeFinalizacion().after(fecha));
	}
	
	public double aplicarCostoDePromocion(Date fecha,
			List<Atraccion> atracciones, double costoTotalAcumulado) {
		
		if(estaVigente(fecha)){
			if (atracciones.containsAll(this.getAtracciones())){		
				costoTotalAcumulado = costoTotalAcumulado - calcularCosto(this.getAtracciones()) + this.getCostoPromocion();
			}
		}
		
		return costoTotalAcumulado;
	}
	
	public double calcularCosto(List<Atraccion> atracciones){
		double costo = 0;
		for (Atraccion attraction : atracciones) {
			costo += attraction.getCosto();
		}
		return costo;
	}
}
