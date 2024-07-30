package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

/**
 * The MyMouse class handles mouse events for the menu
 * Interacts with Menu class to start the game
 */
public class MyMouse implements MouseHandler {

    /**
     * Mouse instance to handle mouse events
     */
    private Mouse mouse;

    /**
     * Menu instance to interact with mouse
     */
    private Menu menu;

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
     * Checks if the mouse is within the menu area and if game hasn't started
     * Starts Game
     * @param mouseEvent containing click information
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // Ensure that menu isn't null and if game hasn't started
        if (menu != null && !menu.isGameStarted() &&
                mouseEvent.getY() >= menu.getTop() &&
                mouseEvent.getY() <= menu.getBottom() &&
                mouseEvent.getX() >= menu.getLeft() && mouseEvent.getX() <= menu.getRight()) {
            // Start game
            menu.startGame();
            // Print message to indicate if a click was detected
            System.out.println("Click");

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
     * Sets menu instance to interact with
     * @param menu instance to be set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
