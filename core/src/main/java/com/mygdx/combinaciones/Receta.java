package com.mygdx.combinaciones;
import java.util.List;

import com.badlogic.gdx.utils.Null;
import com.mygdx.armas.ResultadoFinalId;

public record Receta(List<IngredienteReceta> entradas, @Null IngredientesId herramienta, @Null List<IngredienteReceta> salidas,  @Null ResultadoFinalId salidaFinal, Medios medio) {
}
