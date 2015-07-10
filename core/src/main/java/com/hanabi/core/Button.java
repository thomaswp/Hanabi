package com.hanabi.core;

import static playn.core.PlayN.graphics;
import playn.core.Font.Style;
import playn.core.Pointer.Event;
import playn.core.Color;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.TextFormat;
import tripleplay.util.Colors;

import com.tuxlib.util.CanvasUtils;
import com.tuxlib.util.layer.GroupLayerWrapper;
import com.tuxlib.util.layer.LayerUtils;

public class Button extends GroupLayerWrapper {

	public final float width, height;
	
	private final ImageLayer backgroundLayer;
	private final ImageLayer textLayer;
	
	private String text;
	
	public Runnable onClicked;
	
	public String text() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
		textLayer.setImage(createTextImage());
		LayerUtils.centerLayer(textLayer);
	}
	
	public Button(float width, float height, String text) {
		this.width = width;
		this.height = height;
		
		backgroundLayer = graphics().createImageLayer();
		backgroundLayer.setImage(createImage());
		LayerUtils.centerLayer(backgroundLayer);
		layer.add(backgroundLayer);
		
		textLayer = graphics().createImageLayer();
		setText(text);
		layer.add(textLayer);
		
		backgroundLayer.addListener(new Pointer.Adapter() {
			@Override
			public void onPointerStart(Event event) {
				buttonPressed(event);
			}
			
			@Override
			public void onPointerEnd(Event event) {
				buttonReleased(event);
			}
		});
	}
	
	private void buttonPressed(Event event) {
		layer.setTint(Color.rgb(200, 200, 200));
	}

	private void buttonReleased(Event event) {
		layer.setTint(Colors.WHITE);
		
		float x = event.localX(), y = event.localY();
		if (x < 0 || x > width || y < 0 || y > height) return;
		if (onClicked != null) onClicked.run();
	}
	
	private Image createTextImage() {
		return CanvasUtils.createText(text, 
				new TextFormat(graphics().createFont(
						"Georgia", Style.PLAIN, height * 0.3f), true), 
						Colors.BLACK);
	}
	
	private Image createImage() {
		return CanvasUtils.createRoundRect(width, height, height * 0.1f, 
				Colors.LIGHT_GRAY, 2, Colors.BLACK);
	}

}
