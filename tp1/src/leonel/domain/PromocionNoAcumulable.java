package domain;

import java.util.Date;
import java.util.List;

public interface PromocionNoAcumulable {

	Date getFechaDeInicio();

	void setFechaDeInicio(Date fechaDeInicio);

	Date getFechaDeFinalizacion();

	void setFechaDeFinalizacion(Date fechaDeFinalizacion);

	double aplicarCostoDePromocion(Date fecha, List<Atraccion> atracciones);
}
