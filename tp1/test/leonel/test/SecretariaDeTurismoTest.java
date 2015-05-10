package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.PromocionAXB;
import domain.PromocionAbsoluta;
import domain.Atraccion;
import domain.tipoDeAtraccion;
import domain.Sugerencia;
import domain.PromocionPorcentual;
import domain.Promocion;
import domain.SecretariaDeTurismo;
import domain.Usuario;

public class SecretariaDeTurismoTest {

	@Test
	public void cuandoSeGeneranLasSugerenciasEntoncesLaSecretariaDeTurismoDevuelveDos() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<Promocion> promociones = generarListaDePromociones(atracciones);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, atracciones);
		
		Usuario usuario = new Usuario(2000, 72, 20, tipoDeAtraccion.ACAMPAR);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(2, sugerencias.size());
		
	}
	
	@Test
	public void cuandoSeGeneranLasSugerenciasEntoncesEstasSeCreanConUnCostoDeterminado() {
		List<Atraccion> atraciones = generarListaDeAtracciones() ;
		List<Promocion> promociones = generarListaDePromociones(atraciones);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, atraciones);
		
		Usuario usuario = new Usuario(2000, 72, 20, tipoDeAtraccion.ACAMPAR);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(2000, sugerencias.get(0).getCostoTotal(promociones, fechaNoVigente()), 0.1);
		
	}
	
	@Test
	public void cuandoSeGeneraLaPrimerSugerenciaEstaEsCreadoConCuatroAtraciones() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<Promocion> promociones = generarListaDePromociones(atracciones);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, atracciones);
		
		Usuario usuario = new Usuario(2000, 72, 20, tipoDeAtraccion.ACAMPAR);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(4, sugerencias.get(0).getAtracciones().size());
		
	}
	
	@Test
	public void cuandoSeGeneraLaSegundaSugerenciaEstaEsCreadoConDosAtreacciones() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<Promocion> promociones = generarListaDePromociones(atracciones);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, atracciones);
		
		Usuario usuario = new Usuario(2000, 72, 20, tipoDeAtraccion.ACAMPAR);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(2, sugerencias.get(1).getAtracciones().size());
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryHasAbsolutePromotionAvailableAndApply() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<Promocion> promociones = new ArrayList<Promocion>();
		promociones.add(crearPromocionAbsoluta(atracciones));
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, atracciones);
		
		Usuario usuario = new Usuario(2000, 72, 20, tipoDeAtraccion.ACAMPAR);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(1700, sugerencias.get(0).getCostoTotal(promociones, fechaVigente()), 0.1);
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryHasPercentagePromotionAvailableAndApply() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<Promocion> promociones = new ArrayList<Promocion>();
		promociones.add(crearPromocionPorcentual(atracciones));
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, atracciones);
		
		Usuario usuario = new Usuario(2000, 72, 20, tipoDeAtraccion.ACAMPAR);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(1750, sugerencias.get(0).getCostoTotal(promociones, fechaVigente()), 0.1);
		
	}
	
	@Test
	public void whenAskForItinerariesThenOneItineraryHasAXBPromotionAvailableAndApply() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<Promocion> promociones = new ArrayList<Promocion>();
		promociones.add(crearPromocionAXB(atracciones));
				
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, atracciones);
		
		Usuario usuario = new Usuario(2000, 72, 20, tipoDeAtraccion.ACAMPAR);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(1500, sugerencias.get(0).getCostoTotal(promociones, fechaVigente()), 0.1);
	}

	
	private List<Promocion> generarListaDePromociones(List<Atraccion> atracciones){
		
		Promocion promocionAbsoluta = crearPromocionAbsoluta(atracciones);
 		Promocion promocionPorcentual = crearPromocionPorcentual(atracciones);
 		Promocion PromocionAxB = crearPromocionAXB(atracciones);
 		
 		List<Promocion> promociones = new ArrayList<Promocion>();
 		promociones.add(promocionAbsoluta);
 		promociones.add(promocionPorcentual);
 		promociones.add(PromocionAxB);
 		
 		return promociones;
	}

	private PromocionAXB crearPromocionAXB(List<Atraccion> atracciones) {
		List<Atraccion> atraccionesDePromocionAXB = new ArrayList<Atraccion>() ;
		atraccionesDePromocionAXB.add(atracciones.get(1));
		atraccionesDePromocionAXB.add(atracciones.get(3));
		
		return new PromocionAXB(fechaDeInicio(), fechaDeFin(), atraccionesDePromocionAXB, atracciones.get(2));
	}

	private PromocionPorcentual crearPromocionPorcentual(
			List<Atraccion> atracciones) {
		List<Atraccion> atraccionesDePromocionPorcentual = new ArrayList<Atraccion>() ;
		atraccionesDePromocionPorcentual.add(atracciones.get(2));
		atraccionesDePromocionPorcentual.add(atracciones.get(3));
		
		return new PromocionPorcentual(fechaDeInicio(), fechaDeFin(), 25, atraccionesDePromocionPorcentual);
	}

	private PromocionAbsoluta crearPromocionAbsoluta(
			List<Atraccion> atracciones) {
		List<Atraccion> atraccionesDePromocionAbsoluta = new ArrayList<Atraccion>() ;
		atraccionesDePromocionAbsoluta.add(atracciones.get(2));
		atraccionesDePromocionAbsoluta.add(atracciones.get(3));
		
		return new PromocionAbsoluta(atraccionesDePromocionAbsoluta, fechaDeInicio(), fechaDeFin(), 700);
	}
	
	
	
	private List<Atraccion> generarListaDeAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		Atraccion acampar = new Atraccion(0, 5, 200, 24, 20, tipoDeAtraccion.ACAMPAR);
		Atraccion oaisaje = new Atraccion(5, 9, 800, 12, 15, tipoDeAtraccion.PAISAJE);
		Atraccion museoDeArte = new Atraccion(10, 6, 500, 10, 20, tipoDeAtraccion.MUSEO);
		Atraccion paracaidismo = new Atraccion(7,  12, 500, 12, 50, tipoDeAtraccion.PARACAIDISMO);
		Atraccion aventura = new Atraccion(13, 11, 700, 7, 20, tipoDeAtraccion.AVENTURA);
		Atraccion museoNatural = new Atraccion(40, 7, 400, 7, 20, tipoDeAtraccion.MUSEO);
		
		atracciones.add(acampar);
		atracciones.add(oaisaje);
		atracciones.add(museoDeArte);
		atracciones.add(paracaidismo);
		atracciones.add(aventura);
		atracciones.add(museoNatural);
		
		return atracciones;
	}
	
	private Date fechaNoVigente(){
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2014,0,10);
		
		return ahoraCal.getTime();
	}
	
	private Date fechaVigente(){
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2016,0,10);
		
		return ahoraCal.getTime();
	}

	private Date fechaDeFin() {
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2016,2,10);
		
		return ahoraCal.getTime();
		
	}

	private Date fechaDeInicio() {
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2015,10,10);
		
		return ahoraCal.getTime();
	}

}
