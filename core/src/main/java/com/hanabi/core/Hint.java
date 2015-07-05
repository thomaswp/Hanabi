package com.hanabi.core;

import com.hanabi.core.Card.Color;

public class Hint {
	public final boolean positive;
	public final Color color;
	public final Integer number;
	
	public Hint(Color color, boolean positive) {
		this.color = color;
		this.number = null;
		this.positive = positive;
	}
	
	public Hint(int number, boolean positive) {
		this.color = null;
		this.number = number;
		this.positive = positive;
	}
	
	public boolean isColorHint() {
		return color != null;
	}
	
	public boolean isNumberHint() {
		return number != null;
	}
}
