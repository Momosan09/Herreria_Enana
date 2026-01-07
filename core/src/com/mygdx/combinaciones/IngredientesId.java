package com.mygdx.combinaciones;

import com.badlogic.gdx.utils.Null;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.enums.Items;

/**
 * Este enum engloba los enums de rutas y ademas a todos los items que pueden ser combinables 
 * @author  Momosan09
 *
 */

public enum IngredientesId {

	//ITEMS
	CINCEL(Items.CINCEL),
	LIMA_PLANA(Items.LIMA_PLANA),
	MAZA(Items.MAZA),
	PICO(Items.PICO), 
	SIERRA(Items.SIERRA),
	SIERRA_CIRCULAR(Items.SIERRA_CIRCULAR),
	
	//ESPADAS
		//COMPLETAS
		ESPADA_HIERRO_0(Items.ESPADA_HIERRO_0),
	
		//HOJAS
		HOJA_ESPADA_HIERRO_0(Items.HOJA_ESPADA_HIERRO_0),
		
		
	//MINERALES
	
		//CARBON
	CARBON_MENA(TipoMinerales.CARBON, EstadosMinerales.MENA),
	CARBON_PURO(TipoMinerales.CARBON, EstadosMinerales.PURO),
	
		//HIERRO
	HIERRO_MENA(TipoMinerales.HIERRO, EstadosMinerales.MENA),
	HIERRO_PURO(TipoMinerales.HIERRO, EstadosMinerales.PURO),
	HIERRO_DISCO(TipoMinerales.HIERRO, EstadosMinerales.DISCO),
	HIERRO_PLANCHA(TipoMinerales.HIERRO, EstadosMinerales.PLANCHA),
	HIERRO_TIRA(TipoMinerales.HIERRO, EstadosMinerales.TIRA),
	HIERRO_LINGOTE(TipoMinerales.HIERRO, EstadosMinerales.LINGOTE),
	
		//PIEDRA
	PIEDRA_MENA(TipoMinerales.PIEDRA, EstadosMinerales.MENA), 
	
	
	//ESQUEMAS
	ESQUEMA_SIERRA_CIRCULAR(Items.ESQUEMA_SIERRA_CIRCULAR),
	ESQUEMA_HOJA_ESPADA(Items.ESQUEMA_HOJA_ESPADA),
	
	
	//MADERA
	MANGO_MADERA_0(Items.MANGO_MADERA_0)
	;
	
	/**
	 * Para los items
	 */
	public @Null Items tipoI;
	
	/**
	 * Para los minerales
	 */
	public @Null TipoMinerales tipoM;
	/**
	 * Para los minerales
	 */
	public @Null EstadosMinerales estadoM;
	
	IngredientesId(Items tipo) {
		this.tipoI = tipo;
	}
	
	IngredientesId(TipoMinerales tipo, EstadosMinerales estado){
		this.tipoM = tipo;
		this.estadoM = estado;
	}
}
