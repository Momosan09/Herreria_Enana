package red;

import com.mygdx.pantallas.Juego;
import com.mygdx.utiles.ConsolaDebug;

public class Servidor {

	private HiloServidor hs;
	
	public Servidor(Juego game, ConsolaDebug consola) {
		hs = new HiloServidor(game,consola);
		hs.start();

	}
	
	public void cerrarHilo() {
		hs.fin();//sacar de aca
	   hs.cerrarSocket();
	   hs.interrupt();
	}
}
