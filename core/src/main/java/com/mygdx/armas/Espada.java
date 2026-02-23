package com.mygdx.armas;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Null;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.enums.Items;

public class Espada extends Arma{

	
	public Espada(Mineral metalBase, @Null ArrayList<Items> items, EstadosArmas tipoArma) {
		super(metalBase, items, tipoArma);//poner atributos propios por ser espada
	}
}
