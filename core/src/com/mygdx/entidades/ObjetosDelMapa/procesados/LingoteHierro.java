package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;

public class LingoteHierro extends Mineral{

	public LingoteHierro() {
		//Los dos enum componen la ruta de la textura TipoMinerales.HIERRO.ruta + EstadoMinerales.PURO.ruta
		super(IngredientesId.HIERRO_LINGOTE);
		
	}

}
