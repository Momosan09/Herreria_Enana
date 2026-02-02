package com.mygdx.combinaciones;

import com.mygdx.armas.Arco;
import com.mygdx.armas.Equipo;
import com.mygdx.armas.MazaDeGuerra;
import com.mygdx.armas.ResultadoFinalId;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class ResultadoFinalFactory {

    public static Equipo crear(ResultadoFinalId id, Mineral metalBase) {

        return switch (id) {
            case MAZA_DE_GUERRA -> new MazaDeGuerra(metalBase);
            case ARCO -> new Arco(metalBase);
            
            default -> throw new IllegalArgumentException("Unexpected value: " + id);
        };
    }
}
