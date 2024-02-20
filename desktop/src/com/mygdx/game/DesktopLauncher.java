package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.utiles.CrashReport;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		/*
		 * Monitor currMonitor = Gdx.graphics.getMonitor(); DisplayMode displayMode =
		 * Gdx.graphics.getDisplayMode(currMonitor);
		 */

		config.setForegroundFPS(60);
		config.setTitle("HerreriaEnana");
		config.setIdleFPS(0);
		config.setDecorated(true);
		// config.setMaximized(true);
		config.setWindowedMode(1280, 720);

		try {
			new Lwjgl3Application(new Principal(), config);
		} catch (Exception e) {
			e.printStackTrace();
			CrashReport.report(e);
		}

	}
}
