package com.mygdx.armas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Null;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;

public abstract class Arma extends Equipo{

//	public Arma(Mineral metalBase, EstadosArmas tipoArma) {
//		super(metalBase, tipoArma);
//	}
	
	public Arma(Mineral metalBase, @Null ArrayList<Items> items, EstadosArmas tipoArma) {
		super(metalBase, items, tipoArma);
	}
}
