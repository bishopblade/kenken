package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

class GameScreen implements Screen {
    private final KenKen game;
    private OrthographicCamera camera;

    private Music bestSongMusic;

    private Problem currentProblem;

    GameScreen(final KenKen gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bestSongMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm/bestsongmain.ogg"));
        bestSongMusic.setLooping(true);
        bestSongMusic.play();

        currentProblem = Problem.randomProblem();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(80/255f, 80/255f, 80/255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, currentProblem.problemToString(), 5, 40);
        game.font.draw(game.batch, currentProblem.solutionToString(), 5, 20);
        game.batch.end();
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
        bestSongMusic.dispose();
    }
}
