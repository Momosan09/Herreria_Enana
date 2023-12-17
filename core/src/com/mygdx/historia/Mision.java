package com.mygdx.historia;

import com.mygdx.entidades.Entidad;
import com.mygdx.utiles.HelpDebug;

public class Mision {

	private Entidad requisor;//quien nos da la mision
	private TipoMision tipo;
	private String objeto;
	private int cantidadConseguida = 0, cantidadObjetivo;//La cantidad de lo que pide
	private boolean completada = false;
	
	public Mision(Entidad requisor, TipoMision tipo, String objeto, int cantidadObjetivo) {
		this.requisor = requisor;
		this.tipo = tipo;
		this.objeto = objeto;
		this.cantidadObjetivo = cantidadObjetivo;
	}
	
	public void comprobarCondicion() {
		if( cantidadConseguida == cantidadObjetivo) {
			completada = true;
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
}
