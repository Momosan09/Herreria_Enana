package com.mygdx.entidades.jugador;

import java.util.ArrayList;
import java.util.EnumMap;

import com.mygdx.armas.modificadores.Modificadores;

public class ModificadoresInventarioManager {

    private EnumMap<Modificadores, Integer> inventario = new EnumMap<>(Modificadores.class);

    public ModificadoresInventarioManager() {
        for (Modificadores mod : Modificadores.values()) {
            inventario.put(mod, 0);
        }
    }

    /* =======================
       CONSULTAS
       ======================= */

    public int getCantidad(Modificadores mod) {
        return inventario.getOrDefault(mod, 0);
    }

    public boolean tiene(Modificadores mod) {
        return getCantidad(mod) > 0;
    }

    /* =======================
       AGREGAR
       ======================= */

    public void agregar(Modificadores mod) {
        inventario.put(mod, getCantidad(mod) + 1);
    }

    public void agregar(Modificadores mod, int cantidad) {
        inventario.put(mod, getCantidad(mod) + cantidad);
    }

    /* =======================
       CONSUMIR
       ======================= */

    public void consumir(Modificadores mod) {
        consumir(mod, 1);
    }

    public void consumir(Modificadores mod, int cantidad) {
        int actual = getCantidad(mod);
        if (actual < cantidad) {
            throw new IllegalStateException("No hay suficiente " + mod);
        }
        inventario.put(mod, actual - cantidad);
    }

    /* =======================
       LISTADOS
       ======================= */

    public ArrayList<Modificadores> obtenerModificadoresDisponibles() {
        ArrayList<Modificadores> lista = new ArrayList<>();

        for (Modificadores mod : inventario.keySet()) {
            if (inventario.get(mod) > 0) {
                lista.add(mod);
            }
        }
        return lista;
    }

    /* =======================
       DEBUG
       ======================= */

    public void imprimirInventario() {
        System.out.println("=== MODIFICADORES ===");
        inventario.forEach((k, v) -> {
            if (v > 0) {
                System.out.println(k + " x" + v);
            }
        });
    }
}
