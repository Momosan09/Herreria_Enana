package com.mygdx.armas.modificadores;

import com.mygdx.armas.EstadosArmas;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class AplicadorDeModificadores {

    public static EfectosArma darEfecto(Modificadores mod, Mineral metal, EstadosArmas tipoArma) {
        return RegistroEfectos.obtener(metal, mod, tipoArma);
    }
}
