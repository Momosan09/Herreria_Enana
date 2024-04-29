package com.mygdx.historia;

import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.HelpDebug;

public class Mision {

	private String requisor;//quien nos da la mision
	private TipoMision tipo;
	private String objeto;
	private int cantidadConseguida = 0, cantidadObjetivo;//La cantidad de lo que pide
	private boolean completada = false, recompensaAdquirida = false, objetoFabricado = false;
	private int cobre, plata, oro;
	private String id;
	
	public Mision(MisionesDelJuego datosMision) {
		this.requisor = datosMision.getRequisor();
		this.tipo = datosMision.getTipo();
		this.objeto = datosMision.getObjeto();
		this.cantidadObjetivo = datosMision.getCantidadObjetivo();
		this.oro = datosMision.getOro();
		this.plata = datosMision.getPlata();
		this.cobre = datosMision.getCobre();
		this.id = datosMision.getId();
		System.out.println(HelpDebug.debub(getClass())+"Creada correctamente la mision ["+id+"]");
	}
	
	public void comprobarCondicion() {
		
		switch (tipo) {
		case RECOLECTAR:
			if(cantidadConseguida == cantidadObjetivo) {
				completada = true;
			}			
			break;

		case FABRICAR:
			if(objetoFabricado) {
				completada = true;
			}
			break;
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
	
	public String getEntidad() {
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
	
	public void setObjetoFabricado() {
		objetoFabricado = true;
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
	
	public String getId() {
		return id;
	}
}
