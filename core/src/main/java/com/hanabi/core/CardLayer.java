package com.hanabi.core;

import static playn.core.PlayN.graphics;
import playn.core.GroupLayer;

import com.tuxlib.util.layer.LayerWrapper;

public class CardLayer extends LayerWrapper {
	
	public final float width;
	
	private final GroupLayer layer;
	
	private Card card;
	
	public CardLayer(Card card, float width) {
		super(graphics().createGroupLayer());
		layer = (GroupLayer) layerAddable();
		this.width = width;
		
		setCard(card);
		
	}
	
	public Card card() {
		return card;
	}
	
	public void setCard(Card card) {
		
	}

}
