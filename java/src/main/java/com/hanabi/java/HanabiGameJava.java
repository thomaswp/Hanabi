package com.hanabi.java;

import playn.java.LWJGLPlatform;

import com.hanabi.core.HanabiGame;

public class HanabiGameJava {

  public static void main (String[] args) {
    LWJGLPlatform.Config config = new LWJGLPlatform.Config();
    // use config to customize the Java platform, if needed
    LWJGLPlatform plat = new LWJGLPlatform(config);
    new HanabiGame(plat);
    plat.start();
  }
}
