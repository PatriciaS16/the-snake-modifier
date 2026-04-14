package com.codeforall.online.thesnakemodifier.ui;

import com.codeforall.online.thesnakemodifier.audio.AudioPlayer;
import com.codeforall.online.thesnakemodifier.game.Game;
import com.codeforall.online.thesnakemodifier.input.MyKeyboard;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The Menu class represents the game menu screen.
 */
public class Menu {

    /**
     * Rectangle covering the menu area
     */
    private Rectangle rectangle;

    /**
     * Start button picture
     */
    private Picture picture;

    /**
     * Menu background picture
     */
    private Picture background;

    /**
     * Border pictures
     */
    private Picture borderTop;
    private Picture borderBottom;
    private Picture borderLeft;
    private Picture borderRight;

    /**
     * Title picture
     */
    private Picture title;

    /**
     * Flag to indicate if the game has started
     */
    private boolean gameStarted;

    /**
     * Easy mode picture
     */
    private Picture easyMode;

    /**
     * Audio player for the menu intro music
     */
    private AudioPlayer audioPlayer;

    /**
     * Constructs a Menu and draws all menu components.
     */
    public Menu() {
        this.rectangle = new Rectangle(10, 10, 800, 700);
        this.rectangle.draw();

        this.background = new Picture(10, 10, Game.PREFIX + "LighterBackground.png");
        this.background.draw();

        this.title = new Picture(10, 10, Game.PREFIX + "Title.png");
        this.title.draw();

        this.borderTop = new Picture(10, 10, Game.PREFIX + "Top.png");
        this.borderTop.draw();

        this.borderLeft = new Picture(10, 10, Game.PREFIX + "Left.png");
        this.borderLeft.draw();

        this.borderRight = new Picture(110, 10, Game.PREFIX + "Right.png");
        this.borderRight.draw();

        this.borderBottom = new Picture(10, 10, Game.PREFIX + "Bottom.png");
        this.borderBottom.draw();

        this.picture = new Picture(250, 300, Game.PREFIX + "StartButton.png");
        this.picture.draw();

        this.easyMode = new Picture(10, 10, Game.PREFIX + "EasyMode.png");
        this.easyMode.draw();

        this.gameStarted = false;

        this.audioPlayer = new AudioPlayer();
        audioPlayer.addBackgroundMusic("intro", "/intro.wav");
        audioPlayer.startBackgroundMusic();
    }

    /**
     * Gets the top coordinate of the start button.
     */
    public int getTop() {
        return picture.getY();
    }

    /**
     * Gets the left coordinate of the start button.
     */
    public int getLeft() {
        return picture.getX();
    }

    /**
     * Gets the right coordinate of the start button.
     */
    public int getRight() {
        return picture.getX() + picture.getWidth();
    }

    /**
     * Gets the bottom coordinate of the start button.
     */
    public int getBottom() {
        return picture.getY() + picture.getHeight();
    }

    /**
     * Checks if the game has started.
     * @return true if game has started, false otherwise
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Starts the game if it hasn't started yet.
     * Removes menu elements and initializes game logic.
     */
    public void startGame() {
        if (!gameStarted) {
            gameStarted = true;
            audioPlayer.stopBackgroundMusic();
            this.rectangle.delete();
            this.picture.delete();
            this.easyMode.delete();
            System.out.println("Game started!");
            startGameLogic();
        }
    }

    /**
     * Initializes and starts the game logic.
     */
    private void startGameLogic() {
        Rectangle rectangle = new Rectangle(10, 10, 800, 700);
        rectangle.draw();

        Game game = new Game();
        game.startGameLoop();

        MyKeyboard myKeyboard = new MyKeyboard();
        myKeyboard.setSnakeMovement(game.getSnakeMovement());
        myKeyboard.init();
    }
}
