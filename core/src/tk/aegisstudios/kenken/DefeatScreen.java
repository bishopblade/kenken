package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class DefeatScreen implements Screen {
    private final KenKen game;
    private OrthographicCamera camera;

    private Texture nikhitaTexture;

    private RollingText NIKHITA_TEXT_DEFEAT;

    DefeatScreen(final KenKen gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        nikhitaTexture = new Texture(Gdx.files.internal("img/sprites/nikhitastill.png"));

        NIKHITA_TEXT_DEFEAT = new RollingText("nikhito", Gdx.files.internal("img/sprites/nikhitaface.png"), "Hahaha! You will never escape me, Ken...", 4.0f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(80/255f, 80/255f, 80/255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        NIKHITA_TEXT_DEFEAT.update(delta);
        NIKHITA_TEXT_DEFEAT.render(game.batch, game.font, 100, 50);
        game.batch.draw(nikhitaTexture, (800 - 144) / 2, (480 - 256) / 2, 144, 256);
        game.batch.end();

        if (NIKHITA_TEXT_DEFEAT.isFinished) {
            game.setScreen(new RestartScreen(game));
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
    public void dispose() {
       nikhitaTexture.dispose();
    }
}
