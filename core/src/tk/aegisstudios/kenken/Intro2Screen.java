package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

class Intro2Screen implements Screen {
	private final KenKen game;
	private OrthographicCamera camera;
	
	private Sound woofSound;
	
	private Texture dogImage;
	private Texture nikhitaImage;

    private Rectangle dog;
	private Rectangle nikhito;
	
	private RollingText NIKHITA_TEXT_1;
	private RollingText NIKHITA_TEXT_2;

	private boolean NIKHITA_TEXT_2_FIRST_LOOP = false;
	
    Intro2Screen(final KenKen gam) {
		game = gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		dogImage = new Texture(Gdx.files.internal("img/sprites/dogstill.png"));
		nikhitaImage = new Texture(Gdx.files.internal("img/sprites/nikhitastill.png"));

		dog = new Rectangle();
		dog.x = 50;
		dog.y = (480 - 104) / 2;
		dog.width = 122;
		dog.height = 104;

		nikhito = new Rectangle();
		nikhito.x = (800 - 144) - 50;
		nikhito.y = (480 - 256) / 2;
        nikhito.width = 144;
		nikhito.height = 256;

		NIKHITA_TEXT_1 = new RollingText("nikhito", Gdx.files.internal("img/sprites/nikhitaface.png"), "Hahaha! You will never escape!", 4.0f);
		NIKHITA_TEXT_2 = new RollingText("nikhito", Gdx.files.internal("img/sprites/nikhitaface.png"), "Wait, what?", 2.0f);

        woofSound = Gdx.audio.newSound(Gdx.files.internal("sfx/woof.wav"));
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		game.batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);

		Gdx.gl.glClearColor(80/255f, 80/255f, 80/255f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(nikhitaImage, nikhito.x, nikhito.y, nikhito.width, nikhito.height);
		game.batch.draw(dogImage, dog.x, dog.y, dog.width, dog.height);

		if (!NIKHITA_TEXT_1.isFinished) {
			NIKHITA_TEXT_1.update(delta);
            NIKHITA_TEXT_1.render(game.batch, game.font, 100, 50);
		} else {
            if (NIKHITA_TEXT_2_FIRST_LOOP) {
                NIKHITA_TEXT_2_FIRST_LOOP = true;
                woofSound.play();
            }
            if (NIKHITA_TEXT_2.isFinished) {
                Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                camera.update();
                game.setScreen(new GameScreen(game));
            }

			NIKHITA_TEXT_2.update(delta);
			NIKHITA_TEXT_2.render(game.batch, game.font, 100, 50);
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
        dogImage.dispose();
        nikhitaImage.dispose();
        woofSound.dispose();
	}

}
