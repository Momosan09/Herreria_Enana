package com.mygdx.entidades.ObjetosDelMapa;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.utiles.HelpDebug;

public class IngredientesRegistry {

	/*
	 * Esta clase SOLAMENTE VINCULA TipoMinerales y EstadosMinerales con IngredientesId
	 * registrar lo que hace es tomar los primeros dos parametros y relaciona con el tercero,
	 * es decir, que en IngredientesId se definen TODOS los ingredientesId y en casos particulares como el de
	 * fundicionOmega, donde solamente creo el mineral en tiempo de ejecucion ese mineral tiene que estar definido aqui
	 * para poder asi asignarle correctamente el IngredienteId, el cual es un pilar importantisimo de todo el sistema
	 *
	 * registrar() => cuando el IngredientesId depende de: TipoMinerales + EstadosMinerales
	 */
	
    private static final Map<TipoMinerales, Map<EstadosMinerales, IngredientesId>> MAPA = new HashMap<>();

    static {
    	registrar(TipoMinerales.HIERRO, EstadosMinerales.MOLDE_CABEZA_MAZA, IngredientesId.HIERRO_CABEZA_MAZA);
    }

    private static void registrar(TipoMinerales tipo, EstadosMinerales estado, IngredientesId ingrediente) {
        MAPA.computeIfAbsent(tipo, t -> new HashMap<>()).put(estado, ingrediente);
    }

    public static IngredientesId get(TipoMinerales tipo, EstadosMinerales estado) {
    	
    	if(MAPA.getOrDefault(tipo, Map.of()).get(estado) != null) {
    		return MAPA.getOrDefault(tipo, Map.of()).get(estado);
    	}else {
    		System.err.println(HelpDebug.debub(IngredientesRegistry.class) + "Error. Probablemente falte registrar el ingredienteId en IngredienteRegistry\n acordate que tiene que estar previamente definido en IngredientesId y que se usa cuando el IngredientesId depende de: TipoMinerales + EstadosMinerales => IngredientesId");
    		return null;
    	}

    }
}
