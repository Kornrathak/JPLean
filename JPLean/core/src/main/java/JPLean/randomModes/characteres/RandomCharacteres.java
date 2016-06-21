package JPLean.randomModes.characteres;

import JPLean.sprite.Sprite;
import JPLean.sprite.SpriteLoader;
import playn.core.Layer;
import playn.core.util.Callback;

/**
 * Created by Ex1stCeed on 6/21/2016.
 */
public class RandomCharacteres {

    private Sprite sprite;
    private int spriteIndex;
    private boolean hasLoaded = false;

    public RandomCharacteres(final float x, final float y, final String mode, final String page, int point) {
        this.spriteIndex = point;
        sprite = SpriteLoader.getSprite("images/characteres/" + page.toLowerCase() + "_" + mode.toLowerCase() + ".json");
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
                System.out.println("Error!! Load Character : " + mode + " " + page);
            }
        });
    }

    public Layer layer() {
        return sprite.layer();
    }


    public void update(int nextstate) {
        if (!hasLoaded) return;
        if (nextstate != spriteIndex) {
            spriteIndex = nextstate;
        }
        sprite.setSprite(spriteIndex);
    }
}
