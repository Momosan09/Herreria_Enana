package com.mygdx.armas.modificadores;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.armas.EstadosArmas;
import com.mygdx.armas.modificadores.losModificadores.ExplosionElectrica;
import com.mygdx.armas.modificadores.losModificadores.VolcanDestructor;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;

public class RegistroEfectos {

    private static final Map<ClaveEfecto, EfectosArma> efectos = new HashMap<>();

    static {
        efectos.put(
            new ClaveEfecto(TipoMinerales.ORO, Modificadores.ELECTRICIDAD, EstadosArmas.MAZA_1),
            new ExplosionElectrica()
        );
        
        efectos.put(
                new ClaveEfecto(TipoMinerales.ORO, Modificadores.FUEGO, EstadosArmas.MAZA_1),
                new VolcanDestructor()
            );

//        efectos.put(
//            new ClaveEfecto("oro", "electricidad", EstadosArmas.ESPADA),
//            new RayoPerforante()
//        );
    }

    public static EfectosArma obtener(Mineral metalBase, Modificadores mod, EstadosArmas forma) {
        return efectos.get(new ClaveEfecto(metalBase.tipo, mod ,forma));
    }
}
