package com.mygdx.combinaciones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.combinaciones.dto.*;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.recursos.Recursos;

import java.util.ArrayList;
import java.util.List;

public class CargadorRecetas {

    public static List<Receta> cargar(String path) {

        Json json = new Json();

        RecetasDTO data = json.fromJson(
                RecetasDTO.class,
                Gdx.files.internal(path)
        );

        List<Receta> recetas = new ArrayList<>();

        for (RecetaDTO dto : data.recetas) {

            // Entradas
            List<IngredienteReceta> entradas = new ArrayList<>();
            for (IngredienteDTO i : dto.entradas) {
                entradas.add(
                    new IngredienteReceta(
                        IngredientesId.valueOf(i.ingrediente),
                        i.cantidad
                    )
                );
            }

            // Salidas
            List<IngredienteReceta> salidas = new ArrayList<>();
            for (IngredienteDTO i : dto.salidas) {
                salidas.add(
                    new IngredienteReceta(
                        IngredientesId.valueOf(i.ingrediente),
                        i.cantidad
                    )
                );
            }

            // Herramienta (opcional)
            IngredientesId herramienta = dto.herramienta == null
                    ? null
                    : IngredientesId.valueOf(dto.herramienta);

            // Medio
            Medios medio = Medios.valueOf(dto.medio);

            recetas.add(
                new Receta(
                    entradas,
                    herramienta,
                    salidas,
                    medio
                )
            );
        }
        System.out.println(HelpDebug.debub(CargadorRecetas.class)+ recetas.size()+ " recetas cargadas desde "+ Recursos.RECETAS);
        return recetas;
    }
}
