package com.mygdx.entidades;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.entidades.npcs.VendedorData;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.enums.Items;
import com.mygdx.hud.VentaHUD;
import com.mygdx.utiles.Recursos;

public class Vendedor extends Npc{
	
	private ArrayList<Items> inventario;
	private VentaHUD venta;

	public Vendedor(float x, float y, World world, String ruta, NpcData data, VendedorData itemsData) {
		super(x, y, world, ruta, data, itemsData);
		inventario = itemsData.getInventario();
		venta = new VentaHUD(this);
		Recursos.muxJuego.addProcessor(venta.getStage());
	}

	public ArrayList<Items> getInventario() {
		return inventario;
	}

	public void resetearVenta() {
		if(!jugadorEnRango && venta.getVisible()) {
			venta.ocultar();
		}
	}
	
	public void renderVenta() {
		venta.render();
	}
	
	
	public void mostrarVenta() {
		venta.mostrar();
	}
	public void ocultarVenta() {
		venta.ocultar();
	}
}
