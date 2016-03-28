package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
	final KenKen game;
	OrthographicCamera camera;
	
	Music bestSongMusic;
	
	Texture dogImage;
	
	Rectangle dog;
	
	public GameScreen(final KenKen gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		dogImage = new Texture(Gdx.files.internal("dog.png"));
		dog = new Rectangle();
		dog.x = (800 - 250) / 2;
		dog.y = (480 - 220) / 2;
		dog.width = 250;
		dog.height = 220; 
		
		bestSongMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm/bestsongmain.ogg"));
		bestSongMusic.setLooping(true);
		bestSongMusic.play();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		
		game.batch.end();
		
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
		// TODO Auto-generated method stub

	}

}
