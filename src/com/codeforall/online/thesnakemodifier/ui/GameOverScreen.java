package com.codeforall.online.thesnakemodifier.ui;

import com.codeforall.online.thesnakemodifier.game.Game;
import com.codeforall.online.thesnakemodifier.input.MyKeyboard;
import com.codeforall.online.thesnakemodifier.score.Score;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import javax.swing.JOptionPane;

/**
 * The GameOverScreen class represents the game over screen.
 * It provides options to restart the game or quit.
 */
public class GameOverScreen {

    private Rectangle rectangle;
    private Picture restartButton;
    private Picture quitButton;
    private Picture background;
    private Picture youLose;
    private Picture snakeHead;
    private Picture snakeBody1;
    private Picture snakeBody2;
    private Picture snakeBody3;
    private Picture snakeBody4;
    private Picture snakeBody5;
    private Picture snakeBody6;
    private Picture snakeBody7;
    private Picture snakeEnd;
    private Picture fruit;
    private Picture scoreButton;
    private boolean gameOver;
    private Game previousGame;

    /**
     * Constructs a GameOverScreen and draws all game over screen components.
     *
     * @param previousGame The game that just ended, used to clean up its visuals on restart
     */
    public GameOverScreen(Game previousGame) {
        this.previousGame = previousGame;
        this.rectangle = new Rectangle(10, 10, 800, 700);
        this.rectangle.draw();

        this.background = new Picture(10, 10, Game.PREFIX + "DarkerBackground.png");
        this.background.draw();

        this.quitButton = new Picture(325, 450, Game.PREFIX + "Exit.png");
        this.quitButton.draw();

        this.restartButton = new Picture(300, 300, Game.PREFIX + "Reset.png");
        this.restartButton.draw();

        this.youLose = new Picture(10, 10, Game.PREFIX + "GameOver.png");
        this.youLose.draw();

        this.snakeHead = new Picture(450, 200, Game.PREFIX + "SnakeHead.png");
        this.snakeHead.draw();

        this.snakeBody1 = new Picture(400, 200, Game.PREFIX + "SnakeBody.png");
        this.snakeBody1.draw();
        this.snakeBody2 = new Picture(350, 200, Game.PREFIX + "SnakeBody.png");
        this.snakeBody2.draw();
        this.snakeBody3 = new Picture(300, 200, Game.PREFIX + "SnakeBody.png");
        this.snakeBody3.draw();
        this.snakeBody4 = new Picture(250, 200, Game.PREFIX + "SnakeBody.png");
        this.snakeBody4.draw();
        this.snakeBody5 = new Picture(200, 200, Game.PREFIX + "SnakeBody.png");
        this.snakeBody5.draw();
        this.snakeBody6 = new Picture(200, 240, Game.PREFIX + "SnakeBody.png");
        this.snakeBody6.draw();
        this.snakeBody7 = new Picture(200, 290, Game.PREFIX + "SnakeBody.png");
        this.snakeBody7.draw();

        this.snakeEnd = new Picture(200, 340, Game.PREFIX + "SnakeEnd.png");
        this.snakeEnd.draw();

        this.fruit = new Picture(520, 200, Game.PREFIX + "Fruit.png");
        this.fruit.draw();

        this.scoreButton = new Picture(350, 570, Game.PREFIX + "Score.png");
        this.scoreButton.draw();

        this.gameOver = true;
    }

    public int getRestartTop()    { return restartButton.getY(); }
    public int getRestartLeft()   { return restartButton.getX(); }
    public int getRestartRight()  { return restartButton.getX() + restartButton.getWidth(); }
    public int getRestartBottom() { return restartButton.getY() + restartButton.getHeight(); }

    public int getQuitTop()    { return quitButton.getY(); }
    public int getQuitLeft()   { return quitButton.getX(); }
    public int getQuitRight()  { return quitButton.getX() + quitButton.getWidth(); }
    public int getQuitBottom() { return quitButton.getY() + quitButton.getHeight(); }

    public int getScoreButtonTop()    { return scoreButton.getY(); }
    public int getScoreButtonLeft()   { return scoreButton.getX(); }
    public int getScoreButtonRight()  { return scoreButton.getX() + scoreButton.getWidth(); }
    public int getScoreButtonBottom() { return scoreButton.getY() + scoreButton.getHeight(); }

    /**
     * Displays the high score in a popup message.
     */
    public void showHighScores() {
        int highScore = Score.getInstance().getHighScore();
        JOptionPane.showMessageDialog(null, "High Score: " + highScore);
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Restarts the game by clearing all visuals and creating a new Game instance.
     */
    public void restartGame() {
        // Disable this screen immediately so accumulated GameOverMouse listeners
        // from previous games cannot fire restartGame() again on any future click.
        this.gameOver = false;

        this.rectangle.delete();
        this.background.delete();
        this.quitButton.delete();
        this.restartButton.delete();
        this.scoreButton.delete();
        this.youLose.delete();
        this.snakeHead.delete();
        this.snakeBody1.delete();
        this.snakeBody2.delete();
        this.snakeBody3.delete();
        this.snakeBody4.delete();
        this.snakeBody5.delete();
        this.snakeBody6.delete();
        this.snakeBody7.delete();
        this.snakeEnd.delete();
        this.fruit.delete();

        Game newGame = new Game();
        newGame.startGameLoop();

        MyKeyboard myKeyboard = new MyKeyboard();
        myKeyboard.setSnakeMovement(newGame.getSnakeMovement());
        myKeyboard.init();

        // Clean up old game visuals after new game is already drawn,
        // so the screen never shows a blank gap.
        previousGame.cleanup();
    }

    /**
     * Quits the game.
     */
    public void quitGame() {
        System.exit(0);
    }
}
