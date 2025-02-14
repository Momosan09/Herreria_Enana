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
			if(cantidadConseguida == cantidadObjetivo) {
				objetoFabricado = true;
					completada = true;
				}
			break;
		}
		

	}
	
	public void darRecompensa(Jugador jugador) {
		if(completada && !recompensaAdquirida) {			
			jugador.monedero.agregarMonedasOro(oro);
			jugador.monedero.agregarMonedasPlata(plata);
			jugador.monedero.agregarMonedasCobre(cobre);
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
		if(cantidadConseguida < cantidadObjetivo) {			
		cantidadConseguida += cant;
		}else if(cantidadConseguida == cantidadObjetivo) {
			completada = true;
		}
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
