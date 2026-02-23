package com.mygdx.armas.modificadores;

import com.mygdx.armas.EstadosArmas;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;

public record ClaveEfecto(TipoMinerales metalBase, Modificadores modificador, EstadosArmas forma) {}
