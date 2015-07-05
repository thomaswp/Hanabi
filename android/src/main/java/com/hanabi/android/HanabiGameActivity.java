package com.hanabi.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.hanabi.core.HanabiGame;

public class HanabiGameActivity extends GameActivity {
	@Override
	public void main(){
		PlayN.run(new HanabiGame());
	}
}
