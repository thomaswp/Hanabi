package com.hanabi.core;

import static playn.core.PlayN.graphics;

import java.util.LinkedList;
import java.util.List;

import playn.core.GroupLayer;
import playn.core.util.Clock;

import com.hanabi.core.data.Hand;
import com.tuxlib.util.layer.GroupLayerWrapper;

public class GameLayer extends GroupLayerWrapper {

	public final float width, height;

	private final GroupLayer actionLayer, cardsLayer;
	
	private final List<CardLayer> cardLayers = new LinkedList<CardLayer>();
	
	private final Hand hand;
	private Button buttonHintGo, buttonPlayCancel;
	
	private float cardHeight;
	
	private boolean hinting, playing;
	
	public GameLayer(int cards, float width, float height) {
		this.width = width;
		this.height = height;
		this.hand = new Hand(cards);
		
		actionLayer = graphics().createGroupLayer();
		layer.add(actionLayer);
		
		cardsLayer = graphics().createGroupLayer();
		layer.add(cardsLayer);

		reset();
		createActionButtons(width, height);
	}

	private void createActionButtons(float width, float height) {
		float mWidth = width * 1f;
		float workingHeight = height * 0.95f - cardHeight;
		float midPoint = workingHeight * 0.5f;
		float buttonWidth = mWidth * 0.25f, buttonHeight = height * 0.3f;
		float spacing = (mWidth - buttonWidth * 3) / 4;
		
		buttonHintGo = new Button(buttonWidth, buttonHeight, "Hint!");
		buttonHintGo.setTranslation(spacing + buttonWidth * 0.5f, midPoint);
		buttonHintGo.onClicked = new Runnable() {
			@Override
			public void run() {
				if (hinting) finishHinting();
				else if (playing) finishPlaying();
				else doHint();
			}
		};
		actionLayer.add(buttonHintGo.layerAddable());
		
		buttonPlayCancel = new Button(buttonWidth, buttonHeight, "Play!");
		buttonPlayCancel.setTranslation(2 * spacing + buttonWidth * 1.5f, midPoint);
		buttonPlayCancel.onClicked = new Runnable() {
			@Override
			public void run() {
				if (hinting || playing) cancelAction();
				else doPlay();
			}
		};
		actionLayer.add(buttonPlayCancel.layerAddable());

		Button buttonUndo = new Button(buttonWidth, buttonHeight * 0.45f, "Undo");
		buttonUndo.setTranslation(3 * spacing + buttonWidth * 2.5f, midPoint - buttonHeight * 0.275f);
		actionLayer.add(buttonUndo.layerAddable());
		
		Button buttonRedo = new Button(buttonWidth, buttonHeight * 0.45f, "Redo");
		buttonRedo.setTranslation(3 * spacing + buttonWidth * 2.5f, midPoint + buttonHeight * 0.275f);
		actionLayer.add(buttonRedo.layerAddable());
	}
	
	private void doHint() {
		hinting = true;
		startAction("Hint!");
	}
	
	private void doPlay() {
		playing = true;
		startAction("Play!");
	}
	
	private void finishHinting() {
		cancelAction();
	}
	
	private void finishPlaying() {
		cancelAction();
	}
	
	private void startAction(String action) {
		cardsLayer.setInteractive(true);
		buttonHintGo.setText(action);
		buttonHintGo.setEnabled(false);
		buttonPlayCancel.setText("Cancel");
	}
	
	private void cancelAction() {
		cardsLayer.setInteractive(false);
		for (int i = 0; i < hand.size; i++) {
			cardLayers.get(i).active = false;
		}
		hinting = playing = false;
		buttonHintGo.setText("Hint!");
		buttonHintGo.setEnabled(true);
		buttonPlayCancel.setText("Play!");
	}
	
	private void cardClicked(int index) {
		int active = 0;
		for (int i = 0; i < hand.size; i++) {
			if (playing && i != index) cardLayers.get(i).active = false;
			if (cardLayers.get(i).active) active++;
		}
		if (playing || hinting) {
			buttonHintGo.setEnabled(active > 0);
		}
	}

	private void reset() {
		for (CardLayer cl : cardLayers) cl.destroy();
		cardLayers.clear();
		
		float cardsWidth = width;
		float cWidth = cardsWidth * 0.8f / hand.size;
		cardHeight = cWidth * CardLayer.ASPECT_RATIO;
		for (int i = 0; i < hand.size; i++) {
			CardLayer cl = new CardLayer(hand.cards[i], cWidth);
			cl.setTranslation(cardsWidth * (i + 0.5f) / hand.size, height * 0.95f - cl.height * 0.5f);
			cardsLayer.add(cl.layerAddable());
			cardLayers.add(cl);
			
			final int fi = i;
			cl.onActiveChanged = new Runnable() {
				@Override
				public void run() {
					cardClicked(fi);
				}
			};
		}
		
		cardsLayer.setInteractive(false);
	}

	public void paint(Clock clock) {
		for (CardLayer layer : cardLayers) {
			layer.paint(clock);
		}
	}

	
	
}
