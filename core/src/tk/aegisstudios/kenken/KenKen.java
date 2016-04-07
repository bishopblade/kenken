package tk.aegisstudios.kenken;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KenKen extends Game {
	// Resources
	SpriteBatch batch;
	BitmapFont font;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fnt/munro.fnt"));
		
		this.setScreen(new MainMenuScreen(this, false));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
