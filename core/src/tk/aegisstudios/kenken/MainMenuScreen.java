package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;

class MainMenuScreen implements Screen {
	private final KenKen game;
	private OrthographicCamera camera;
	
	private Texture kenLeftImage;
	private Texture kenRightImage;
	private Rectangle kenLeft;
	private Rectangle kenRight;
	
	private GlyphLayout layout;

	MainMenuScreen(final KenKen gam) {
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
		
		layout = new GlyphLayout();
	}
	
	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.4f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		layout.setText(game.font, "Welcome to Ken-Ken!");
		game.font.draw(game.batch, layout, (800 - layout.width) / 2, 440);
		
		layout.setText(game.font, "Click anywhere to start");
		game.font.draw(game.batch, layout, (800 - layout.width) / 2, 50);
		
		game.batch.draw(kenLeftImage, kenLeft.x, kenLeft.y, kenLeft.width, kenLeft.height);
		game.batch.draw(kenRightImage, kenRight.x, kenRight.y, kenRight.width, kenRight.height);
		game.batch.end();
		
		if (Gdx.input.isTouched()) {
			game.setScreen(new IntroScreen(game));
			dispose();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.G)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
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
		kenLeftImage.dispose();
		kenRightImage.dispose();
	}

}
