package com.hanabi.core;


public class Hand {

	public final int size;
	public Card[] cards;
	
	public Hand(int size) {
		this.size = size;
		cards = new Card[size];
		for (int i = 0; i < size; i++) {
			cards[i] = new Card();
		}
	}
		
	public Hand clone() {
		Hand hand = new Hand(size);
		for (int i = 0; i < size; i++) hand.cards[i] = cards[i].clone();
		return hand;
	}
	
	public Hand hint(Hint hint, boolean[] affected) {
		Hand hand = clone();
		for (int i = 0; i < size; i++) {
			if (!hand.cards[i].hint(hint, affected[i])) return null;
		}
		return hand;
	}
	
	public void play(int index) {
		for (int i = index; i < size - 1; i++) {
			cards[i] = cards[i + 1];
		}
		cards[size - 1] = new Card();
	}
}
