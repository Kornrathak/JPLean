package JPLean.core;

import JPLean.button.CustomButton;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import java.util.ArrayList;

/**
 * Created by Ex1stCeed on 6/15/2016.
 */
public class HomeScreen extends Screen {

    private ScreenStack ss;
    private CustomButton custom;
    private ArrayList<CustomButton> modes = new ArrayList<CustomButton>();
    private ArrayList<CustomButton> pages = new ArrayList<CustomButton>();
    private final float x = 150f, y = 100f;
    private int[] stateMode = new int[4];
    private boolean hasPages = false;

    public HomeScreen(ScreenStack ss) {
        this.ss = ss;
        custom = new CustomButton(ss, "images/button/modeHiragana.json", x, y);
        modes.add(custom);
        custom = new CustomButton(ss, "images/button/modeKatakana.json", x, y * 2 - 30f);
        modes.add(custom);
        custom = new CustomButton(ss, "images/button/modeVocabulary.json", x, y * 3 - 60f);
        modes.add(custom);
        custom = new CustomButton(ss, "images/button/modeListening.json", x, y * 4 - 90f);
        modes.add(custom);

        custom = new CustomButton(ss, "images/button/pageAll.json", x * 2.5f, y);
        pages.add(custom);
        custom = new CustomButton(ss, "images/button/pageOne.json", x * 2.5f, y * 2 - 30f);
        pages.add(custom);
        custom = new CustomButton(ss, "images/button/pageTwo.json", x * 2.5f, y * 3 - 60f);
        pages.add(custom);
        custom = new CustomButton(ss, "images/button/pageThree.json", x * 2.5f, y * 4 - 90f);
        pages.add(custom);
    }

    @Override
    public void wasShown() {
        this.layer.add(StartScreen.bgGame);
        for (CustomButton item: modes) this.layer.add(item.layer());
    }

    @Override
    public void update(int delta) {
        for (int i = 0; i < modes.size(); i++) {
            modes.get(i).update(delta);
            stateMode[i] = modes.get(i).getStatus();
        }

        if (!hasPages) {
            for (int i = 0; i < stateMode.length; i++) {
                if (i < 2 && stateMode[i] == 1) {
                    System.out.println(layer.size());
                    for (int j = 0; j < pages.size(); j++) this.layer.add(pages.get(j).layer());
                    System.out.println(layer.size());
                    hasPages = true;
                }
            }
        }
    }
}
