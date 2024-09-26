package com.mygdx.utiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.enums.ColorLuz;
import com.mygdx.enums.Luz;
import com.mygdx.eventos.EventoCambioDeDia;
import com.mygdx.eventos.Listeners;
import com.mygdx.historia.CartasManager;
import com.mygdx.hud.CartaHUD;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Iluminacion implements EventoCambioDeDia{
    
    private World world;
    private PointLight pl, pl2;
    private Luz estadoLuz;
    private ColorLuz colorLuz;
    
    private float valorLuzAmbiental, topeLuzAmbiental;
    private long minutoDelMundo = 1, horaDelMundo = 4; // Inicia a las 6:00 AM en el mundo virtual
    private int diaDelMundo = 3;
    private long ultimoTiempoActualizacion = 0; // Control para actualizar el tiempo correctamente
    
    public Iluminacion(World world, OrthographicCamera camaraJugador) {
        this.world = world;
        determinarDia(diaDelMundo);
        Render.rayHandler.setCombinedMatrix(camaraJugador);
        
        Render.rayHandler.setBlurNum(3);
        Render.rayHandler.setShadows(true);
        pl = new PointLight( Render.rayHandler, 128, new Color(Color.valueOf("#ea8e0e")), 300, 43*32, 55*32);
        pl.setStaticLight(false);
        pl.setSoft(true);
        
        pl2 = new PointLight( Render.rayHandler, 128, new Color(Color.valueOf("#ef9413")), 90, 35.5f*32, 57.5f*32);
        pl2.setStaticLight(false);
        pl2.setSoft(false);
        Render.rayHandler.setCulling(false); //si lo pongo en true no anda la luz, pero deberia estar en true...
    
        Listeners.agregarListener(this);
        Listeners.cambioDeDia();//Esto tiene que estar aca para los eventos del primer dia

    }

    public void setCombinedMatrix(OrthographicCamera camaraJugador) {
    	 Render.rayHandler.setCombinedMatrix(camaraJugador);
    }

    public void setCombinedMatrix(Matrix4 combined, int i, int j, int k, int l) {
    	 Render.rayHandler.setCombinedMatrix(combined, k, l, i, i);
    }

    private void update() {
    	 Render.rayHandler.update();
        tiempo();
        diaYNocheLuz();        
    }

    public void render(OrthographicCamera camaraJugador) {
        update();
        Render.rayHandler.setCombinedMatrix(camaraJugador.combined,0,0,1,1);
        Render.rayHandler.render();
    }
    
    private void tiempo() { 
        long segundosJuegoActivo = Tiempo.getSegundosEnEstadoJuego(); // Segundos desde que comenzó el juego activo
        
        // Verificar si ha pasado al menos un segundo real
        if (segundosJuegoActivo > ultimoTiempoActualizacion) {
            long segundosTranscurridos = segundosJuegoActivo - ultimoTiempoActualizacion; // Segundos reales que pasaron desde la última actualización
            
            // Incrementa el tiempo del mundo virtual basado en el tiempo real transcurrido
            minutoDelMundo += segundosTranscurridos; // Un segundo real equivale a un minuto virtual
            // Si los minutos del mundo superan 60, incrementa la hora del mundo
            if (minutoDelMundo >= 60) {
                horaDelMundo++;
                minutoDelMundo = 0;
            }
            revisarCartas();//Revisa si hay cartas a cada minuto, quizas esto sea mucho y con que se fije en el dia ya esta...

            // Si las horas del mundo superan 24, incrementa el día del mundo
            if (horaDelMundo >= 24) {
        		horaDelMundo = 0;
                diaDelMundo++;
                MundoConfig.diasTranscurridos ++;
                MundoConfig.diaDelMundo = diaDelMundo;
            	Listeners.cambioDeDia();
            }
            
            // Si los días del mundo superan 7, reinicia el ciclo semanal
            if (diaDelMundo > 7) {
                diaDelMundo = 1;
            }

            // Guardar el tiempo de actualización más reciente
            ultimoTiempoActualizacion = segundosJuegoActivo;

            // Actualizar la configuración del mundo
            MundoConfig.minutoDelMundo = minutoDelMundo;
            MundoConfig.horaDelMundo = horaDelMundo;

            // Imprimir el tiempo virtual para debugging
           // System.out.println(horaDelMundo + "Hs " + minutoDelMundo + "mins ");
            //determinarDia(diaDelMundo);
        }
    }
    
    private void diaYNocheLuz() {
        if (horaDelMundo >= 0 && horaDelMundo < 4f) {
            topeLuzAmbiental = .2f;
            estadoLuz = Luz.MANTENER;
            colorLuz = ColorLuz.MADRUGADA;
        } else if (horaDelMundo >= 4f && horaDelMundo < 6f) {
            topeLuzAmbiental = .3f;
            estadoLuz = Luz.INCREMENTAR;
            colorLuz = ColorLuz.CREPUSCULO;
        } else if (horaDelMundo >= 6 && horaDelMundo < 8) {
            topeLuzAmbiental = .7f;
            estadoLuz = Luz.INCREMENTAR;
            colorLuz = ColorLuz.DIA;
        } else if (horaDelMundo >= 8 && horaDelMundo < 16) {
            topeLuzAmbiental = .8f;
            estadoLuz = Luz.INCREMENTAR;
            colorLuz = ColorLuz.DIA;
        } else if (horaDelMundo >= 16 && horaDelMundo < 18) {
            topeLuzAmbiental = .6f;
            estadoLuz = Luz.DISMINUIR;
            colorLuz = ColorLuz.CREPUSCULO;
        } else if (horaDelMundo >= 18 && horaDelMundo < 20) {
            topeLuzAmbiental = .4f;
            estadoLuz = Luz.DISMINUIR;
            colorLuz = ColorLuz.NOCHE;
        } else if (horaDelMundo >= 20 && horaDelMundo <= 24) {
            topeLuzAmbiental = .3f;
            estadoLuz = Luz.DISMINUIR;
            colorLuz = ColorLuz.NOCHE;
        }

        // Actualizar la luz ambiental
        if (estadoLuz == Luz.INCREMENTAR && valorLuzAmbiental < topeLuzAmbiental) {
            valorLuzAmbiental += .001f;
        } else if (estadoLuz == Luz.DISMINUIR && valorLuzAmbiental > topeLuzAmbiental) {
            valorLuzAmbiental -= .001f;
        }

        Render.rayHandler.setAmbientLight(valorLuzAmbiental);
    }
    
	private void determinarDia(int dia) {
		switch (dia) {
		case 1:
			MundoConfig.dia = Recursos.bundle.get("dia.1");
			break;
		case 2:
			MundoConfig.dia = Recursos.bundle.get("dia.2");
			break;
		case 3:
			MundoConfig.dia = Recursos.bundle.get("dia.3");
			break;
		case 4:
			MundoConfig.dia = Recursos.bundle.get("dia.4");
			break;
		case 5:
			MundoConfig.dia = Recursos.bundle.get("dia.5");
			break;
		case 6:
			MundoConfig.dia = Recursos.bundle.get("dia.6");
			break;
		case 7:
			MundoConfig.dia = Recursos.bundle.get("dia.7");
			break;
		}
	}


	@Override
	public void cambioDeDia() { //aca va todo lo que pasa cuando cambia de dia
        determinarDia(diaDelMundo);

        //esto ahora parece no tener sentido, pero cuando tenga mas eventos y, especialmente, unicos del dia cero voy a poder elegir los que se ejecutan y los que no
        if(diaDelMundo == 0) { // esto es para las particularidades del dia cero
        	revisarCartas();
        }if(diaDelMundo == 1){ // esto para el dia 1 y asi ir agregando conforme necesite, quizas un dia no se revisen cartas y eso
        
        }else {//aca es el comportamineto general
        	revisarCartas();        	
        }
        
	}
	
	/**
	Revisa si hay cartas para para ese dia
	@param the parameters used by the method
	@return the value returned by the method
	@throws what kind of exception does this method throw
	*/
	public void revisarCartas() {
        //Ver si hay carta para ese dia
        CartaHUD cartaDelDia = CartasManager.determinarCarta();
        if(cartaDelDia != null) {
        	MundoConfig.cartaAMostrar = cartaDelDia;
        	Listeners.recibirCarta(MundoConfig.cartaAMostrar);    
        }else {
        	MundoConfig.cartaAMostrar = null;
        	//System.out.println("No hay carta para hoy \n");
        }
	}
}
