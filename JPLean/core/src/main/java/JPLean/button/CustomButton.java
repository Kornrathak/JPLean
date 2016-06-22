package JPLean.button;

import JPLean.core.GamePlayScreen;
import JPLean.core.HiraganaScreen;
import JPLean.core.HomeScreen;
import JPLean.core.KatakanaScreen;
import JPLean.sprite.Sprite;
import JPLean.sprite.SpriteLoader;
import playn.core.Layer;
import playn.core.Mouse;
import playn.core.util.Callback;
import tripleplay.game.ScreenStack;

import java.util.Random;

/**
 * Created by Ex1stCeed on 6/15/2016.
 */
public class CustomButton {

    private Sprite sprite;
    private int spriteIndex = 0, e = 0;
    private String jsonf;
    private boolean hasLoaded = false;
    private int status = 0;
    private Random rand = new Random();

    public CustomButton(final ScreenStack ss, final String json, final float x, final float y) {
        this.jsonf = json.substring(14, json.length() - 5);
        sprite = SpriteLoader.getSprite(json);
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite sprite) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width() / 2f, sprite.height() / 2f);
                sprite.layer().setTranslation(x, y);
                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error!! Button " + jsonf);
            }
        });
        sprite.layer().addListener(new Mouse.LayerAdapter(){
            @Override
            public void onMouseUp(Mouse.ButtonEvent event) {
                if (jsonf.equals("startgame")) ss.push(new HomeScreen(ss));
                else if (jsonf.equals("modeHiragana") || jsonf.equals("modeKatakana") || jsonf.equals("modeVocabulary") || jsonf.equals("modeListening")) {
                    if (spriteIndex == 0 && jsonf.equals("modeHiragana")) {
                        ss.push(new HiraganaScreen(ss));
                    }
                    else if (spriteIndex == 0 && jsonf.equals("modeKatakana")) {
                        ss.push(new KatakanaScreen(ss));
                    }
                    else if (spriteIndex == 0 && jsonf.equals("modeVocabulary")) {
                        ss.push(new GamePlayScreen(ss, jsonf, 2));
                    }
                }
                else if (jsonf.equals("pageAll") || jsonf.equals("pageOne") || jsonf.equals("pageTwo") || jsonf.equals("pageThree")) {
                    if (spriteIndex == 0) {
                        ss.push(new GamePlayScreen(ss, jsonf, status));
                    }
                }
                else if (jsonf.equals("backbutton")) ss.remove(ss.top());
                else if (jsonf.equals("nextbutton")) {
                    if (GamePlayScreen.total > 0) {
                        status = rand.nextInt(GamePlayScreen.total);
                        GamePlayScreen.total--;
                    }
                    else {
                        ss.remove(ss.top());
                        ss.remove(ss.top());
                    }
                }
            }
        });
    }

    public Layer layer() {
        return sprite.layer();
    }

    public void update(int delta) {
        if (!hasLoaded) return;
        e += delta;
        if (jsonf.equals("startgame")) {
            if (e > 150) {
                spriteIndex = (spriteIndex + 1) % 3;
                sprite.setSprite(spriteIndex);
                e = 0;
            }
        }
        else if (jsonf.equals("modeHiragana") || jsonf.equals("modeKatakana") || jsonf.equals("modeVocabulary") || jsonf.equals("modeListening")) {
            sprite.setSprite(spriteIndex);
            e = 0;
        }
        else if (jsonf.equals("pageAll") || jsonf.equals("pageOne") || jsonf.equals("pageTwo") || jsonf.equals("pageThree")) {
            sprite.setSprite(spriteIndex);
            e = 0;
        }
        else if (jsonf.equals("backbutton") || jsonf.equals("nextbutton") ) {
            sprite.setSprite(spriteIndex);
            e = 0;
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
