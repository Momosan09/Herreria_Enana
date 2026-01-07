package com.mygdx.combinaciones;

public interface InventarioCrafteo {

    int getCantidad(IngredientesId ingrediente);

    void consumir(IngredientesId ingrediente, int cantidad);

    void agregar(IngredientesId ingrediente, int cantidad);
}
