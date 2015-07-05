package com.hanabi.core;

import static playn.core.PlayN.graphics;

import java.util.LinkedList;
import java.util.List;

import playn.core.GroupLayer;

import com.tuxlib.util.layer.LayerWrapper;

public class GameLayer extends LayerWrapper {

	public final float width, height;
	
	private final GroupLayer layer;
	private final List<CardLayer> cardLayers = new LinkedList<CardLayer>();
	
	private final Hand hand;
	
	public GameLayer(int cards, float width, float height) {
		super(graphics().createGroupLayer());
		layer = (GroupLayer) layerAddable();
		
		this.width = width;
		this.height = height;
		this.hand = new Hand(cards);
		
		reset();
	}

	private void reset() {
		for (CardLayer cl : cardLayers) cl.destroy();
		cardLayers.clear();
		
		float cardsWidth = width * 0.8f;
		float cWidth = cardsWidth * 0.8f / hand.size;
		for (int i = 0; i < hand.size; i++) {
			CardLayer cl = new CardLayer(hand.cards[i], cWidth);
			cl.setTranslation(cardsWidth * (i + 0.5f) / hand.size, height / 2);
		}
	}

	
	
}
