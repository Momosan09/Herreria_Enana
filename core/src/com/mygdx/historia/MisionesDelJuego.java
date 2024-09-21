package com.mygdx.historia;import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.Carpintero;
import com.mygdx.utiles.Recursos;

public enum MisionesDelJuego {
	
	
	//En esta clase creo todas las misiones que se que voy a necesitar
	
	//Misiones:
	
	//REY
	
	
	//viejo
	
	//Carpintero
		CARP_00(Recursos.bundle.get("npc.nombre.carpintero"), TipoMision.FABRICAR,Recursos.bundle.get("mision.fabricable.sierra_circular"), 1,0,0,0,"CARP_00");
		
	private String requisor;
	private TipoMision tipo;
	private String objeto;
	private int cantidadObjetivo;
	private int oro, plata, cobre;
	private String id;
	
	MisionesDelJuego(String requisor, TipoMision tipo, String objeto, int cantidadObjetivo, int oro, int plata, int cobre, String id){
		this.requisor = requisor;
		this.tipo = tipo;
		this.objeto = objeto;
		this.cantidadObjetivo = cantidadObjetivo;
		this.oro = oro;
		this.plata = plata;
		this.cobre = cobre;
		this.id = id;
	}

	public String getRequisor() {
		return requisor;
	}

	public TipoMision getTipo() {
		return tipo;
	}

	public String getObjeto() {
		return objeto;
	}

	public int getCantidadObjetivo() {
		return cantidadObjetivo;
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
