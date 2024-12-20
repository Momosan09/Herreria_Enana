package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.enums.Items;
import com.mygdx.historia.TipoMision;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;

public class Mineral extends ObjetoDelMapa{
	
	public TipoMinerales tipo;
	public EstadosMinerales estado;
	public int vida = 100;
	public int valor=5;
	private boolean comprar = false, cerrar = false, comprable = false;
	private boolean dialogoAbierto = false;

	
	public Mineral(float x, float y, World world, boolean comprable, String rutaTextura, TipoMinerales tipo, EstadosMinerales estado, int ancho, int alto) {
		super(x, y, world, rutaTextura);
		this.comprable = comprable;
		this.tipo = tipo;
		this.estado = estado;
		crearCuerpo(world, ancho, alto);
	}

	
	public Mineral(float x, float y, boolean comprable, String rutaTextura, TipoMinerales tipo, EstadosMinerales estado) {
		super(x, y,comprable, rutaTextura);
		this.comprable = comprable;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Mineral( boolean comprable, String rutaTextura, TipoMinerales tipo, EstadosMinerales estado) {
		super(0, 0,comprable, rutaTextura);
		this.comprable = comprable;
		this.tipo = tipo;
		this.estado = estado;
	}

	protected void crearCuerpo(World world, int ancho, int alto) {//esto lo puedo sacar mas adelante, si le hago animacion a los minerales...
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posicion.x+16, posicion.y+alto);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ancho, alto);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
	}
	
	public void click(Jugador jugador) {
	    if (Gdx.input.isTouched()) {
	        float clickX = Gdx.input.getX();
	        float clickY = Gdx.input.getY();
	        Vector3 worldCoords = jugador.getCamara().unproject(new Vector3(clickX, clickY, 0));

	        if (this.getBoundingRectangle().contains(worldCoords.x, worldCoords.y)) {
	            // El toque está dentro del rango del mineral
	            this.vida -= 10;
	        }
	    }
	}

//	public void minar(Jugador jugador) {
//	    if ((getJugadorEnRango() && buscarPorItemEnJugador(Items.PICO))) {
//	    	System.out.println("Me mina");
//	        if (Gdx.input.isTouched()) {
//	            click(jugador);
//	        }
//
//	        if (this.vida <= 0) {
//	        	recolectar();
//	        }
//	    }
//	}

	
	private void recolectar() {
        System.out.println(HelpDebug.debub(getClass()) + "muerte");
        jugador.agregarMineral(this);
        OrganizadorSpritesIndiceZ.eliminarMineral(this);
        for(int i = 0; i<jugador.getMisiones().size();i++) {
        	if(jugador.getMisiones().get(i).getTipoMision() == TipoMision.RECOLECTAR) {
        		if(jugador.getMisiones().get(i).getObjeto().equals(this.tipo.toString())) {
        			jugador.getMisiones().get(i).setCantidadConseguida(+1);
        		}
        	}
        	
        }
	}
	
	public boolean comprar(Jugador jugador) {
	    if ((getJugadorEnRango() && isComprable()) && Gdx.input.isTouched()) {
	        // Cambiar el estado del diálogo
	        comprar = true;
	        return comprar;
	    }
	    return comprar;
	}
	
	public boolean getComprar() {
		return comprar;
	}

	 public boolean isComprable() {
	        return comprable;
	    }
	 public void abrirDialogo() {
		    dialogoAbierto = true;
		}

		public void cerrarDialogo() {
		    dialogoAbierto = false;
		}
		
	    public Rectangle getBoundingRectangle() {
	        return new Rectangle(posicion.x, posicion.y, textura.getWidth(), textura.getHeight());
	    }

		public TipoMinerales getTipoMineral() {
			return tipo;
		}
		public EstadosMinerales getEstadoMineral() {
			return estado;
		}
		
		public String getNombre() {
			String nombre = tipo.toString();
			return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
		}
	
		public String getEstado() {
			String estado = this.estado.toString();
			return estado.substring(0, 1).toUpperCase() + estado.substring(1).toLowerCase();
		}
}
