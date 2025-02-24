package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
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
import com.mygdx.eventos.EventoMinar;
import com.mygdx.eventos.Listeners;
import com.mygdx.historia.TipoMision;
import com.mygdx.historia.misiones.MisionRecFab;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;

public class Mineral extends ObjetoDelMapa implements EventoMinar{
	
	public TipoMinerales tipo;
	public EstadosMinerales estado;
	public int vida = 100;
	public int valor = 5;
	private boolean comprar = false, cerrar = false, comprable = false;
	private Circle areaMinado;//Es el area en el que el jugador puede minar el mineral, mas grande que el area de interaccion la cual es en donde el jugador debe poner el cursor para minar

	
	public Mineral(float x, float y, World world, boolean comprable, TipoMinerales tipo, EstadosMinerales estado, int ancho, int alto) {
		super(x, y, world, tipo.ruta + estado.ruta);
		this.comprable = comprable;
		this.tipo = tipo;
		this.estado = estado;
		this.areaMinado = new Circle(this.posicion.x, this.posicion.y, 64);
		crearCuerpo(world, ancho, alto);
		Listeners.agregarListener(this);
	}

	
	public Mineral(float x, float y, boolean comprable, TipoMinerales tipo, EstadosMinerales estado) {
		super(x, y,comprable, tipo.ruta + estado.ruta);
		this.comprable = comprable;
		this.tipo = tipo;
		this.estado = estado;
		this.areaMinado = new Circle(this.posicion.x, this.posicion.y, 64);
		Listeners.agregarListener(this);
	}

	public Mineral(boolean comprable, TipoMinerales tipo, EstadosMinerales estado) {
		super(tipo.ruta + estado.ruta);
		this.comprable = comprable;
		this.tipo = tipo;
		this.estado = estado;
		this.areaMinado = new Circle(this.posicion.x, this.posicion.y, 64);
		Listeners.agregarListener(this);
	}
	/**
	 * Este Es para los que nunca van a salir al mundo, es decir, se compra o aparece al inicio del juego
	 * @param rutaTextura
	 */
	
	public Mineral(TipoMinerales tipo, EstadosMinerales estado) {
		super(tipo.ruta+estado.ruta);
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

//	    public Rectangle getBoundingRectangle() {
//	        return new Rectangle(posicion.x, posicion.y, textura.getWidth(), textura.getHeight());
//	    }

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


		/**
		 * verdadero si el jugador esta dentro del area de minado
		 * @param j
		 */
		private boolean getJugadorRangoMinado(Jugador j) {
			if(areaMinado.overlaps(j.areaJugador)) {
				return true;
			}else {
				return false;
			}
		}
		
		
		private void recolectar(Jugador j) {
			if(vida <= 0) {	
	        System.out.println(HelpDebug.debub(getClass()) + "muerte");
	        j.agregarMineral(this);

	        for(int i = 0; i<j.getMisiones().size();i++) {
	        	if(j.getMisiones().get(i).getTipo() == TipoMision.RECOLECTAR) {
	        		MisionRecFab m = (MisionRecFab) j.getMisiones().get(i);
	        		if(m.getObjeto().equals(this.tipo.toString())) {
	        			j.avanzarMision(m);
	        		}
	        	}        	
	        }
	        OrganizadorSpritesIndiceZ.eliminarMineral(this);
			}
		}
		
		@Override
		public void minar(Jugador j, int x, int y) {
			if(vida > 0) { //evita que el mineral se pueda seguir minando post mortem
			 Vector3 worldCoords = j.getCamara().unproject(new Vector3(x, y, 0));
			if(getJugadorRangoMinado(j)) {
		        if (this.areaDeInteraccion.contains(worldCoords.x, worldCoords.y)) {
		            // El toque está dentro del rango del mineral
					vida -= 50;
					recolectar(j);
					//j.getCamara().update();
		        }
			}
			}
		}
		
		public void dibujarAreaDeMinado() {
			dibujarAreasInteraccion(areaMinado, Colores.ROSA_DEBUG);
		}
}
