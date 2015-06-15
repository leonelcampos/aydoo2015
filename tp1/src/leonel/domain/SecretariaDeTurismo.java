package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecretariaDeTurismo {

	private List<PromocionAcumulable> promocionesAcumulables;

	private List<Atraccion> atracciones;

	private PromocionNoAcumulable promocionExtranjero;

	public SecretariaDeTurismo(List<PromocionAcumulable> promocionesAcumulables,
			PromocionNoAcumulable promocionNoAcumulable, List<Atraccion> atracciones) {
		this.promocionesAcumulables = promocionesAcumulables;
		this.atracciones = atracciones;
		this.promocionExtranjero = promocionNoAcumulable;
	}

	public List<PromocionAcumulable> getPromociones() {
		return promocionesAcumulables;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Sugerencia> generarSugerencias(Usuario usuario, Date fecha) {

		List<Sugerencia> sugerencias = new ArrayList<>();
		List<Atraccion> atraccionesFiltradasParaElUsuario = getAtraccionesApropiadasParaElUsuario(
				usuario, usuario.getGrupoFamiliar());

		sugerencias = obtenerListaDeSugerenciasAjustadasAlUsuario(usuario,
				atraccionesFiltradasParaElUsuario);

		completarTiempoYCostoASugerencias(sugerencias, usuario);
		
		if (verificarUsuarioExtranjero(usuario)) {
				aplicarPromocionExtranjero(sugerencias, usuario.getGrupoFamiliar(),fecha); 
		} else {
				aplicarPromocionesAcumulables(sugerencias, usuario.getGrupoFamiliar(), fecha);
		}
		return sugerencias;
	}

	private boolean verificarUsuarioExtranjero(Usuario usuario) {
		double distanciaAtraccionMasCercana = obtenerDistanciaAAtraccionMasCercana(usuario.getPosicion());
		if(distanciaAtraccionMasCercana > 200){
			return true;
		}else{
			return false;
		}
	}

	private void aplicarPromocionExtranjero(List<Sugerencia> sugerencias,
			int cantidadDeEntradas, Date fecha) {
		double costo;
		for (Sugerencia sugerencia : sugerencias) {
			costo = promocionExtranjero.aplicarCostoDePromocion(fecha,
					sugerencia.getAtracciones());
			sugerencia.setCostoTotal(costo*cantidadDeEntradas);
		}
	}

	private void aplicarPromocionesAcumulables(List<Sugerencia> sugerencias,
			int cantidadDeEntradas, Date fecha) {
		double costo = 0;
		
		for (Sugerencia sugerencia : sugerencias) {
			costo = sugerencia.getCostoTotal();

			for (PromocionAcumulable promocion : promocionesAcumulables) {

				costo = promocion.aplicarCostoDePromocion(fecha,
						sugerencia.getAtracciones(), costo, cantidadDeEntradas);
			}
			sugerencia.setCostoTotal(costo);
		}
	}

	private void completarTiempoYCostoASugerencias(
			List<Sugerencia> sugerencias, Usuario usuario) {

		double costo = 0;
		double tiempo = 0;
		for (Sugerencia sugerencia : sugerencias) {

			costo = calcularCostoAtracciones(sugerencia.getAtracciones())* usuario.getGrupoFamiliar();
			tiempo = calcularTiempoTotal(sugerencia.getAtracciones(), usuario);

			sugerencia.setCostoTotal(costo);
			sugerencia.setTiempoTotal(tiempo);
		}

	}

	private List<Sugerencia> obtenerListaDeSugerenciasAjustadasAlUsuario(
			Usuario usuario, List<Atraccion> atraccionesFiltradasParaElUsuario) {
		List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		Sugerencia sugerencia = new Sugerencia(new ArrayList<Atraccion>(), 0,0);
		Atraccion atraccion = null;

		for (int i = 0; i < atraccionesFiltradasParaElUsuario.size(); i++) {
			atraccion = atraccionesFiltradasParaElUsuario.get(i);
			if(verificarAtraccionesApropiadasParaElUsuario(atracciones, atraccion, usuario)){
				atracciones.add(atraccion);
			}else{
				sugerencia.setAtracciones(atracciones);
				sugerencias.add(sugerencia);
				sugerencia = new Sugerencia(null, 0, 0);
				atracciones = new ArrayList<Atraccion>();
				atracciones.add(atraccion);
			}
		}
		
		sugerencia.setAtracciones(atracciones);
		sugerencias.add(sugerencia);

		return sugerencias;
	}

	private boolean verificarAtraccionesApropiadasParaElUsuario(
			List<Atraccion> atracciones, Atraccion atraccion, Usuario usuario) {
		
		List<Atraccion> atraccionesAEvaluar = new ArrayList<Atraccion>();
		atraccionesAEvaluar.addAll(atracciones);
		atraccionesAEvaluar.add(atraccion);
		
		double costoTotal = calcularCostoAtracciones(atraccionesAEvaluar);
		double tiempoTotal = calcularTiempoTotal(atraccionesAEvaluar, usuario);

		if (verificarCosto(usuario, costoTotal)
				&& verificarTiempo(usuario, tiempoTotal)) {
			return true;
		} else {
			return false;
		}
	}

	private List<Atraccion> getAtraccionesApropiadasParaElUsuario(Usuario user,
			int cantidadDeEntradas) {

		List<Atraccion> atraccionesApropiadasParaElUsuario = new ArrayList<Atraccion>();

		for (Atraccion attraction : atracciones) {
			if (verificarAtraccionesDisponibles(attraction, cantidadDeEntradas)
					&& verificarCosto(user, attraction.getCosto())
					&& verificarTiempo(user, attraction.getTiempoPromedio())) {
				atraccionesApropiadasParaElUsuario.add(attraction);
			}
		}
		return atraccionesApropiadasParaElUsuario;

	}

	private boolean verificarTiempo(Usuario user, double tiempoTotal) {
		return user.getTiempoDisponible() >= tiempoTotal;
	}

	private boolean verificarCosto(Usuario usuario, double costoTotal) {
		return usuario.getDinero() >= costoTotal*usuario.getGrupoFamiliar();
	}

	private boolean verificarAtraccionesDisponibles(Atraccion atraccion,
			int cantidadDeEntradas) {

		return ((atraccion.getDisponibilidad() - cantidadDeEntradas) > 0);
	}

	private double calcularCostoAtracciones(List<Atraccion> atracciones) {
		double cost = 0;
		for (Atraccion atraccion : atracciones) {
			cost += atraccion.getCosto();
		}
		return cost;
	}

	private double obtenerDistanciaAAtraccionMasCercana(Posicion posicionUsuario) {
		double distancia = 0;
		double siguienteDistancia = 0;
		for (int i = 0; i < this.atracciones.size(); i++) {
			if (i == 0) {
				distancia = calcularDistancia(posicionUsuario, this.atracciones.get(0)
						.getPosicion());
			} else {
				siguienteDistancia = calcularDistancia(posicionUsuario, this.atracciones
						.get(i).getPosicion());
				if (distancia > siguienteDistancia) {
					distancia = siguienteDistancia;
				}

			}
		}
		return distancia;
	}

	private double calcularTiempoTotal(List<Atraccion> atracciones,
			Usuario usuario) {
		double tiempoTotal = 0;
		for (Atraccion attraction : atracciones) {
			tiempoTotal += attraction.getTiempoPromedio();
		}
		tiempoTotal += calcularTiempoDeViaje(atracciones, usuario);

		return tiempoTotal;
	}

	private double calcularTiempoDeViaje(List<Atraccion> atracciones,
			Usuario usuario) {
		double distancia = 0;
		for (int i = 0; i < atracciones.size() - 1; i++) {

			if (i == 0) {
				distancia = calcularDistancia(usuario.getPosicion(),
						atracciones.get(i).getPosicion());
			} else {
				distancia += calcularDistancia(
						atracciones.get(i).getPosicion(), atracciones
								.get(i + 1).getPosicion());
			}
		}
		return distancia / usuario.getVelocidad();
	}

	private double calcularDistancia(Posicion posicion1, Posicion posicion2) {

		double x = Math.pow(
				posicion1.getPosicionX() - posicion2.getPosicionX(), 2);
		double y = Math.pow(
				posicion1.getPosicionY() - posicion2.getPosicionY(), 2);
		double distance = Math.sqrt(x + y);

		return distance;

	}

}
