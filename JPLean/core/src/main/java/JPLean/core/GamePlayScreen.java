package JPLean.core;

import JPLean.button.CustomButton;
import JPLean.randomModes.characteres.RandomCharacteres;
import JPLean.randomModes.vocabularies.RandomVocabulary;
import playn.core.Image;
import playn.core.ImageLayer;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import java.util.ArrayList;
import java.util.Random;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by Ex1stCeed on 6/21/2016.
 */
public class GamePlayScreen extends Screen {

    private ScreenStack ss;
    private ImageLayer bgGame;
    private String mode;
    private RandomCharacteres randomes;
    private final float x = 400f, y = 300f;
    private Random random = new Random();
    private CustomButton custom;
    private RandomVocabulary vocab;
    private ArrayList<CustomButton> state = new ArrayList<CustomButton>();
    private ArrayList<Integer> stateAll = new ArrayList<Integer>();
    private int nextstate;
    public static int total = 0;
    private int e = 0, old, status;

    public GamePlayScreen(ScreenStack ss, String jsonf, int status) {
        this.ss = ss;
        this.status = status;
        Image bgImage = assets().getImage("images/background/gameplay/randomCharacter.png");
        this.bgGame = graphics().createImageLayer(bgImage);
        switch (status) {
            case 0: mode = "Hiragana"; break;
            case 1: mode = "Katakana"; break;
            case 2: mode = "vocabulary"; break;
        }
        if (status == 1 || status == 0) {
            if (jsonf.equals("pageOne")) {
                total = 46;
                old = 46;
                for (int i = 0; i < total; i++) stateAll.add(i);
            }
            else if (jsonf.equals("pageTwo")) {
                total = 25;
                old = 25;
                for (int i = 0; i < total; i++) stateAll.add(i);
            }
            else if (jsonf.equals("pageThree")) {
                total = 33;
                old = 33;
                for (int i = 0; i < total; i++) stateAll.add(i);
            }
            else if (jsonf.equals("pageAll")) {
                total = 46 + 25 + 33;
                old = total;
                for (int i = 0; i < total; i++) stateAll.add(i);
            }
            int temp = random.nextInt(total);
            this.randomes = new RandomCharacteres(x, y, mode, jsonf, stateAll.get(temp));
            stateAll.remove(temp);
            nextstate = temp;
            total--;
            old = total;
        }
        else if (status == 2) {
            total = 60;
            old = 60;
            for (int i = 0; i < total; i++) stateAll.add(i);
            int temp = random.nextInt(total);
            this.vocab = new RandomVocabulary(x, y, mode, stateAll.get(temp));
            stateAll.remove(temp);
            nextstate = temp;
            total--;
            old = total;
        }

        custom = new CustomButton(ss, "images/button/backbutton.json", x - 200, y + 200);
        state.add(custom);
        custom = new CustomButton(ss, "images/button/nextbutton.json", x + 200, y + 200);
        state.add(custom);
    }

    @Override
    public void wasShown() {
        this.layer.add(bgGame);
        if (status == 1 || status == 0) this.layer.add(randomes.layer());
        else if (status == 2) this.layer.add(vocab.layer());
        for (CustomButton item: state) this.layer.add(item.layer());
    }

    @Override
    public void update(int delta) {
        if (status == 1 || status == 0) {
            e += delta;
            if (e > 120) {
                int temp = state.get(state.size() - 1).getStatus();
                if (total != old) {
                    nextstate = temp;
                    randomes.update(stateAll.get(nextstate));
                    stateAll.remove(nextstate);
                    old = total;
                    e = 0;
                }
            }
        }
        else if (status == 2) {
            e += delta;
            if (e > 120) {
                int temp = state.get(state.size() - 1).getStatus();
                if (total != old) {
                    nextstate = temp;
                    vocab.update(stateAll.get(nextstate));
                    stateAll.remove(nextstate);
                    old = total;
                    e = 0;
                }
            }
        }
    }
}
