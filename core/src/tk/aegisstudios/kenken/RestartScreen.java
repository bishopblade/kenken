package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class RestartScreen implements Screen {
    private final KenKen game;
    private OrthographicCamera camera;

    private Texture nikhitaTexture;

    private GlyphLayout layout;

    RestartScreen(final KenKen gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        nikhitaTexture = new Texture(Gdx.files.internal("img/nikhito.png"));

        layout = new GlyphLayout();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(80/255f, 80/255f, 80/255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.setColor(1.0f, 1.0f, 1.0f, 0.05f);
        game.batch.draw(nikhitaTexture, 0, -300, 800, 1056);
        game.batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        layout.setText(game.font, "Restart?");
        game.font.draw(game.batch, layout, (800 - layout.width) / 2, 350);
        layout.setText(game.font, "Press any key to be reborn. Or close the game if you're a loser.");
        game.font.draw(game.batch, layout, (800 - layout.width) / 2, 300);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new MainMenuScreen(game, true));
            dispose();
        }
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void dispose() {}
}
