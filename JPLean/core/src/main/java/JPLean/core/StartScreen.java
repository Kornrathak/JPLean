package JPLean.core;

import JPLean.button.CustomButton;
import playn.core.*;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by Ex1stCeed on 6/15/2016.
 */
public class StartScreen extends Screen {

    public static ImageLayer bgGame;
    private ScreenStack ss;
    private CustomButton custom;

    public StartScreen(final ScreenStack ss) {
        this.ss = ss;
        Image bgImage = assets().getImage("images/background/screen/bg.png");
        this.bgGame = graphics().createImageLayer(bgImage);
        this.custom = new CustomButton(ss, "images/button/startgame.json", 200f, 100f);

        PlayN.keyboard().setListener(new Keyboard.Adapter(){
            @Override
            public void onKeyUp(Keyboard.Event event) {
                if (event.key() == Key.SPACE) ss.remove(ss.top());
            }
        });
    }

    @Override
    public void wasShown() {
        super.wasShown();
        this.layer.add(bgGame);
        this.layer.add(custom.layer());
    }

    @Override
    public void update(int delta) {
        this.custom.update(delta);
    }
}
