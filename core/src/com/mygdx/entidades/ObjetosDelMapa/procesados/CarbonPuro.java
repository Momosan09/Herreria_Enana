package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.utiles.recursos.Recursos;

public class CarbonPuro extends Mineral implements Combustible{

	public CarbonPuro() {
		//Los dos enum componen la ruta de la textura TipoMinerales.HIERRO.ruta + EstadoMinerales.PURO.ruta
		super(IngredientesId.CARBON_PURO);
		
	}

	@Override
	public float getCalorias() {
		return 100;//TODO no creo que este sea el mejor lugar para poner el valor
	}
}
