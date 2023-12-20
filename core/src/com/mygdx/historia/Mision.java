package com.mygdx.historia;

import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.HelpDebug;

public class Mision {

	private Entidad requisor;//quien nos da la mision
	private TipoMision tipo;
	private String objeto;
	private int cantidadConseguida = 0, cantidadObjetivo;//La cantidad de lo que pide
	private boolean completada = false, recompensaAdquirida = false;
	private int cobre, plata, oro;
	
	public Mision(Entidad requisor, TipoMision tipo, String objeto, int cantidadObjetivo, int oro, int plata, int cobre) {
		this.requisor = requisor;
		this.tipo = tipo;
		this.objeto = objeto;
		this.cantidadObjetivo = cantidadObjetivo;
		this.oro = oro;
		this.plata = plata;
		this.cobre = cobre;
	}
	
	public void comprobarCondicion() {
		if(cantidadConseguida == cantidadObjetivo) {
			completada = true;
		}
	}
	
	public void darRecompensa(Jugador jugador) {
		if(completada && !recompensaAdquirida) {			
		jugador.dinero[0] += oro;
		jugador.dinero[1] += plata;
		jugador.dinero[2] += cobre;
		recompensaAdquirida = true;
		}
	}
	
	public Entidad getEntidad() {
		return requisor;
	}
	
	public TipoMision getTipoMision() {
		return tipo;
	}
	
	public String getObjeto() {
		return objeto;
	}
	
	public int getCantidadObjetivo() {
		return cantidadObjetivo;
	}
	
	public boolean getCompletada() {
		return completada;
	}

	public int getCantidadConseguida() {
		return cantidadConseguida;
	}
	
	public void setCantidadConseguida(int cant) {
		cantidadConseguida += cant;
	}
	
	public int getOro() {
		return oro;
	}
	
	public int getPlata() {
		return plata;
	}
	
	public int getCobre() {
		return cobre;
	}
}
