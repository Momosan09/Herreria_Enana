package com.mygdx.combinaciones.dto;

import java.util.List;

import com.badlogic.gdx.utils.Null;
import com.mygdx.armas.Equipo;
import com.mygdx.armas.ResultadoFinalId;

public class RecetaDTO {
    public String medio;
    public List<IngredienteDTO> entradas;
    @Null public String herramienta; // puede ser null
    @Null public List<IngredienteDTO> salidas;
    @Null public ResultadoFinalId salidaFinal;

}
