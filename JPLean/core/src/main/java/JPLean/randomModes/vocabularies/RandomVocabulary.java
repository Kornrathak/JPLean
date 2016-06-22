package JPLean.randomModes.vocabularies;

import JPLean.sprite.Sprite;
import JPLean.sprite.SpriteLoader;
import playn.core.Layer;
import playn.core.util.Callback;

/**
 * Created by Ex1stCeed on 6/22/2016.
 */
public class RandomVocabulary {

    private Sprite sprite;
    private int spriteIndex;
    private boolean hasLoaded = false;

    public RandomVocabulary(final float x, final float y, final String mode, Integer point) {
        this.spriteIndex = point;
        sprite = SpriteLoader.getSprite("images/characteres/" + mode.toLowerCase() + ".json");
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
                System.out.println("Error!! Load Character : " + mode);
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
