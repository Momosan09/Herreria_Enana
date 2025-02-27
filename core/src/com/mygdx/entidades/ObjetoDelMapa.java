package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.eventos.EventoInteraccionObj;
import com.mygdx.eventos.Listeners;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;

public abstract class ObjetoDelMapa extends Entidad{

	protected Jugador jugador;
	
	public ObjetoDelMapa(float x, float y, World world, String rutaTextura, Jugador jugador) {
		super(x, y, world,rutaTextura);
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);
		OrganizadorSpritesIndiceZ.objetosDelMapa.add(this);
		this.jugador = jugador;
		//crearCuerpo(world, sprite.getWidth()-5, 3);
	}
	
	public ObjetoDelMapa(float x, float y, World world, String rutaTextura) {
		super(x, y, world,rutaTextura);
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);
		OrganizadorSpritesIndiceZ.objetosDelMapa.add(this);
		//crearCuerpo(world, sprite.getWidth()-5, 3);
	}
	
	
	public ObjetoDelMapa(float x, float y, boolean comprable, String rutaTextura) {
		super(comprable,rutaTextura);
		//OrganizadorSpritesIndiceZ.objetosDelMapa.add(this);
		}


	public ObjetoDelMapa( String rutaTextura) {
		super(rutaTextura);
	}

	

	
	


	
	
}
