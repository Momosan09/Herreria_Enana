package com.mygdx.armas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Null;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;
import com.mygdx.utiles.HelpDebug;

public class MazaDeGuerra extends Arma{

//	public MazaDeGuerra(Mineral metalBase) {
//		super(metalBase, EstadosArmas.MAZA_1);//poner atributos propios por ser maza de guerra
//
//	}
	
	public MazaDeGuerra(Mineral metalBase, @Null ArrayList<Items> items, EstadosArmas tipoArma) {
		super(metalBase, items, tipoArma);//poner atributos propios por ser maza de guerra

	}
	
}
