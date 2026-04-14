package com.codeforall.online.thesnakemodifier.input;

import com.codeforall.online.thesnakemodifier.ui.GameOverScreen;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

/**
 * The GameOverMouse class handles mouse events for the game over screen.
 * Interacts with GameOverScreen to restart or quit the game.
 */
public class GameOverMouse implements MouseHandler {

    /**
     * Mouse instance to handle mouse events
     */
    private Mouse mouse;

    /**
     * GameOverScreen instance to interact with
     */
    private GameOverScreen gameOverScreen;

    /**
     * Initializes the mouse event handling.
     */
    public void init() {
        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    /**
     * Handles mouse click events for the game over screen buttons.
     *
     * @param mouseEvent containing click information
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (gameOverScreen != null && gameOverScreen.isGameOver()) {
            if (mouseEvent.getY() >= gameOverScreen.getRestartTop() &&
                    mouseEvent.getY() <= gameOverScreen.getRestartBottom() &&
                    mouseEvent.getX() >= gameOverScreen.getRestartLeft() &&
                    mouseEvent.getX() <= gameOverScreen.getRestartRight()) {
                gameOverScreen.restartGame();
                System.out.println("Restarting Game");
            } else if (mouseEvent.getY() >= gameOverScreen.getQuitTop() &&
                    mouseEvent.getY() <= gameOverScreen.getQuitBottom() &&
                    mouseEvent.getX() >= gameOverScreen.getQuitLeft() &&
                    mouseEvent.getX() <= gameOverScreen.getQuitRight()) {
                gameOverScreen.quitGame();
                System.out.println("Quitting Game");
            } else if (mouseEvent.getY() >= gameOverScreen.getScoreButtonTop() &&
                    mouseEvent.getY() <= gameOverScreen.getScoreButtonBottom() &&
                    mouseEvent.getX() >= gameOverScreen.getScoreButtonLeft() &&
                    mouseEvent.getX() <= gameOverScreen.getScoreButtonRight()) {
                gameOverScreen.showHighScores();
                System.out.println("Showing High Score");
            }
        }
    }

    /**
     * Handles mouse movement events (currently unused).
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

    /**
     * Sets the GameOverScreen instance to interact with.
     *
     * @param gameOverScreen instance to be set
     */
    public void setGameOverScreen(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }
}
