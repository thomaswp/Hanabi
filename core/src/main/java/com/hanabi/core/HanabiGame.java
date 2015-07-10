package com.hanabi.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.ImageLayer;

import com.tuxlib.util.SolidClock;

public class HanabiGame extends Game.Default {
	
	private SolidClock clock = new SolidClock(33);
	private GameLayer gameLayer;

	public HanabiGame () {
		super(33); // update our "simulation" 33ms (30 times per second)
	}

	@Override
	public void init() {

		ImageLayer bg = graphics().createImageLayer();
		bg.setImage(assets().getImage("images/bg.png"));
		bg.setSize(graphics().width(), graphics().height());
		graphics().rootLayer().add(bg);

		gameLayer = new GameLayer(5 , graphics().width(), graphics().height());
		graphics().rootLayer().add(gameLayer.layerAddable());

	}

	@Override
	public void update(int delta) {
		super.update(delta);
		clock.update(delta);
	}
	
	
	@Override
	public void paint(float alpha) {
		super.paint(alpha);
		clock.paint(alpha);
		gameLayer.paint(clock);
	}
}
