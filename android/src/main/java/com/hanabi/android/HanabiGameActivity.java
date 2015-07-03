package com.hanabi.android;

import playn.android.GameActivity;

import com.hanabi.core.HanabiGame;

public class HanabiGameActivity extends GameActivity {

  @Override public void main () {
    new HanabiGame(platform());
  }
}
