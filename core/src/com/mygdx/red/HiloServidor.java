package com.mygdx.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.mygdx.enums.Direcciones;
import com.mygdx.pantallas.Juego;
import com.mygdx.utiles.ConsolaDebug;
import com.mygdx.utiles.HelpDebug;

public class HiloServidor extends Thread{

	private DatagramSocket socket;
	private boolean fin = false;
	private int cantConexiones = 0, maxConexiones = 2;
	private JugadorRed[] jugadores;
	private Juego juego;

	
	private ConsolaDebug consola;
	
	public HiloServidor(Juego juego, ConsolaDebug consola) {
		jugadores = new JugadorRed[maxConexiones];
		this.juego = juego;
		try {
			this.consola = consola;
			socket = new DatagramSocket(35323);
			System.out.println(HelpDebug.debub(getClass())+"servidor iniciado");
			consola.agregarMensajes("Servidor iniciado");

		} catch (SocketException e) {
			fin = true;
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
	    while (!fin) {
	        byte[] datos = new byte[1024];
	        DatagramPacket dp = new DatagramPacket(datos, datos.length);
	        System.out.println(HelpDebug.debub(getClass())+"Esperando mensaje...");
	        try {
	            socket.receive(dp);
	            procesarMensaje(dp);
	        } catch (IOException e) {
	            //e.printStackTrace();
	        }
	    }
	    System.out.println(HelpDebug.debub(getClass())+"Hilo del servidor finalizado."); // Agrega un mensaje aquí
	}


	private void procesarMensaje(DatagramPacket dp) {
		String msg = new String(dp.getData()).trim();//trim() lo que hace es sacar los espacios
		String[] mensajeCompuesto = msg.split("#");
	
		System.out.println(HelpDebug.debub(getClass())+"mensaje de longitud " + mensajeCompuesto.length);
		

		

		System.out.println(HelpDebug.debub(getClass())+mensajeCompuesto[0]);
		switch(mensajeCompuesto[0]) {
		case "conectar":
			cantConexiones++;
			//System.out.println(cantConexiones);
			if(cantConexiones == 1) {
				jugadores[0] = new JugadorRed(juego.getJugador1(), dp.getAddress(), dp.getPort());
				consola.agregarMensajes("Conectado: " + dp.getAddress()+" "+ dp.getPort());
				enviarMensaje("conexion_exitosa#"+0, jugadores[0].ip, jugadores[0].puerto);
				
			}else if(cantConexiones == 2){
				jugadores[1] = new JugadorRed(juego.getJugador2(), dp.getAddress(),dp.getPort());
				consola.agregarMensajes("Conectado: " + dp.getAddress()+" "+ dp.getPort());
				enviarMensaje("conexion_exitosa#"+1, jugadores[1].ip, jugadores[1].puerto);
				enviarMensaje("jugadores_listos");
			}else {
				consola.agregarMensajes("Sala llena");
				enviarMensaje("servidor_lleno", dp.getAddress(), dp.getPort());
			}
			break;
		case "desconectar":{
			if(cantConexiones>0) {//esto es para que no se "desconecten mas de la cuenta" y me quede la variable en negativo
				consola.agregarMensajes("Jugador desconectado");
				cantConexiones--;
			if(cantConexiones == 1) {//si la cantidad de conexiones es uno y alguien se desconecta, se eliminan los datos
				//consola.agregarMensajes("Jugador desconectado");
				jugadores[0].ip = null;
				jugadores[0].puerto = -1;
				jugadores[0] = null;
			
			}else if(cantConexiones == 2) {	//si hay dos jugadores
			if(dp.getAddress().equals(jugadores[1].ip) && dp.getPort() == jugadores[1].puerto) {//si la ip del paquete enviado es igual a la del jugador 1, entonces se le avisa al jugador 0
				enviarMensaje("jugador_desconectado", jugadores[0].ip, jugadores[0].puerto);
				jugadores[1] = null;
			//enviarMensaje("jugador_desconectado", ip[0],puerto[0]);
				}else {// Sino el jugador 2 pasa a ser el 1 y el 2 se borra 
					jugadores[0].ip = jugadores[1].ip;
					jugadores[0].puerto = jugadores[1].puerto;
					jugadores[1] = null;
				}
			}
			}
		}
			
		case "direccion":
			if(cantConexiones >=2) {

				int nroJugador = -1;
				for (int i = 0; i < jugadores.length; i++) {
				    if (dp.getPort() == jugadores[i].puerto && dp.getAddress().equals(jugadores[i].ip)) {
				        nroJugador = i;
				        break;
				    }
				}

				System.out.println("El puerto del jugador 0 es = " + jugadores[0].puerto);
				System.out.println("El puerto del jugador 1 es = " + jugadores[1].puerto);
				System.out.println("El puerto del paquete es = " + dp.getPort());
			
			switch (mensajeCompuesto[1]) {
			case "arriba":
				System.out.println("EL JUGADOR " + nroJugador + " Va a arriba");
				
				//if(nroCliente == 0) {
					jugadores[nroJugador].getEntidadJugador().movimiento(Direcciones.ARRIBA);
				//}
				break;
			case "abajo":
				System.out.println("abajo");
				jugadores[nroJugador].getEntidadJugador().movimiento(Direcciones.ABAJO);
				break;
			case "izquierda":
				System.out.println("izquierda");
				jugadores[nroJugador].getEntidadJugador().movimiento(Direcciones.IZQUIERDA);
				break;
			case "derecha":
				System.out.println("derecha");
				jugadores[nroJugador].getEntidadJugador().movimiento(Direcciones.DERECHA);
				break;
			}
			enviarMensaje("actualizar_posicion#"+nroJugador+"#"+
			+jugadores[nroJugador].getEntidadJugador().posicion.x
			+"#"
			+jugadores[nroJugador].getEntidadJugador().posicion.y);
			
			}
			}
		}
			
	
	
	public void enviarMensaje(String msg, InetAddress ipDestino, int puerto) {
		byte[] mensaje = msg.getBytes();
		try {
			DatagramPacket dp = new DatagramPacket(mensaje, mensaje.length, ipDestino, puerto);
			socket.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(String msg) {//Manda mensaje a todos los clientes
		byte[] mensaje = msg.getBytes();
		try {
			for(int i = 0; i<jugadores.length;i++) {				
			DatagramPacket dp = new DatagramPacket(mensaje, mensaje.length, jugadores[i].ip, jugadores[i].puerto);
			socket.send(dp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void fin() {
		fin = true;
		socket.close();
	}
	


}

