package com.hanabi.core.data;

import com.hanabi.core.data.Card.Color;

public class Hint {
	public final Color color;
	public final Integer number;
	
	public Hint(Color color) {
		this.color = color;
		this.number = null;
	}
	
	public Hint(int number) {
		this.color = null;
		this.number = number;
	}
	
	public boolean isColorHint() {
		return color != null;
	}
	
	public boolean isNumberHint() {
		return number != null;
	}
}
