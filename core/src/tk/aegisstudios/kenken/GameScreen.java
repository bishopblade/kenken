package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.TimeUtils;

class GameScreen implements Screen {
    private final KenKen game;
    private OrthographicCamera camera;

    private Music bestSongMusic;

    private GlyphLayout layout;

    private Problem currentProblem;
    private Solution[] currentSolutions;

    private Texture separatorsTexture;

    private Dog dog;
    private Nikhita nikhita;

    private long lastAnswerTime;

    GameScreen(final KenKen gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bestSongMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm/bestsongmain.ogg"));
        bestSongMusic.setLooping(true);
        bestSongMusic.play();

        layout = new GlyphLayout();

        chooseNewProblem();

        separatorsTexture = new Texture(Gdx.files.internal("img/sprites/separators.png"));

        dog = new Dog();
        nikhita = new Nikhita(game.font);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(80/255f, 80/255f, 80/255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        dog.render(game.batch);
        nikhita.render(game.batch);

        layout.setText(game.font, currentProblem.problem.toString());
        game.font.draw(game.batch, layout, (800 - layout.width) / 2, (480 - layout.height) / 2);


        int solX = 0;
        game.batch.draw(separatorsTexture, 0, 0, 800, 70);
        for (int i = 0; i < currentSolutions.length; i++) {
            Solution solution = currentSolutions[i];

            layout.setText(game.font, solution.toString());
            game.font.draw(game.batch, layout, solX + ((200 - layout.width) / 2), layout.height+20);

            solX += 200;
        }

        if (Gdx.input.isTouched() && Gdx.input.getY() > 380) {
            Solution chosenSolution;

            if (Gdx.input.getX() <= 200) {
                chosenSolution = currentSolutions[0];
            } else if (Gdx.input.getX() > 200 && Gdx.input.getX() <= 400) {
                chosenSolution = currentSolutions[1];
            } else if (Gdx.input.getX() > 400 && Gdx.input.getX() <= 600) {
                chosenSolution = currentSolutions[2];
            } else {
                chosenSolution = currentSolutions[3];
            }

            if (currentProblem.isAnswerCorrect(chosenSolution) && TimeUtils.timeSinceNanos(lastAnswerTime) >= 1000000000L) {
                lastAnswerTime = TimeUtils.nanoTime();
                nikhita.problemAnswered();
                chooseNewProblem();
            }
        }

        game.batch.end();
    }

    void chooseNewProblem() {
        currentProblem = Problem.randomProblem();
        currentSolutions = currentProblem.solutionChoicesShuffled();
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
