package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecretariaDeTurismo {

	private List<Promocion> promociones;

	private List<Atraccion> atracciones;

	private PromocionExtranjero promocionExtranjero;

	private PromocionFamiliar promocionFamiliar;

	public SecretariaDeTurismo(List<Promocion> promociones,
			List<Atraccion> atracciones) {
		this.promociones = promociones;
		this.atracciones = atracciones;
		this.promocionFamiliar = obtenerPromocionFamiliarDePromociones();
		this.promocionExtranjero = obtenerPromocionExtranjeroDePromociones();
	}

	private PromocionExtranjero obtenerPromocionExtranjeroDePromociones() {
		PromocionExtranjero promocionExtranjero = null;

		for (Promocion promocion : promociones) {
			if (promocion instanceof PromocionExtranjero) {

				promocionExtranjero = (PromocionExtranjero) promocion;
			}
		}

		promociones.remove(promocionExtranjero);

		return promocionExtranjero;
	}

	private PromocionFamiliar obtenerPromocionFamiliarDePromociones() {

		PromocionFamiliar promocionFamiliar = null;

		for (Promocion promocion : promociones) {
			if (promocion instanceof PromocionFamiliar) {

				promocionFamiliar = (PromocionFamiliar) promocion;
			}
		}

		promociones.remove(promocionFamiliar);

		return promocionFamiliar;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Sugerencia> generarSugerencias(Usuario usuario, Date fecha,
			int cantidadDeEntradas) {

		List<Sugerencia> sugerencias = new ArrayList<>();
		List<Atraccion> atraccionesFiltradasParaElUsuario = getAtraccionesApropiadasParaElUsuario(
				usuario, cantidadDeEntradas);
		double distancia;

		sugerencias = obtenerListaDeSugerenciasAjustadasAlUsuario(usuario,
				atraccionesFiltradasParaElUsuario);

		completarTiempoYCostoASugerencias(sugerencias, usuario);

		for (Sugerencia sugerencia : sugerencias) {
			distancia = calcularAtraccionConMenorDistancia(
					sugerencia.getAtracciones(), usuario.getPosicion());

			if (distancia > 200) {
				aplicarPromocionExtranjero(sugerencia, cantidadDeEntradas,
						fecha);
			} else {
				aplicarPromociones(sugerencia, cantidadDeEntradas, fecha);
			}
		}

		return sugerencias;

	}

	private void aplicarPromocionExtranjero(Sugerencia sugerencia,
			int cantidadDeEntradas, Date fecha) {

		double costo = promocionExtranjero.aplicarCostoDePromocion(fecha,
				sugerencia.getAtracciones());

		sugerencia.setCostoXPersona(costo);
		sugerencia.setCostoXCantidadDeEntradas(cantidadDeEntradas * costo);
	}

	private void aplicarPromociones(Sugerencia sugerencia,
			int cantidadDeEntradas, Date fecha) {
		double costo = 0;

		costo = sugerencia.getCostoXPersona();

		for (Promocion promocion : promociones) {

			costo = promocion.aplicarCostoDePromocion(fecha,
					sugerencia.getAtracciones(), costo);
		}

		sugerencia.setCostoXPersona(costo);

		if (promocionFamiliar != null) {
			sugerencia.setCostoXCantidadDeEntradas(promocionFamiliar
					.aplicarCostoDePromocion(fecha, atracciones, costo,
							cantidadDeEntradas));
		} else {
			sugerencia.setCostoXCantidadDeEntradas(costo * cantidadDeEntradas);
		}

	}

	private void completarTiempoYCostoASugerencias(
			List<Sugerencia> sugerencias, Usuario usuario) {

		double costo = 0;
		double tiempo = 0;
		for (Sugerencia sugerencia : sugerencias) {

			costo = calcularCostoAtracciones(sugerencia.getAtracciones());
			tiempo = calcularTiempoTotal(sugerencia.getAtracciones(), usuario);

			sugerencia.setCostoXPersona(costo);
			sugerencia.setTiempoTotal(tiempo);

		}

	}

	private List<Sugerencia> obtenerListaDeSugerenciasAjustadasAlUsuario(
			Usuario usuario, List<Atraccion> atraccionesFiltradasParaElUsuario) {
		List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		Sugerencia sugerencia = new Sugerencia(new ArrayList<Atraccion>(), 0,
				0, 0);
		Atraccion atraccion = null;

		for (int i = 0; i < atraccionesFiltradasParaElUsuario.size(); i++) {
			atraccion = atracciones.get(i);
			sugerencia.addAtraccion(atraccion);

			sugerencia = generarSugerenciaAjustadaAlUsuario(usuario,
					sugerencia, sugerencias, atraccion);
		}
		sugerencias.add(sugerencia);

		return sugerencias;
	}

	private Sugerencia generarSugerenciaAjustadaAlUsuario(Usuario usuario,
			Sugerencia sugerencia, List<Sugerencia> sugerencias,
			Atraccion atraccion) {

		if (!verificarAtraccionesApropiadasParaElUsuario(sugerencia, usuario)) {

			sugerencia.getAtracciones().remove(atraccion);
			sugerencias.add(sugerencia);
			sugerencia = new Sugerencia(new ArrayList<Atraccion>(), 0, 0, 0);
			sugerencia.addAtraccion(atraccion);
		}

		return sugerencia;
	}

	private boolean verificarAtraccionesApropiadasParaElUsuario(
			Sugerencia sugerencia, Usuario usuario) {

		double costoTotal = calcularCostoAtracciones(sugerencia
				.getAtracciones());
		double tiempoTotal = calcularTiempoTotal(sugerencia.getAtracciones(),
				usuario);

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

	private boolean verificarTiempo(Usuario user, double totalTime) {
		return user.getTiempoDisponible() >= totalTime;
	}

	private boolean verificarCosto(Usuario user, double totalCost) {
		return user.getDinero() >= totalCost;
	}

	private boolean verificarAtraccionesDisponibles(Atraccion attraction,
			int cantidadDeEntradas) {

		return (attraction.getDisponibilidad() - cantidadDeEntradas > 0);
	}

	private double calcularCostoAtracciones(List<Atraccion> atracciones) {
		double cost = 0;
		for (Atraccion atraccion : atracciones) {
			cost += atraccion.getCosto();
		}
		return cost;
	}

	private double calcularAtraccionConMenorDistancia(
			List<Atraccion> atracciones, Posicion posicion) {
		double distancia = 0;
		double siguienteDistancia = 0;
		for (int i = 0; i < atracciones.size(); i++) {
			if (i == 0) {
				distancia = calcularDistancia(posicion, atracciones.get(0)
						.getPosicion());
			} else {
				siguienteDistancia = calcularDistancia(posicion, atracciones
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
