package com.hanabi.core;

import static playn.core.PlayN.graphics;

import java.util.LinkedList;
import java.util.List;

import playn.core.GroupLayer;
import playn.core.util.Clock;

import com.tuxlib.util.layer.GroupLayerWrapper;

public class GameLayer extends GroupLayerWrapper {

	public final float width, height;

	private final GroupLayer actionLayer;
	
	private final List<CardLayer> cardLayers = new LinkedList<CardLayer>();
	
	private final Hand hand;
	
	public GameLayer(int cards, float width, float height) {
		this.width = width;
		this.height = height;
		this.hand = new Hand(cards);
		
		float mWidth = width * 1f;
		float buttonWidth = mWidth * 0.25f, buttonHeight = height * 0.3f;
		float spacing = (mWidth - buttonWidth * 3) / 4;
		
		actionLayer = graphics().createGroupLayer();
		layer.add(actionLayer);
		
		Button buttonHint = new Button(buttonWidth, buttonHeight, "Hint!");
		buttonHint.setTranslation(spacing + buttonWidth * 0.5f, height * 0.25f);
		actionLayer.add(buttonHint.layerAddable());
		
		Button buttonPlay = new Button(buttonWidth, buttonHeight, "Play!");
		buttonPlay.setTranslation(2 * spacing + buttonWidth * 1.5f, height * 0.25f);
		actionLayer.add(buttonPlay.layerAddable());

		Button buttonUndo = new Button(buttonWidth, buttonHeight * 0.45f, "Undo");
		buttonUndo.setTranslation(3 * spacing + buttonWidth * 2.5f, height * 0.25f - buttonHeight * 0.275f);
		actionLayer.add(buttonUndo.layerAddable());
		
		Button buttonRedo = new Button(buttonWidth, buttonHeight * 0.45f, "Redo");
		buttonRedo.setTranslation(3 * spacing + buttonWidth * 2.5f, height * 0.25f + buttonHeight * 0.275f);
		actionLayer.add(buttonRedo.layerAddable());
		
		reset();
	}

	private void reset() {
		for (CardLayer cl : cardLayers) cl.destroy();
		cardLayers.clear();
		
		float cardsWidth = width;
		float cWidth = cardsWidth * 0.8f / hand.size;
		for (int i = 0; i < hand.size; i++) {
			CardLayer cl = new CardLayer(hand.cards[i], cWidth);
			cl.setTranslation(cardsWidth * (i + 0.5f) / hand.size, height * 0.75f);
			layer.add(cl.layerAddable());
			cardLayers.add(cl);
		}
	}

	public void paint(Clock clock) {
		for (CardLayer layer : cardLayers) {
			layer.paint(clock);
		}
	}

	
	
}
