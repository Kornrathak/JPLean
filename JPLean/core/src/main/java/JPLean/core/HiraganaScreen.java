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
 * Created by Ex1stCeed on 6/21/2016.
 */
public class HiraganaScreen extends Screen {

    private ScreenStack ss;
    private CustomButton custom;
    private ArrayList<CustomButton> pages = new ArrayList<CustomButton>();
    private final float x = 150f, y = 100f;
    private ImageLayer bgGame;

    public HiraganaScreen(ScreenStack ss) {
        this.ss = ss;
        Image bgImage = assets().getImage("images/background/screen/bg.png");
        this.bgGame = graphics().createImageLayer(bgImage);
        custom = new CustomButton(ss, "images/button/pageAll.json", x * 2.5f, y);
        pages.add(custom);
        custom = new CustomButton(ss, "images/button/pageOne.json", x * 2.5f, y * 2 - 30f);
        pages.add(custom);
        custom = new CustomButton(ss, "images/button/pageTwo.json", x * 2.5f, y * 3 - 60f);
        pages.add(custom);
        custom = new CustomButton(ss, "images/button/pageThree.json", x * 2.5f, y * 4 - 90f);
        pages.add(custom);

        custom = new CustomButton(ss, "images/button/backbutton.json", x * 2.5f, y * 5 - 120f);
        pages.add(custom);
    }

    @Override
    public void wasShown() {
        this.layer.add(bgGame);
        for (CustomButton item: pages) this.layer.add(item.layer());
    }

    @Override
    public void update(int delta) {
        for (CustomButton item: pages) item.setStatus(0);
    }
}
