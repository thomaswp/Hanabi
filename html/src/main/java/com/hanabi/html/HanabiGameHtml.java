package com.hanabi.html;

import com.google.gwt.core.client.EntryPoint;
import playn.html.HtmlPlatform;
import com.hanabi.core.HanabiGame;

public class HanabiGameHtml implements EntryPoint {

  @Override public void onModuleLoad () {
    HtmlPlatform.Config config = new HtmlPlatform.Config();
    // use config to customize the HTML platform, if needed
    HtmlPlatform plat = new HtmlPlatform(config);
    plat.assets().setPathPrefix("tracker/");
    new HanabiGame(plat);
    plat.start();
  }
}
