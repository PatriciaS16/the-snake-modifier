package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

/**
 * The GameOverMouse class handles mouse events for the game over screen.
 * Interacts with the GameOverScreen class to restart or quit the game.
 */
public class GameOverMouse implements MouseHandler {

    /**
     * Mouse instance to handle mouse events
     */
    private Mouse mouse;

    /**
     * GameOverScreen instance to interact with mouse
     */
    private GameOverScreen gameOverScreen;


    /**
     * *
     */
    private Game game;

    /**
     * Initializes the mouse event handling
     */
    public void init() {
        // Create a new mouse instance
        mouse = new Mouse(this);
        // Add Listener for mouse clicks
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    /**
     * Handles mouse click events
     * Checks if the mouse is within the game over screen areas and performs actions
     * @param mouseEvent containing click information
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // Ensure that gameOverScreen isn't null and if game is over
        if (gameOverScreen != null && gameOverScreen.isGameOver()) {
            // Check if the click is within the restart button area
            if (mouseEvent.getY() >= gameOverScreen.getRestartTop() &&
                    mouseEvent.getY() <= gameOverScreen.getRestartBottom() &&
                    mouseEvent.getX() >= gameOverScreen.getRestartLeft() &&
                    mouseEvent.getX() <= gameOverScreen.getRestartRight()) {
                // Restart the game
                gameOverScreen.restartGame();
                System.out.println("Restarting Game");
            }
            // Check if the click is within the quit button area
            else if (mouseEvent.getY() >= gameOverScreen.getQuitTop() &&
                    mouseEvent.getY() <= gameOverScreen.getQuitBottom() &&
                    mouseEvent.getX() >= gameOverScreen.getQuitLeft() &&
                    mouseEvent.getX() <= gameOverScreen.getQuitRight()) {
                // Quit the game
                gameOverScreen.quitGame();
                System.out.println("Quitting Game");
            }
        }
    }

    /**
     * Handles the mouse movement events
     * @param mouseEvent (currently unnecessary)
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

    /**
     * Sets the GameOverScreen instance to interact with
     * @param gameOverScreen instance to be set
     */
    public void setGameOverScreen(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }
}