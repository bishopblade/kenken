package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

class IntroScreen implements Screen {
	private long SECOND;

	private final KenKen game;
	private OrthographicCamera camera;
	private GlyphLayout layout;
	
	private Texture nikhitaImage;
	private Texture dogImage;

    private Rectangle nikhita;
	private Rectangle dog;
	
	private float batchAlpha = 0.0f;
	
	private boolean nikhitaStage = true;
	private boolean dogStage = false;
	private boolean fading = false;
	
	private long timeSaved;
	private boolean timeSavedSet = false;
	
	private Music bestSongMusic;
	private Sound tadaSound;
	
	IntroScreen(final KenKen gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		layout = new GlyphLayout();
		
		nikhitaImage = new Texture(Gdx.files.internal("img/nikhito.png"));
		dogImage = new Texture(Gdx.files.internal("img/dog.png"));
		
		nikhita = new Rectangle();
		nikhita.x = (800 - 200) / 2;
		nikhita.y = (480 - 264) / 2;
		nikhita.width = 200;
		nikhita.height = 264;
		
		dog = new Rectangle();
		dog.x = (800 - 250) / 2;
		dog.y = (480 - 220) / 2;
		dog.width = 250;
		dog.height = 220;
		
		bestSongMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm/bestsongintro.ogg"));
		bestSongMusic.setLooping(true);
		bestSongMusic.play();
		
		tadaSound = Gdx.audio.newSound(Gdx.files.internal("sfx/tada.mp3"));

		SECOND = 1000000000L;
	}
	
	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		
		if (nikhitaStage) {
			Gdx.gl.glClearColor(80/255f, 80/255f, 80/255f, 1.0f);
		} else if (dogStage) {
			Gdx.gl.glClearColor(0.2f, 0.0f, 0.7f, 1.0f);
		}
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.setColor(1.0f, 1.0f, 1.0f, batchAlpha);
		if (nikhitaStage) {
			if (!fading) batchAlpha += 0.25f * delta;
			
			game.font.setColor(Color.WHITE);
			layout.setText(game.font, "You were kidnapped by the evil lord Nikhito!");
			int textX = (800 - (int) layout.width) / 2;
			int textY = 50;
			game.font.draw(game.batch, layout, textX, textY);
			game.batch.draw(nikhitaImage, nikhita.x, nikhita.y, nikhita.width, nikhita.height);
			
			if (batchAlpha >= 1.0f) {
				fading = true;
			} if (fading) {
				batchAlpha -= 0.25f * delta;
				if (batchAlpha <= 0.0f) {
					fading = false;
					nikhitaStage = false;
					dogStage = true;
				}
			}
		} else if (dogStage) {
			game.batch.draw(dogImage, dog.x, dog.y, dog.width, dog.height);
			
			if (batchAlpha <= 1.0f && !timeSavedSet) {
				batchAlpha += 0.5f * delta;
				layout.setText(game.font, "But luckily...");
				int textX = (800 - (int) layout.width) / 2;
				int textY = 50;
				
				game.font.draw(game.batch, layout, textX, textY);
			}

			if ((batchAlpha >= 1.0f && !timeSavedSet) || (timeSavedSet && TimeUtils.timeSinceNanos(timeSaved) <= 2 * SECOND)) {
				layout.setText(game.font, "Your dog came to save you!");
				int textX = (800 - (int) layout.width) / 2;
				int textY = 50;
				
				game.font.draw(game.batch, layout, textX, textY);
				
				if (!timeSavedSet) {
					tadaSound.play();
					timeSavedSet = true;
					timeSaved = TimeUtils.nanoTime();
				}
			}
			
			if (timeSavedSet && TimeUtils.timeSinceNanos(timeSaved) >= 2 * SECOND && TimeUtils.timeSinceNanos(timeSaved) <= 6 * SECOND) {
				layout.setText(game.font, "But...");
				int textX = (800 - (int) layout.width) / 2;
				int textY = 50;
				
				game.font.draw(game.batch, layout, textX, textY);
			}
			
			if (timeSavedSet && TimeUtils.timeSinceNanos(timeSaved) >= 6 * SECOND && TimeUtils.timeSinceNanos(timeSaved) <= 10 * SECOND) {
				layout.setText(game.font, "Your dog only listens if you calculate trig functions!");
				int textX = (800 - (int) layout.width) / 2;
				int textY = 50;
				
				fading = true;
				
				game.font.draw(game.batch, layout, textX, textY);
			}
			
			if (fading) {
				batchAlpha -= 0.2f * delta;
				
				if (batchAlpha <= 0.0f) {
					game.setScreen(new Intro2Screen(game));
					dispose();
				}
			}
		}
		game.batch.end();
	}
	
	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		nikhitaImage.dispose();
		dogImage.dispose();
		bestSongMusic.dispose();
		tadaSound.dispose();
	}

}
