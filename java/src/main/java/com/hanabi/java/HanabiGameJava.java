package com.hanabi.java;


import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.hanabi.core.HanabiGame;

public class HanabiGameJava {

	public static void main (String[] args) {
		JavaPlatform.Config config = new JavaPlatform.Config();
		config.width = 1000;
		config.height = 600;
		// use config to customize the Java platform, if needed
		JavaPlatform.register(config);
		PlayN.run(new HanabiGame());
	}
}
