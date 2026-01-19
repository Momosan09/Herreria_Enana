package com.mygdx.utiles.particulas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.recursos.Recursos;

public class ParticulasManager {
	
	private TextureAtlas atlas;
	
    private final ObjectMap<ListaDeParticulas, ParticleEffect> prototipos = new ObjectMap<>();
    private final Array<ParticleEffect> activos = new Array<>();
	

    private static ParticulasManager instance;
	
	public ParticulasManager(){
		
		atlas = new TextureAtlas(Gdx.files.internal(Recursos.particulas.ATLAS));
		
		//Minar piedra
		cargar(ListaDeParticulas.MINADO_PIEDRA);
		cargar(ListaDeParticulas.FUEGO);
	
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
    	if(!estaEnPantalla(x, y, 64f)) return; // Si no esta visible no se crea TODO aca puede haber un problema 
    	
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
    	if(!Config.permitirParticulas) return;
    	
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

    public static ParticulasManager get() {
        if (instance == null) instance = new ParticulasManager();
        return instance;
    }
    
    public void dispose() {
        atlas.dispose();
        for (ParticleEffect e : activos) e.dispose();
    }
	
	 
    private boolean estaEnPantalla(float x, float y, float radio) {
        OrthographicCamera cam = MundoConfig.camaraJugador;

        return cam.frustum.boundsInFrustum(
            x, y, 0,
            radio, radio, 0
        );
    }

    
}
