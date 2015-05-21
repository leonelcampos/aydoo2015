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
import domain.PromocionExtranjero;
import domain.PromocionFamiliar;
import domain.PromocionPorcentual;
import domain.TipoDeAtraccion;

public class PromocionTest {
	
	@Test
	public void cuandoCreoUnaPromocionAbsolutaEstaSeCreaConUnCostoDeterminado() {
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(atracciones,fechaInicio(), fechaFin(), 200);
		
		double costoEsperado = 200;
		
		Assert.assertEquals( costoEsperado, promocionAbsoluta.getCostoPromocion(), 0.1);
			
	}
	
	@Test
	public void cuandoCreoUnaPromocionAXBEstaSeCreaConUnCostoDeterminado() {
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		PromocionAXB promocionAXB = new PromocionAXB(fechaInicio(), fechaFin(), atracciones, generarAtraccionBonificadaParaPromocionAXB());
		
		double cotoEsperado = 1200;
		
		Assert.assertEquals( cotoEsperado, promocionAXB.getCostoPromocion(), 0.1);
			
	}

	@Test
	public void cuandoCreoUnaPromocionPorcentualEstaSeCreaConUnCostoDeterminado() {
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(fechaInicio(), fechaFin(), 50, atracciones);
		
		double costExpected = 600;
		
		Assert.assertEquals( costExpected, promocionPorcentual.getCostoPromocion(), 0.1);
			
	}
	
	
	@Test
	public void cuandoAplicoUnaPromocionExtranjeroEntoncesSeAplicaUnDescuentoDeCincuantaPorciento() {
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		PromocionExtranjero promocionExtranjero = new PromocionExtranjero(fechaInicio(), fechaFin());
		
		double costExpected = 600;
		
		Assert.assertEquals( costExpected, promocionExtranjero.aplicarCostoDePromocion(fechaVigente(), atracciones), 0.1);
			
	}
	
	@Test
	public void cuandoAplicoUnaPromocionFamiliarConCuatroEntradasEntoncesSeAplicaUnDescuentoDeDiezPorciento(){
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		PromocionFamiliar promocionFamiliar = new PromocionFamiliar(fechaInicio(), fechaFin());
		
		double costExpected = 4320;
		double costoDeAtracciones = calcularCostoDeAtracciones(atracciones);
		
		Assert.assertEquals( costExpected, promocionFamiliar.aplicarCostoDePromocion(fechaVigente(), atracciones, costoDeAtracciones, 4), 0.1);
		
	}
	
	@Test
	public void cuandoAplicoUnaPromocionFamiliarConSeisEntradasEntoncesSeAplicaUnDescuentoDeTreintaPorciento(){
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		PromocionFamiliar promocionFamiliar = new PromocionFamiliar(fechaInicio(), fechaFin());
		
		double costExpected = 6000;
		double costoDeAtracciones = calcularCostoDeAtracciones(atracciones);
		
		Assert.assertEquals( costExpected, promocionFamiliar.aplicarCostoDePromocion(fechaVigente(), atracciones, costoDeAtracciones, 6), 0.1);
		
	}
	
	
	
	private Date fechaVigente(){
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(2016,0,10);
		
		return ahoraCal.getTime();
	}
	
	private double calcularCostoDeAtracciones(List<Atraccion> atracciones){
		double costo = 0;
		for (Atraccion attraction : atracciones) {
			costo += attraction.getCosto();
		}
		return costo;
	}
	
	
	private Atraccion generarAtraccionBonificadaParaPromocionAXB(){
		
		Posicion posicion = new Posicion(50,20);
		Atraccion acampar = new Atraccion(posicion, 500, 8, 20, TipoDeAtraccion.ACAMPAR);
		
		return acampar;
	}
	
	private List<Atraccion> generarListaDeAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		Posicion posicionAcampar = new Posicion(0,5);
		Posicion posicionMuseo = new Posicion(20,6);
		Posicion posicionParacaidismo = new Posicion(27,12);
		
		Atraccion acampar = new Atraccion(posicionAcampar, 200, 24, 20, TipoDeAtraccion.AVENTURA);
		Atraccion museo = new Atraccion(posicionMuseo, 500, 10, 20, TipoDeAtraccion.MUSEO);
		Atraccion paracaidismo = new Atraccion(posicionParacaidismo, 500, 12, 50, TipoDeAtraccion.PARACAIDISMO);
		
		atracciones.add(paracaidismo);
		atracciones.add(acampar);
		atracciones.add(museo);
		
		return atracciones;
	}

	private Date fechaFin() {
		Calendar fechaFin = Calendar.getInstance();
		fechaFin.set(2016,2,10);
		
		return fechaFin.getTime();
		
	}

	private Date fechaInicio() {
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.set(2015,10,10);
		
		return fechaInicio.getTime();
	}
	
	

}
