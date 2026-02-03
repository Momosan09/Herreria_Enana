package com.mygdx.combinaciones;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Null;
import com.mygdx.armas.Arco;
import com.mygdx.armas.Equipo;
import com.mygdx.armas.EstadosArmas;
import com.mygdx.armas.MazaDeGuerra;
import com.mygdx.armas.ResultadoFinalId;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;

public class ResultadoFinalFactory {

    public static Equipo crear(ResultadoFinalId id, Mineral metalBase, @Null ArrayList<Items> items) {

        return switch (id) {
            case MAZA_DE_GUERRA -> new MazaDeGuerra(metalBase, items, EstadosArmas.MAZA_1);
            case ARCO -> new Arco(metalBase);
            
            default -> throw new IllegalArgumentException("Unexpected value: " + id);
        };
    }
}
