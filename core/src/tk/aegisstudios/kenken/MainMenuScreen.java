package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen implements Screen {
	final KenKen game;
	OrthographicCamera camera;
	
	Texture kenLeftImage;
	Texture kenRightImage;
	Rectangle kenLeft;
	Rectangle kenRight;

	public MainMenuScreen(final KenKen gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		kenLeftImage = new Texture(Gdx.files.internal("img/kenLeft.png"));
		kenLeft = new Rectangle();
		kenLeft.x = 50;
		kenLeft.y = (480 - 375) / 2;
		kenLeft.width = 189;
		kenLeft.height = 375;
		
		kenRightImage = new Texture(Gdx.files.internal("img/kenRight.png"));
		kenRight = new Rectangle();
		kenRight.x = 800 - 50 - 189;
		kenRight.y = (480 - 375) / 2;
		kenRight.width = 189;
		kenRight.height = 375;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Ken-Ken!", 330, 440);
		game.font.draw(game.batch, "Press ENTER to start.", 330, 50);
		game.batch.draw(kenLeftImage, kenLeft.x, kenLeft.y, kenLeft.width, kenLeft.height);
		game.batch.draw(kenRightImage, kenRight.x, kenRight.y, kenRight.width, kenRight.height);
		game.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			game.setScreen(new IntroScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		kenLeftImage.dispose();
		kenRightImage.dispose();
	}

}
