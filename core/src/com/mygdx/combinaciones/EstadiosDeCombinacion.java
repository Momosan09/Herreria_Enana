package com.mygdx.combinaciones;

/**
 * Tiene que ver con las recetas, una receta puede dar como resultado un ingrediente (IngredientesId) x
 * los cuales no tienen muchas cualidades. 
 * Tambien puede dar una espada (ObraDeHerrero) la cual tiene carcteristicas x
 * unicas debidas a los ingredientes con las que esta formada y la perfeccion en la ejecucion de la herreria/forjado. x
 * Ademas son items los cuales no se pueden aplicar combinaciones regulares con otros ingredientes y deben estar x
 * diferenciados, es decir, hojas de espadas por mas que sean ambas de hierro pueden tener una ejecucion distinta x
 * haciendo una mejor que la otra.
 * 
 * Este que un item crafteado tenga el valor "OBRA_DE_HERRERO" o "OBRA_MAESTRA_DE_HERRERIA" significa que a ese item se x
 * le tiene que hacer un seguimiento de sus atributos propios y, por lo tanto, tiene que ser un objeto.
 * @author  Momosan09
 *
 */
public enum EstadiosDeCombinacion {
	INGREDIENTE, OBRA_DE_HERRERO, OBRA_MAESTRA_DE_HERRERIA;
}
