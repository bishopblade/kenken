package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

class Nikhita {
    int health = 20;
    boolean alive = true;
    boolean damageAnimation = false;
    long damageAnimationStart;

    Texture healthTexture;
    Texture nikhitaImage;

    Rectangle nikhita;

    BitmapFont font;
    GlyphLayout layout;

    Nikhita(BitmapFont font) {
        healthTexture = new Texture(Gdx.files.internal("img/sprites/health.png"));
        nikhitaImage = new Texture(Gdx.files.internal("img/sprites/nikhitastill.png"));

        nikhita = new Rectangle();
        nikhita.width = 144;
        nikhita.height = 256;
        nikhita.x = 800 - nikhita.width - 20;
        nikhita.y = (480 - nikhita.height) / 2;

        this.font = font;

        layout = new GlyphLayout();
    }

    void problemAnswered() {
        health -= 1;
        if (health <= 0) alive = false;
    }

    void render(SpriteBatch batch) {
        if (damageAnimation && TimeUtils.timeSinceNanos(damageAnimationStart) <= 300000000L) {
            batch.setColor(0.9f, 0.0f, 0.0f, 1.0f);
        } else if (damageAnimation && TimeUtils.timeSinceNanos(damageAnimationStart) >= 300000000L) {
            damageAnimation = false;
        }

        batch.draw(nikhitaImage, nikhita.x, nikhita.y, nikhita.width, nikhita.height);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        layout.setText(font, health + "/20");
        if (health < 2) {
            batch.draw(healthTexture, 0, 416, 80, 64);
            font.draw(batch, layout, (80 - layout.width) / 2, 480 - ((64 - layout.height) / 2));
        } else {
            batch.draw(healthTexture, 0, 416, 40 * health, 64);
            font.draw(batch, layout, (40*health - layout.width) / 2, 480 - ((64 - layout.height) / 2));
        }
    }

}
