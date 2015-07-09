package com.hanabi.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import java.rmi.registry.LocateRegistry;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.Pointer.Event;
import playn.core.util.Clock;

import com.tuxlib.util.ImageCallback;
import com.tuxlib.util.Lerp;
import com.tuxlib.util.layer.LayerWrapper;

public class CardLayer extends LayerWrapper {
	
	public final float width;
	
	private final GroupLayer layer;
	private final ImageLayer cardBackLayer;
	
	private Card card;
	private boolean active;
	
	public float height() {
		return cardBackLayer.scaledHeight();
	}
	
	public CardLayer(Card card, float width) {
		super(graphics().createGroupLayer());
		layer = (GroupLayer) layerAddable();
		this.width = width;
		
		Image backImage = assets().getImage("images/back.png");
		cardBackLayer = graphics().createImageLayer(backImage);
		backImage.addCallback(new ImageCallback() {
			@Override
			public void onSuccess(Image result) {
				cardBackLayer.setScale(CardLayer.this.width / result.width());
				layer.setOrigin(CardLayer.this.width / 2, height() / 2);
			}
		});
		layer.add(cardBackLayer);
		
		cardBackLayer.addListener(new Pointer.Adapter() {			
			@Override
			public void onPointerEnd(Event event) {
				active = !active;
			}
		});
		
		setCard(card);
		
	}
	
	public Card card() {
		return card;
	}
	
	public void setCard(Card card) {
		
	}

	public void paint(Clock clock) {
		float targetScale = 1;
		if (active) targetScale = (float) (1 + Math.abs(1 - Math.sin(clock.time() / 1000 * Math.PI)) * 0.075f);
		layer.setScale(Lerp.lerp(layer.scaleX(), targetScale, 0.1f));
	}

}
