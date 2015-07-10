package com.hanabi.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.Pointer.Event;
import playn.core.util.Clock;

import com.tuxlib.util.ImageCallback;
import com.tuxlib.util.Lerp;
import com.tuxlib.util.layer.GroupLayerWrapper;

public class CardLayer extends GroupLayerWrapper {
	
	public final float width;
	
	private final ImageLayer cardBackLayer;
	
	private Card card;
	private boolean active;
	
	public float height() {
		return cardBackLayer.scaledHeight();
	}
	
	public CardLayer(Card card, float width) {
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
		float targetScale = active ? 1.1f : 1;
		layer.setScale(Lerp.lerp(layer.scaleX(), targetScale, 0.1f));
	}

}
