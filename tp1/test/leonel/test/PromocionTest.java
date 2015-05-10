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
import domain.PromocionPorcentual;
import domain.Promocion;

public class PromocionTest {
	
	@Test
	public void cuandoCreoUnaPromocionAbsolutaEstaSeCreaConUnCostoDeterminado() {
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		Promocion promocionAbsoluta = new PromocionAbsoluta(atracciones,fechaInicio(), fechaFin(), 200);
		
		double costoEsperado = 200;
		
		Assert.assertEquals( costoEsperado, promocionAbsoluta.getCostoPromocion(), 0.1);
			
	}
	
	@Test
	public void cuandoCreoUnaPromocionAXBEstaSeCreaConUnCostoDeterminado() {
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		Promocion promocionAXB = new PromocionAXB(fechaInicio(), fechaFin(), atracciones, generarAtraccionBonificadaParaPromocionAXB());
		
		double cotoEsperado = 1200;
		
		Assert.assertEquals( cotoEsperado, promocionAXB.getCostoPromocion(), 0.1);
			
	}

	@Test
	public void cuandoCreoUnaPromocionPorcentualEstaSeCreaConUnCostoDeterminado() {
		
		List<Atraccion> atracciones = generarListaDeAtracciones();
		Promocion promocionPorcentual = new PromocionPorcentual(fechaInicio(), fechaFin(), 50, atracciones);
		
		double costExpected = 600;
		
		Assert.assertEquals( costExpected, promocionPorcentual.getCostoPromocion(), 0.1);
			
	}
	
	
	private Atraccion generarAtraccionBonificadaParaPromocionAXB(){
		
		
		Atraccion acampar = new Atraccion(50, 20, 500, 8, 20, tipoDeAtraccion.ACAMPAR);
		
		return acampar;
	}
	
	private List<Atraccion> generarListaDeAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		Atraccion acampar = new Atraccion(0, 5, 200, 24, 20, tipoDeAtraccion.AVENTURA);
		Atraccion museo = new Atraccion(20, 6, 500, 10, 20, tipoDeAtraccion.MUSEO);
		Atraccion paracaidismo = new Atraccion(27,  12, 500, 12, 50, tipoDeAtraccion.PARACAIDISMO);
		
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
