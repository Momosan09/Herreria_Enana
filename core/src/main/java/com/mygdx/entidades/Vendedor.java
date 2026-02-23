package com.mygdx.entidades;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.npcs.VendedorData;
import com.mygdx.utiles.EstadoMundo;

public class Vendedor extends Npc{
	
	private ArrayList<IngredientesId> inventario;

	public Vendedor(float x, float y, World world, String ruta, NpcData data, VendedorData itemsData, EstadoMundo estadoM, String rutaDialgos) {
		super(x, y, world, ruta, data, itemsData, estadoM, rutaDialgos);
		inventario = itemsData.getInventario();
	}

	public ArrayList<IngredientesId> getInventario() {
		return inventario;
	}



}
