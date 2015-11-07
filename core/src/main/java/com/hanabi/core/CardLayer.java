package com.hanabi.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.Pointer.Event;
import playn.core.util.Clock;
import pythagoras.f.Point;

import com.hanabi.core.data.Card;
import com.tuxlib.util.ImageCallback;
import com.tuxlib.util.Lerp;
import com.tuxlib.util.layer.GroupLayerWrapper;

public class CardLayer extends GroupLayerWrapper {
	
	public final static float ASPECT_RATIO = 1.55f;
	
	public final float width;
	public final float height;
	
	private final ImageLayer cardBackLayer;

	public Runnable onActiveChanged;
	public boolean active;
	
	private Card card;
	
	public Card card() {
		return card;
	}
	
	public void setCard(Card card) {
		
	}
	
	public CardLayer(Card card, float width) {
		this.width = width;
		this.height = width * ASPECT_RATIO;
		
		Image backImage = assets().getImage("images/back.png");
		cardBackLayer = graphics().createImageLayer(backImage);
		layer.setOrigin(width / 2, height / 2);
		
		backImage.addCallback(new ImageCallback() {
			@Override
			public void onSuccess(Image result) {
				cardBackLayer.setScale(CardLayer.this.width / result.width());
			}
		});
		layer.add(cardBackLayer);
		
		cardBackLayer.addListener(new Pointer.Adapter() {			
			@Override
			public void onPointerEnd(Event event) {
				if (cardBackLayer.hitTest(new Point(event.localX(), event.localY())) != cardBackLayer) {
					return;
				}
				active = !active;
				if (onActiveChanged != null) {
					onActiveChanged.run();
				}
			}
		});
		
		setCard(card);
		
	}

	public void paint(Clock clock) {
		float targetScale = active ? 1.1f : 1;
		layer.setScale(Lerp.lerp(layer.scaleX(), targetScale, 0.1f));
	}

}
