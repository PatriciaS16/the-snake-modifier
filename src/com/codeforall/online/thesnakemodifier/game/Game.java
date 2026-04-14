package com.codeforall.online.thesnakemodifier.game;

import com.codeforall.online.thesnakemodifier.audio.AudioPlayer;
import com.codeforall.online.thesnakemodifier.food.Food;
import com.codeforall.online.thesnakemodifier.food.FoodFactory;
import com.codeforall.online.thesnakemodifier.food.FoodType;
import com.codeforall.online.thesnakemodifier.input.GameOverMouse;
import com.codeforall.online.thesnakemodifier.movement.SnakeMovement;
import com.codeforall.online.thesnakemodifier.score.Score;
import com.codeforall.online.thesnakemodifier.score.ScoreDisplay;
import com.codeforall.online.thesnakemodifier.snake.Snake;
import com.codeforall.online.thesnakemodifier.ui.GameOverScreen;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.io.IOException;
import javax.swing.Timer;

/**
 * Game initializes all components and drives the game loop.
 */
public class Game {

    public static final String PREFIX = "resources/";

    private final Snake snake;
    private final SnakeMovement snakeMovement;
    private final Grid grid;
    private final CollisionHandler collisionHandler;
    private final AudioPlayer audioPlayer;
    private final ScoreDisplay scoreDisplay;
    private Timer gameLoopTimer;
    private boolean gameOver = false;
    private boolean gameEndHandled = false;

    public Game() {
        this.grid = new Grid(new Picture(10, 10, PREFIX + "LighterBackgroundLog.png"), 10);

        this.snake = new Snake(new Picture(400, 350, PREFIX + "SnakeHead.png"));
        this.snakeMovement = new SnakeMovement(snake, grid);

        this.audioPlayer = new AudioPlayer();
        audioPlayer.addBackgroundMusic("arcade game", "/arcadeLoop.wav");
        audioPlayer.addSoundEffects("food",       "/Food.wav");
        audioPlayer.addSoundEffects("game over",  "/gameOver.wav");
        audioPlayer.addSoundEffects("bonus",      "/bonus.wav");
        audioPlayer.addSoundEffects("lose Score", "/losePoints.wav");

        Food food = new FoodFactory(grid).createFood(FoodType.FRUIT);
        this.collisionHandler = new CollisionHandler(snake, grid, food, this, audioPlayer);

        this.scoreDisplay = new ScoreDisplay(650, 30);
    }

    public Snake getSnake() {
        return snake;
    }

    public SnakeMovement getSnakeMovement() {
        return snakeMovement;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        if (gameOver) {
            audioPlayer.playSoundEffects("game over");
        }
    }

    public boolean checkGameOver() {
        return gameOver;
    }

    public void startGameLoop() {
        Score.getInstance().resetScore();
        audioPlayer.startBackgroundMusic();
        gameLoopTimer = new Timer(150, e -> {
            snakeMovement.move();
            collisionHandler.checkCollisions();
            scoreDisplay.updateScore();
            if (checkGameOver()) {
                endGame();
            }
        });
        gameLoopTimer.start();
    }

    public void endGame() {
        if (!gameEndHandled) {
            gameEndHandled = true;
            gameLoopTimer.stop();
            audioPlayer.stopBackgroundMusic();

            try {
                Score.getInstance().checkScore();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GameOverScreen gameOverScreen = new GameOverScreen(this);
            GameOverMouse gameOverMouse = new GameOverMouse();
            gameOverMouse.setGameOverScreen(gameOverScreen);
            gameOverMouse.init();
        }
    }

    public void cleanup() {
        snake.cleanup();
        grid.cleanup();
        collisionHandler.cleanup();
        scoreDisplay.cleanup();
    }
}
