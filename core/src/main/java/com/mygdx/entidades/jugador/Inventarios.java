package com.mygdx.entidades.jugador;

import java.util.HashMap;

import com.mygdx.armas.Equipo;
import com.mygdx.historia.Mision;
import com.mygdx.utiles.InventarioList;


/**
 * Junta todos los inventarios en una sola clase, limpiando la clase Jugador de los metodos especificos de cada inventario y creando una sintaxis mas limpia
 */
public class Inventarios {

    public TareasInventarioManager tareas = new TareasInventarioManager();
    public ItemsInventarioManager items = new ItemsInventarioManager();
	public InventarioList<Equipo> armas = new InventarioList<>();
	public ModificadoresInventarioManager modificadores = new ModificadoresInventarioManager();
	public IngredientesInventarioManager ingredientes = new IngredientesInventarioManager(items.getItems());
    
}
