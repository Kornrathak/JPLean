package JPLean.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import JPLean.core.MyGame;

public class MyGameJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    config.width = 1024;
    config.height = 768;
    JavaPlatform.register(config);
    PlayN.run(new MyGame());
  }
}
