package com.mygdx.entidades.npcs.dialogos.charlas;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.mygdx.entidades.npcs.dialogos.charlas.condiciones.CondicionHora;
import com.mygdx.entidades.npcs.dialogos.charlas.condiciones.CondicionLluvia;
import com.mygdx.entidades.npcs.dialogos.charlas.consecuencias.ConsecuenciaAsignarMision;
import com.mygdx.entidades.npcs.dialogos.charlas.consecuencias.ConsecuenciaIniciarCharla;
import com.mygdx.entidades.npcs.dialogos.charlas.dto.CharlaDTO;
import com.mygdx.entidades.npcs.dialogos.charlas.dto.CondicionDTO;
import com.mygdx.entidades.npcs.dialogos.charlas.dto.ConsecuenciaDTO;
import com.mygdx.entidades.npcs.dialogos.charlas.dto.RespuestaDTO;

public class DialogoLoader {

	public static List<Charla> cargar(String ruta) {

	    Json json = new Json();

	    Array<CharlaDTO> dtos = json.fromJson(Array.class, CharlaDTO.class, Gdx.files.internal(ruta));

	    if (dtos == null) {
	        throw new RuntimeException("No se pudieron cargar diálogos desde: " + ruta);
	    }

	    List<Charla> resultado = new ArrayList<>();

	    for (CharlaDTO dto : dtos) {
	        resultado.add(convertir(dto));
	    }

	    return resultado;
	}


    private static Charla convertir(CharlaDTO dto) {

        List<Condicion> condiciones = new ArrayList<>();

        if (dto.condiciones != null) {
            for (CondicionDTO c : dto.condiciones) {
                condiciones.add(crearCondicion(c));
            }
        }

        List<Respuesta> respuestas = new ArrayList<>();

        if (dto.respuestas != null) {
            for (RespuestaDTO r : dto.respuestas) {
                respuestas.add(
                    new Respuesta(
                        r.texto,
                        crearConsecuencia(r.consecuencia)
                    )
                );
            }
        }

        return new Charla(dto.id, dto.monologo, condiciones, respuestas);
    }

    private static Condicion crearCondicion(CondicionDTO dto) {
        switch (dto.tipo) {
            case "lluvia":
                return new CondicionLluvia(dto.valor);
            case "hora":
                return new CondicionHora(dto.min, dto.max);
            default:
                throw new RuntimeException("Condición desconocida: " + dto.tipo);
        }
    }

    private static Consecuencia crearConsecuencia(ConsecuenciaDTO dto) {

        if (dto == null) return (mundo, npc, jugador) -> {};

        switch (dto.tipo) {
            case "irA":
                return new ConsecuenciaIniciarCharla(dto.id);
                
            case "agregarMision":
            	//return new ConsecuenciaAsignarMision(dto.id);
            case "nada":
                return (mundo, npc, jugador) -> {};
            default:
                throw new RuntimeException("Consecuencia desconocida: " + dto.tipo);
        }
    }
}
