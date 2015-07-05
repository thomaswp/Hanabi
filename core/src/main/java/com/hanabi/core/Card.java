package com.hanabi.core;

import java.util.HashSet;
import java.util.Set;

public class Card {

	public enum Color {
		Red("r"),
		White("w"),
		Blue("b"),
		Green("g"),
		Yellow("y")
		;
		
		public final String code;
		
		Color(String code) {
			this.code = code;
		}
	}
	
	public enum Answer {
		Yes, Maybe, No;
		
		public static Answer fromBoolean(boolean answer) {
			return answer ? Yes : No;
		}
		
		public boolean t() {
			return this == Yes;
		}
		
		public boolean f() {
			return this == No;
		}
	}
	
	public final static int MAX_NUMBER = 5;
		
//	public int order;
	
	private final Set<Color> notColors = new HashSet<Color>();
	private final Set<Integer> notNumbers = new HashSet<Integer>();
	
	private Color color;
	private Integer number;
		
	public Answer isColor(Color color) {
		if (this.color != null) return Answer.fromBoolean(this.color == color);
		if (notColors.contains(color)) return Answer.No;
		return Answer.Maybe;
	}
	
	public Answer isNumber(Integer number) {
		if (this.number != null) return Answer.fromBoolean(this.number == number);
		if (notNumbers.contains(number)) return Answer.No;
		return Answer.Maybe;
	}
	
	public boolean hint(Hint hint, boolean positive) {
		if (hint.isColorHint()) return hint(hint.color, positive);
		return hint(hint.number, positive);
	}
	
	private boolean hint(Color color, boolean positive) {	
		if (positive) {
			if (isColor(color).f()) return false;
			this.color = color;
		} else {
			if (isColor(color).t()) return false;
			notColors.add(color);
			if (notColors.size() == Color.values().length - 1) {
				for (Color c : Color.values()) {
					if (!notColors.contains(c)) this.color = c;
				}
			}
		}
		return true;
		
	}
	
	private boolean hint(Integer number, boolean positive) {	
		if (positive) {
			if (isNumber(number).f()) return false;
			this.number = number;
		} else {
			if (isNumber(number).t()) return false;
			notNumbers.add(number);
			if (notNumbers.size() == MAX_NUMBER - 1) {
				for (int i = 1; i <= MAX_NUMBER; i++) {
					if (!notNumbers.contains(i)) this.number = i;
				}
			}
		}
		return true;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card card = (Card) obj;
			for (Color c : Color.values()) {
				if (isColor(c) != card.isColor(c)) return false;
			}
			for (int i = 1; i <= MAX_NUMBER; i++) {
				if (isNumber(i) != card.isNumber(i)) return false;
			}
//			if (card.order != order) return false;
			return true;
		}
		return super.equals(obj);
	}
	
	public Card clone() {
		Card card = new Card();
		card.color = color;
		card.number = number;
		for (Color c : notColors) card.notColors.add(c);
		for (Integer n : notNumbers) card.notNumbers.add(n);
//		card.order = order;
		return card;
	}
}
