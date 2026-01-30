package com.mygdx.combinaciones;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.procesados.CarbonPuro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroPuro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.LingoteHierro;

/**
 * Como los ingredientes ahora son solo por id, y no hay objetos, aveces voy a necesitar el objeto en particular del id, entonces lo tengo que definir aca
 * Un ejemplo en particular es en "FundicionOmega" donde necesito el combustible y por temas de herencia y polimorfismo no me deja acceder a las carateristicas de combustible del mineral elegido (en este caso carbonpuro)
 * @author  Momosan09
 *
 */
public class CreadorDeMinerales {
    public static Mineral crear(IngredientesId id) {

        switch (id) {

            case CARBON_PURO:
                return new CarbonPuro();
            
            case HIERRO_PURO:
                return new HierroPuro();

            case HIERRO_LINGOTE:
                return new LingoteHierro();

            default:
                return new Mineral(id);
        }
    }
}
