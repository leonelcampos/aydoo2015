package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecretariaDeTurismo {
	
	List<Promocion> promociones;
	
	List<Atraccion> atracciones;
	

	public SecretariaDeTurismo(List<Promocion> promociones,
			List<Atraccion> atracciones) {
		this.promociones = promociones;
		this.atracciones = atracciones;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Sugerencia> generarSugerencias(Usuario usuario, Date fecha) {
		List<Sugerencia> sugerencias = new ArrayList<>();
		List<Atraccion> atraccionesFiltradasParaElUsuario = getAtraccionesApropiadasParaElUsuario(usuario);
		Sugerencia sugerencia = new Sugerencia(new ArrayList<Atraccion>());
		
		for (int i= 0; i< atraccionesFiltradasParaElUsuario.size(); i++) {
			
			sugerencia.addAtraccion(atracciones.get(i));
			
			if(!verificarAtraccionesApropiadasParaElUsuario(sugerencia, usuario, fecha)){
				sugerencia.getAtracciones().remove(atracciones.get(i));
				sugerencias.add(sugerencia);
				
				sugerencia = new Sugerencia(new ArrayList<Atraccion>());
				sugerencia.addAtraccion(atracciones.get(i));
				
				if(!verificarAtraccionesApropiadasParaElUsuario(sugerencia, usuario, fecha)){	
					sugerencia.getAtracciones().remove(atracciones.get(i));					
				}
			}
		}
		sugerencias.add(sugerencia);
		return sugerencias;
		
	}
	

	private List<Atraccion> getAtraccionesApropiadasParaElUsuario(Usuario user) {
		List<Atraccion> atraccionesApropiadasParaElUsuario = new ArrayList<Atraccion>();
		
		for (Atraccion attraction : atracciones) {
			if(verificarAtraccionesDisponibles(attraction) && verificarCosto(user, attraction.getCosto()) && verificarTiempo(user, attraction.getTiempoPromedio())){
				atraccionesApropiadasParaElUsuario.add(attraction);
			}
		}
		return atraccionesApropiadasParaElUsuario;
		
	}

	private boolean verificarAtraccionesApropiadasParaElUsuario(
			Sugerencia sugerencia, Usuario usuario, Date fecha) {
		
		if(verificarCosto(usuario, sugerencia.getCostoTotal(promociones, fecha)) 
				&& verificarTiempo(usuario, sugerencia.getTiempoTotal(sugerencia.getAtracciones(), usuario.getSpeed()))){
			return true;
		}else{
			return false;
		}
			
	}

	private boolean verificarTiempo(Usuario user, double totalTime) {
		return user.getAvailableTime() >= totalTime;
	}

	private boolean verificarCosto(Usuario user, double totalCost) {
		return user.getMany() >= totalCost;
	}



	private boolean verificarAtraccionesDisponibles(Atraccion attraction) {	
			return (attraction.getDisponibilidad() > 0);
	}
	


	
	
	
	
	
}
