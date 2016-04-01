package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

class RollingText {
	private String character;
	private Texture characterImage;
	private String message;
	private float rate;

    private Texture textArrow;
    private Sound textSound;

	private int substrEnd = 0;
	private String currentMessage;
	
	private float combinedDelta = 0.0f;
    private long lastOnTime;
	
    boolean isFinished = false;

    RollingText(String character, FileHandle characterImage, String message, float length) {
		this.character = character.toUpperCase();
        this.characterImage = new Texture(characterImage);
		this.message = message;
		
		this.rate = length / message.length();

        textArrow = new Texture(Gdx.files.internal("img/sprites/arrow.png"));
		textSound = Gdx.audio.newSound(Gdx.files.internal("sfx/text.ogg"));
	}
	
    void update(float delta) {
		combinedDelta += delta;
		if (combinedDelta >= rate && substrEnd < message.length()) {
			combinedDelta = 0.0f;
			textSound.play();
			substrEnd++;
		}
		if (substrEnd == message.length() && (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))) {
			isFinished = true;
		}
		
		currentMessage = String.format("%s: %s", character, message.substring(0, substrEnd));
	}

    void render(SpriteBatch batch, BitmapFont font, int x, int y) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, currentMessage);

        batch.draw(characterImage, 10, 10, 64, 64);
        if (substrEnd == message.length() && !isFinished) {
            if (TimeUtils.timeSinceNanos(lastOnTime) >= 1000000000L) {
                lastOnTime = TimeUtils.nanoTime();
            } else if (TimeUtils.timeSinceNanos(lastOnTime) <= 500000000L){
                batch.draw(textArrow, x + layout.width + 10, y - 15, 15, 15);
            }
        }

        font.draw(batch, layout, x, y);
    }
}
