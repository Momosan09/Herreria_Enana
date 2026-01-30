package com.mygdx.combinaciones.dto;

import java.util.List;

import com.badlogic.gdx.utils.Null;

public class RecetaDTO {
    public String medio;
    public List<IngredienteDTO> entradas;
    @Null public String herramienta; // puede ser null
    public List<IngredienteDTO> salidas;
}
