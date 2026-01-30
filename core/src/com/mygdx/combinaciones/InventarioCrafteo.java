package com.mygdx.combinaciones;

import java.util.ArrayList;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public interface InventarioCrafteo {

    int getCantidad(IngredientesId ingrediente);

    void consumir(IngredientesId ingrediente, int cantidad);

    void agregar(IngredientesId ingrediente, int cantidad);
    
    boolean tieneItem(IngredientesId ingrediente);
    
    ArrayList<Mineral> getMinerales();
}
