package com.mygdx.combinaciones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.combinaciones.dto.*;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;

import java.util.ArrayList;
import java.util.List;

public class CargadorRecetas {

    public static List<Receta> cargar(String path) {

        Json json = new Json();

        RecetasDTO data = json.fromJson(RecetasDTO.class, Gdx.files.internal(path));

        List<Receta> recetas = new ArrayList<>();

        for (RecetaDTO dto : data.recetas) {

            // Entradas
            List<IngredienteReceta> entradas = new ArrayList<>();
            for (IngredienteDTO i : dto.entradas) {
                entradas.add( new IngredienteReceta(IngredientesId.valueOf(i.ingrediente),i.cantidad));
            }

            // Salidas
            List<IngredienteReceta> salidas = new ArrayList<>();
            for (IngredienteDTO i : dto.salidas) {
                salidas.add(new IngredienteReceta(IngredientesId.valueOf(i.ingrediente),i.cantidad));
            }

            // Herramienta (opcional)
            IngredientesId herramienta = dto.herramienta == null? null : IngredientesId.valueOf(dto.herramienta);

            // Medio
            Medios medio = Medios.valueOf(dto.medio);
            
            EstadiosDeCombinacion estadio = EstadiosDeCombinacion.valueOf(dto.estadio);

            recetas.add(new Receta(entradas,herramienta,salidas,medio, estadio));
        }
        System.out.println(HelpDebug.debub(CargadorRecetas.class)+ recetas.size()+ " recetas cargadas desde "+ Recursos.RECETAS);
        return recetas;
    }
    
    public static Receta buscar(IngredientesId a, IngredientesId b, Medios medio) {

        for (Receta r : MundoConfig.recetas) {

            if (r.medio() != medio) continue;

            boolean aEsEntrada = contieneEntrada(r, a);
            boolean bEsEntrada = contieneEntrada(r, b);

            boolean aEsHerramienta = r.herramienta() != null && r.herramienta() == a;
            boolean bEsHerramienta = r.herramienta() != null && r.herramienta() == b;

            // entrada + entrada
            if (aEsEntrada && bEsEntrada) {
                return r;
            }

            // entrada + herramienta (en cualquier orden)
            if ((aEsEntrada && bEsHerramienta) || (bEsEntrada && aEsHerramienta)) {
                return r;
            }
        }

        return null;
    }

    private static boolean contieneEntrada(Receta r, IngredientesId id) {
        return r.entradas().stream()
                .anyMatch(e -> e.ingrediente() == id);
    }

}
