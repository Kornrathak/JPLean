package JPLean.core;

import JPLean.button.CustomButton;
import playn.core.Image;
import playn.core.ImageLayer;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import java.util.ArrayList;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by Ex1stCeed on 6/15/2016.
 */
public class HomeScreen extends Screen {

    private ScreenStack ss;
    private CustomButton custom;
    private ArrayList<CustomButton> modes = new ArrayList<CustomButton>();
    private final float x = 150f, y = 100f;
    private ImageLayer bgGame;

    public HomeScreen(ScreenStack ss) {
        this.ss = ss;
        Image bgImage = assets().getImage("images/background/screen/bg.png");
        this.bgGame = graphics().createImageLayer(bgImage);
        custom = new CustomButton(ss, "images/button/modeHiragana.json", x, y);
        modes.add(custom);
        custom = new CustomButton(ss, "images/button/modeKatakana.json", x, y * 2 - 30f);
        modes.add(custom);
        custom = new CustomButton(ss, "images/button/modeVocabulary.json", x, y * 3 - 60f);
        modes.add(custom);
        custom = new CustomButton(ss, "images/button/modeListening.json", x, y * 4 - 90f);
        modes.add(custom);

        custom = new CustomButton(ss, "images/button/backbutton.json", x, y * 5 - 120f);
        modes.add(custom);
    }

    @Override
    public void wasShown() {
        this.layer.add(bgGame);
        for (CustomButton item: modes) this.layer.add(item.layer());
    }
}
