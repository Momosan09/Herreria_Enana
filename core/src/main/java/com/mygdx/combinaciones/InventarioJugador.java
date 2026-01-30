package com.mygdx.combinaciones;

import java.util.ArrayList;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class InventarioJugador implements InventarioCrafteo {

    private final Jugador jugador;

    public InventarioJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public int getCantidad(IngredientesId id) {
        return jugador.getCantidad(id);
    }

    @Override
    public void consumir(IngredientesId id, int cantidad) {
        jugador.consumir(id, cantidad);
    }

    @Override
    public void agregar(IngredientesId id, int cantidad) {
        jugador.agregar(id, cantidad);
    }

	@Override
	public ArrayList<Mineral> getMinerales() {
		return jugador.getMinerales();
	}
	
	@Override
	public boolean tieneItem(IngredientesId id) {
	    return jugador.tieneItem(id);
	}

}
