package domain;

public class Posicion {
	
	private int posicionX;
	private int posicionY;
	
	
	public Posicion(int posicionX, int posicionY){
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}


	public int getPosicionX() {
		return posicionX;
	}


	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}


	public int getPosicionY() {
		return posicionY;
	}


	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	
	
}
