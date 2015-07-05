package com.hanabi.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;

public class HanabiGame extends Game.Default {

  public HanabiGame () {
    super(33); // update our "simulation" 33ms (30 times per second)
  }

	@Override
	public void init() {

	    // create and add background image layer
	    Image bgImage = assets().getImage("images/bg.png");
	    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
	    // scale the background to fill the screen
	    bgLayer.setSize(graphics().width(), graphics().height());
	    graphics().rootLayer().add(bgLayer);
		
	}
}
