package JPLean.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import JPLean.core.MyGame;

public class MyGameJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    config.width = 800;
    config.height = 600;
    config.appName = "JPLean";
    JavaPlatform.register(config);
    PlayN.run(new MyGame());
  }
}
