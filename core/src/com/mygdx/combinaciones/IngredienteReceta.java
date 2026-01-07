package com.mygdx.combinaciones;

/**
 * Necesito un objeto darle mas variedad a las combinaciones, o sea, este Record me permite agregar atributos que se me vayan ocurriendo para el json. Si en el json especifico "cantidad" para entradas (ejemplo: dos lingotes de hierro), en este Record tengo que poner un atributo cantidad y, en el Record Receta, no usar IngredientesId puro, sino que esta clase
 * @author  Momosan09
 *
 */
public record IngredienteReceta(IngredientesId ingrediente, int cantidad) 
{}
