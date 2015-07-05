package com.hanabi.core;

import static playn.core.PlayN.graphics;
import playn.core.Game;

public class HanabiGame extends Game.Default {

  public HanabiGame () {
    super(33); // update our "simulation" 33ms (30 times per second)
  }

	@Override
	public void init() {

		GameLayer gameLayer = new GameLayer(4, graphics().width(), graphics().height());
		graphics().rootLayer().add(gameLayer.layerAddable());
		
	}
}
