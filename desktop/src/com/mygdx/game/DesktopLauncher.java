package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Principal;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		/*
		Monitor currMonitor = Gdx.graphics.getMonitor();
		DisplayMode displayMode = Gdx.graphics.getDisplayMode(currMonitor);
		*/



		config.setForegroundFPS(60);
		config.setTitle("HerreriaEnana");
		config.setIdleFPS(0);
		config.setDecorated(true);
		config.setWindowedMode(1280, 768);



		
		new Lwjgl3Application(new Principal(), config);
	}
}
