package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Dog {
    Texture dogTexture;
    Rectangle dog;

    Dog() {
        dogTexture = new Texture(Gdx.files.internal("img/sprites/dog.png"));

        dog = new Rectangle();
        dog.width = 122;
        dog.height = 104;
        dog.x = 20;
        dog.y = (480 - dog.height) / 2;
    }

    void render(SpriteBatch batch) {
        batch.draw(dogTexture, dog.x, dog.y, dog.width, dog.height);
    }
}
