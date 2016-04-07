package tk.aegisstudios.kenken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    private Texture correctTexture;
    private Texture incorrectTexture;
    private Texture fireball1Texture;
    private Texture fireball2Texture;

    private Sound correctSound;
    private Sound incorrectSound;

    private Dog dog;
    private Nikhita nikhita;

    private boolean attacking = false;
    private int fireState = 0;
    private int fireX = 0;

    private long lastAnswerTime;
    private long correctTime;
    private long incorrectTime;
    private long fireSwitchTime;

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
        correctTexture = new Texture(Gdx.files.internal("img/sprites/correct.png"));
        incorrectTexture = new Texture(Gdx.files.internal("img/sprites/incorrect.png"));
        fireball1Texture = new Texture(Gdx.files.internal("img/sprites/fireball1.png"));
        fireball2Texture = new Texture(Gdx.files.internal("img/sprites/fireball2.png"));

        correctSound = Gdx.audio.newSound(Gdx.files.internal("sfx/correct.mp3"));
        incorrectSound = Gdx.audio.newSound(Gdx.files.internal("sfx/incorrect.mp3"));

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

        if (TimeUtils.timeSinceNanos(correctTime) >= 1 && TimeUtils.timeSinceNanos(correctTime) <= 500000000L) {
            game.batch.draw(correctTexture, (800 - 128) / 2, (480 - 128) / 2, 128, 128);
        } else if (TimeUtils.timeSinceNanos(incorrectTime) >= 1 && TimeUtils.timeSinceNanos(incorrectTime) <= 500000000L) {
            game.batch.draw(incorrectTexture, (800 - 126) / 2, (480 - 150) / 2, 126, 150);
        }

        if (Gdx.input.isTouched() && Gdx.input.getY() > 380 && TimeUtils.timeSinceNanos(lastAnswerTime) >= 1000000000L) {
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

            if (currentProblem.isAnswerCorrect(chosenSolution)) {
                correctTime = TimeUtils.nanoTime();
                correctSound.play();
                attacking = true;
                nikhita.problemAnswered();
            } else {
                incorrectTime = TimeUtils.nanoTime();
                incorrectSound.play();
            }

            lastAnswerTime = TimeUtils.nanoTime();
            chooseNewProblem();

        }

        if (attacking) {
            if (fireState == 0) {
                game.batch.draw(fireball1Texture, fireX, (480 - 44) / 2, 84, 44);
                if (TimeUtils.timeSinceNanos(fireSwitchTime) >= 500000000L) {
                    fireSwitchTime = TimeUtils.nanoTime();
                    fireState = 1;
                }
            } else {
                game.batch.draw(fireball2Texture, fireX, (480 - 44) / 2, 84, 44);
                if (TimeUtils.timeSinceNanos(fireSwitchTime) >= 500000000L) {
                    fireSwitchTime = TimeUtils.nanoTime();
                    fireState = 0;
                }
            }

            fireX += 400 * delta;

            if (fireX >= 800 - 144 - 84) {
                fireX = 0;
                attacking = false;
                nikhita.damageAnimation = true;
                nikhita.damageAnimationStart = TimeUtils.nanoTime();
            }
        }

        // Check end states
        if (currentProblem.equals(new Problem("sin", "-1000"))) {
            game.setScreen(new DefeatScreen(game));
            dispose();
        } else if (nikhita.health <= 0) {
            game.setScreen(new VictoryScreen(game));
            dispose();
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
        separatorsTexture.dispose();
        correctTexture.dispose();
        incorrectTexture.dispose();
        fireball1Texture.dispose();
        fireball2Texture.dispose();
        correctSound.dispose();
        incorrectSound.dispose();
    }
}
