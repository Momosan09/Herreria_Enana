package com.mygdx.historia;import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.Carpintero;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.utiles.Npcs;
import com.mygdx.utiles.recursos.Recursos;

public enum MisionesDelJuego {
	
	
	//En esta clase creo todas las misiones que se que voy a necesitar
	
	//Misiones:
	
	//REY
		//Rey Carta 1 Fabricar ESPadas
		RC1_FESP(Recursos.bundle.get("npc.nombre.rey"),
				Recursos.bundle.get("misiones.descripciones.rey.RC1_FESP"),
				7,
				TipoMision.FABRICAR,
				Recursos.bundle.get("item.fabricable.espada"),
				15, 0, 50,100,
				"RC1_FESP"),
	
		RC2_VIE(Recursos.bundle.get("npc.nombre.rey"),
				Recursos.bundle.get("misiones.descripciones.rey.RC2_VIE"),
				-1,
				TipoMision.HABLAR,
				Npcs.NPCS.get(NpcData.VIEJO),
				"viejo_REC2_VIE_0",
				0,0,0,
				"REC2_VIE"
				),
	
	//viejo
	
	//Carpintero
		CARP_00(Recursos.bundle.get("npc.nombre.carpintero"), 
				Recursos.bundle.get("misiones.descripciones.carpintero.CARP_00"),
				-1,
				TipoMision.FABRICAR,Recursos.bundle.get("mision.fabricable.sierra_circular"),
				1,0,0,0,
				"CARP_00");
	
	private boolean completada;
	private String requisor;
	private String descripcion;
	private int diasParaCompletar;
	private TipoMision tipo;
	private String objeto;
	private int cantidadObjetivo;
	private Npc npcObjetivo;
	private String charlaObjetivo;
	private int oro, plata, cobre;
	private String id;
	
	/**
	 * 
	 * @param requisor
	 * @param descripcion El texto que le aparece al jugador describiendo la mision
	 * @param diasParaCompletar Si es -1 no hay limite
	 * @param tipo
	 * @param objeto
	 * @param cantidadObjetivo
	 * @param oro
	 * @param plata
	 * @param cobre
	 * @param id
	 */
	MisionesDelJuego(String requisor, String descripcion, int diasParaCompletar, TipoMision tipo, String objeto, int cantidadObjetivo, int oro, int plata, int cobre, String id){
		this.requisor = requisor;
		this.descripcion = descripcion;
		this.diasParaCompletar = diasParaCompletar;
		this.tipo = tipo;
		this.objeto = objeto;
		this.cantidadObjetivo = cantidadObjetivo;
		this.oro = oro;
		this.plata = plata;
		this.cobre = cobre;
		this.id = id;
	}
	
	/**
	 * 
	 * @param requisor
	 * @param descripcion
	 * @param diasParaCompletar
	 * @param tipo
	 * @param npcObjetivo El npc al que se le debe hablar
	 * @param charlaObjetivo Es la charla que se debe tener con el npc para que se complete la mision
	 * @param oro
	 * @param plata
	 * @param cobre
	 * @param id
	 */
	MisionesDelJuego(String requisor, String descripcion, int diasParaCompletar, TipoMision tipo, Npc npcObjetivo, String charlaObjetivo,int oro, int plata, int cobre, String id){
		this.requisor = requisor;
		this.descripcion = descripcion;
		this.diasParaCompletar = diasParaCompletar;
		this.tipo = tipo;
		this.npcObjetivo = npcObjetivo;
		this.charlaObjetivo = charlaObjetivo;//Es la charla que se tiene que tener para que se complete la mision
		this.oro = oro;
		this.plata = plata;
		this.cobre = cobre;
		this.id = id;
	}

	public String getRequisor() {
		return requisor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public int getDiasParaCompletar() {
		return diasParaCompletar;
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

	public Npc getNpcObjetivo() {
		return npcObjetivo;
	}
	
	public String getNombreCharlaObjetivo() {
		return charlaObjetivo;
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
