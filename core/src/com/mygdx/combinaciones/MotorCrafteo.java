package com.mygdx.combinaciones;

public class MotorCrafteo {

    /**
     * Verifica si se puede fabricar una receta
     */
    public static boolean puedeFabricar(
            Receta receta,
            InventarioCrafteo inventario,
            Medios medioActual
    ) {

        // Medio correcto
        if (receta.medio() != medioActual) {
            return false;
        }

        // Herramienta (no se consume)
        if (receta.herramienta() != null) {
            if (inventario.getCantidad(receta.herramienta()) <= 0) {
                return false;
            }
        }

        // Entradas
        for (IngredienteReceta ing : receta.entradas()) {
            if (inventario.getCantidad(ing.ingrediente()) < ing.cantidad()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Fabrica la receta (ASUME que ya fue validada)
     */
    public static void fabricar(
            Receta receta,
            InventarioCrafteo inventario
    ) {

        // Consumir entradas
        for (IngredienteReceta ing : receta.entradas()) {
            inventario.consumir(
                    ing.ingrediente(),
                    ing.cantidad()
            );
        }

        // Agregar salidas
        for (IngredienteReceta out : receta.salidas()) {
            inventario.agregar(
                    out.ingrediente(),
                    out.cantidad()
            );
        }
    }
}
