package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.Atraccion;
import domain.Posicion;
import domain.PromocionAXB;
import domain.PromocionAbsoluta;
import domain.PromocionAcumulable;
import domain.PromocionExtranjero;
import domain.PromocionFamiliar;
import domain.PromocionNoAcumulable;
import domain.PromocionPorcentual;
import domain.SecretariaDeTurismo;
import domain.Sugerencia;
import domain.TipoDeAtraccion;
import domain.Usuario;

public class SecretariaDeTurismoTest {

	@Test
	public void cuandoSeGeneranLasSugerenciasEntoncesLaSecretariaDeTurismoDevuelveDos() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = generarListaDePromociones(atracciones);
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 2000, 72, 20, TipoDeAtraccion.ACAMPAR, 1);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(2, sugerencias.size());
	}
	
	@Test
	public void cuandoSeGeneranLaPrimeraSugerenciasEntoncesEstaEsCreadaConUnTiempoTotalDeterminado() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = generarListaDePromociones(atracciones);
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null,  atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 2000, 72, 20, TipoDeAtraccion.ACAMPAR, 1);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(61.33, sugerencias.get(0).getTiempoTotal(), 0.1);
		
	}
	
	@Test
	public void cuandoSeGeneranLasSugerenciasEntoncesEstaSeCreanConUnCostoDeterminado() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = generarListaDePromociones(atracciones);
		
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 2000, 72, 20, TipoDeAtraccion.ACAMPAR, 1);
		
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(2000, sugerencias.get(0).getCostoTotal(), 0.01);
		
	}
	
	@Test
	public void cuandoSeGeneraLaPrimerSugerenciaEstaEsCreadaConCuatroAtraciones() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = generarListaDePromociones(atracciones);
		
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones, null,atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 2000, 72, 20, TipoDeAtraccion.ACAMPAR, 1);
		
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(4, sugerencias.get(0).getAtracciones().size());
		
	}
	
	@Test
	public void cuandoSeGeneraLaSegundaSugerenciaEstaEsCreadaConDosAtreacciones() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = generarListaDePromociones(atracciones);
		
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 2000, 72, 20, TipoDeAtraccion.ACAMPAR,1);
		
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaNoVigente());
		
		Assert.assertEquals(2, sugerencias.get(1).getAtracciones().size());
		
	}
	
	@Test
	public void cuandosSeGeneraLaPrimeraSugerenciaEstaTieneAplicadaPromocionAbsolutaEnElCosto() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = new ArrayList<PromocionAcumulable>();
		promociones.add(crearPromocionAbsoluta(atracciones));
		
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 1900, 72, 20, TipoDeAtraccion.ACAMPAR,1);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(900, sugerencias.get(0).getCostoTotal(), 0.01);	
	}
	
	@Test
	public void cuandosSeGeneraLaPrimeraSugerenciaEstaTieneAplicadaPromocionPorcentualEnElCosto() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = new ArrayList<PromocionAcumulable>();
		promociones.add(crearPromocionPorcentual(atracciones));
		
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 1900, 72, 20, TipoDeAtraccion.ACAMPAR,1);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(1325, sugerencias.get(0).getCostoTotal(), 0.01);
		
	}
	
	@Test
	public void cuandosSeGeneraLaPrimeraSugerenciaEstaTieneAplicadaPromocionAXBEnElCosto() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = new ArrayList<PromocionAcumulable>();
		promociones.add(crearPromocionAXB(atracciones));
				
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 1900, 72, 20, TipoDeAtraccion.ACAMPAR,1);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(1500, sugerencias.get(0).getCostoTotal(), 0.01);
	}
	
	@Test
	public void cuandosSeGeneraLaPrimeraSugerenciaEstaTieneAplicadaPromocionFamiliarConDescuentoDeCuatroEntradas() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = new ArrayList<PromocionAcumulable>();
		promociones.add(crearPromocionFamiliar());
				
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 8000, 72, 20, TipoDeAtraccion.ACAMPAR, 4);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(7200, sugerencias.get(0).getCostoTotal(), 0.01);
	}

	
	@Test
	public void cuandosSeGeneraLaPrimeraSugerenciaEstaTieneAplicadaPromocionFamiliarConDescuentoDeSeisEntradas() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = new ArrayList<PromocionAcumulable>();
		promociones.add(crearPromocionFamiliar());
				
		Posicion posicionUsuario = new Posicion(30,50);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 12000, 72, 20, TipoDeAtraccion.ACAMPAR,6);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(10000, sugerencias.get(0).getCostoTotal(), 0.01);
	}
	
	@Test
	public void cuandosSeGeneraLaPrimeraSugerenciaParaTresPersonasNoSeAplicaLaPromocionFamiliar() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		List<PromocionAcumulable> promociones = new ArrayList<PromocionAcumulable>();
		promociones.add(crearPromocionFamiliar());
				
		Posicion posicionUsuario = new Posicion(30,50);
				
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(promociones,null, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 6000, 72, 20, TipoDeAtraccion.ACAMPAR,3);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(6000, sugerencias.get(0).getCostoTotal(), 0.01);
	}
	
	@Test
	public void cuandosSeGeneraLaPrimeraSugerenciaEstaTieneAplicadaPromocionExtranjero() {
		List<Atraccion> atracciones = generarListaDeAtracciones() ;
		PromocionNoAcumulable promocionExtranjero = crearPromocionExtranjero();
				
		Posicion posicionUsuario = new Posicion(300,300);
		
		SecretariaDeTurismo tierraMedia = new SecretariaDeTurismo(null,promocionExtranjero, atracciones);
		
		Usuario usuario = new Usuario(posicionUsuario, 1900, 72, 20, TipoDeAtraccion.ACAMPAR,1);
		
		List<Sugerencia> sugerencias = tierraMedia.generarSugerencias(usuario, fechaVigente());
		
		Assert.assertEquals(750, sugerencias.get(0).getCostoTotal(), 0.01);
	}
	
	
	private List<PromocionAcumulable> generarListaDePromociones(List<Atraccion> atracciones){
		
		PromocionAcumulable promocionAbsoluta = crearPromocionAbsoluta(atracciones);
 		PromocionAcumulable promocionPorcentual = crearPromocionPorcentual(atracciones);
 		PromocionAcumulable PromocionAxB = crearPromocionAXB(atracciones);
 		
 		List<PromocionAcumulable> promociones = new ArrayList<PromocionAcumulable>();
 		promociones.add(promocionAbsoluta);
 		promociones.add(promocionPorcentual);
 		promociones.add(PromocionAxB);
 		
 		return promociones;
	}

	private PromocionAXB crearPromocionAXB(List<Atraccion> atracciones) {
		List<Atraccion> atraccionesDePromocionAXB = new ArrayList<Atraccion>() ;
		atraccionesDePromocionAXB.add(atracciones.get(1));
		atraccionesDePromocionAXB.add(atracciones.get(0));
		
		return new PromocionAXB(fechaDeInicio(), fechaDeFin(), atraccionesDePromocionAXB, atracciones.get(3));
	}
	
	private PromocionExtranjero crearPromocionExtranjero() {
		return new PromocionExtranjero(fechaDeInicio(), fechaDeFin());
	}
	
	private PromocionFamiliar crearPromocionFamiliar() {
		
		return new PromocionFamiliar(fechaDeInicio(), fechaDeFin());
	}

	private PromocionPorcentual crearPromocionPorcentual(
			List<Atraccion> atracciones) {
		List<Atraccion> atraccionesDePromocionPorcentual = new ArrayList<Atraccion>() ;
		atraccionesDePromocionPorcentual.add(atracciones.get(0));
		atraccionesDePromocionPorcentual.add(atracciones.get(2));
		
		return new PromocionPorcentual(fechaDeInicio(), fechaDeFin(), 25, atraccionesDePromocionPorcentual);
	}

	private PromocionAbsoluta crearPromocionAbsoluta(
			List<Atraccion> atracciones) {
		List<Atraccion> atraccionesDePromocionAbsoluta = new ArrayList<Atraccion>() ;
		atraccionesDePromocionAbsoluta.add(atracciones.get(1));
		atraccionesDePromocionAbsoluta.add(atracciones.get(2));
		
		return new PromocionAbsoluta(atraccionesDePromocionAbsoluta, fechaDeInicio(), fechaDeFin(), 700);
	}
	
	
	
	private List<Atraccion> generarListaDeAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		Posicion posicionAcampar = new Posicion(0,5);
		Posicion posicionPaisaje = new Posicion(5,9);
		Posicion posicionMuseoArte = new Posicion(10,6);
		Posicion posicionParacaidismo = new Posicion(7,12);
		Posicion posicionAventura = new Posicion(13,11);
		Posicion posicionMuseoNatural = new Posicion(40,7);
		
		Atraccion acampar = new Atraccion(posicionAcampar, 200, 24, 20, TipoDeAtraccion.ACAMPAR);
		Atraccion paisaje = new Atraccion(posicionPaisaje, 800, 12, 15, TipoDeAtraccion.PAISAJE);
		Atraccion museoDeArte = new Atraccion(posicionMuseoArte, 500, 10, 20, TipoDeAtraccion.MUSEO);
		Atraccion paracaidismo = new Atraccion(posicionParacaidismo, 500, 12, 50, TipoDeAtraccion.PARACAIDISMO);
		Atraccion aventura = new Atraccion(posicionAventura, 700, 7, 20, TipoDeAtraccion.AVENTURA);
		Atraccion museoNatural = new Atraccion(posicionMuseoNatural, 400, 7, 20, TipoDeAtraccion.MUSEO);
		
		atracciones.add(acampar);
		atracciones.add(paisaje);
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
