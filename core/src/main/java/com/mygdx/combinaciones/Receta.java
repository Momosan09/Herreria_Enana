package com.mygdx.combinaciones;
import java.util.List;

import com.badlogic.gdx.utils.Null;

public record Receta(List<IngredienteReceta> entradas, @Null IngredientesId herramienta, List<IngredienteReceta> salidas, Medios medio) {
}
