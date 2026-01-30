package com.mygdx.utiles.particulas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.recursos.Recursos;

public class ParticulasManager {
	
	private TextureAtlas atlas;
	private ParticleEffect efectoMinarPiedra;
	
    private final ObjectMap<ListaDeParticulas, ParticleEffect> prototipos = new ObjectMap<>();
    private final Array<ParticleEffect> activos = new Array<>();
	

    private static ParticulasManager instance;
	
	public ParticulasManager(){
		
		atlas = new TextureAtlas(Gdx.files.internal(Recursos.particulas.ATLAS));
		
		//Minar piedra
		cargar(ListaDeParticulas.MINADO_PIEDRA);
	
	}
	
    private void cargar(ListaDeParticulas tipo) {
        ParticleEffect efecto = new ParticleEffect();
        efecto.load(Gdx.files.internal(tipo.ruta), atlas);
        prototipos.put(tipo, efecto);
    }

    /**
     * 
     * @param tipo Elegir el efecto de la lista
     * @param x	Definir posicion X
     * @param y Definir posicion Y
     * @param continuo El efecto se repite indefinidamente
     */
    public void spawn(ListaDeParticulas tipo, float x, float y, boolean continuo) {
        ParticleEffect efecto = new ParticleEffect(prototipos.get(tipo));

        if(!continuo) {        	
        for (ParticleEmitter emitter : efecto.getEmitters()) {
            emitter.setContinuous(false);
        }
        }

        efecto.setPosition(x, y);
        efecto.start();
        activos.add(efecto);
    }
    

    public void updateAndDraw() {
    	if(Config.permitirParticulas) {
    		
        float delta = Gdx.graphics.getDeltaTime();

        for (int i = activos.size - 1; i >= 0; i--) {
            ParticleEffect e = activos.get(i);
            e.update(delta);
            e.draw(Render.batch);

            if (e.isComplete()) {
                e.dispose();
                activos.removeIndex(i);
            }
        }
    	}
    }

    public static ParticulasManager get() {
        if (instance == null) instance = new ParticulasManager();
        return instance;
    }
    
    public void dispose() {
        atlas.dispose();
        for (ParticleEffect e : activos) e.dispose();
    }
	
}
