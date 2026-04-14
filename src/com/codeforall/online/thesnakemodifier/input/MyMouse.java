package com.codeforall.online.thesnakemodifier.input;

import com.codeforall.online.thesnakemodifier.ui.Menu;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

/**
 * The MyMouse class handles mouse events for the menu.
 * Interacts with the Menu class to start the game.
 */
public class MyMouse implements MouseHandler {

    /**
     * Mouse instance to handle mouse events
     */
    private Mouse mouse;

    /**
     * Menu instance to interact with
     */
    private Menu menu;

    /**
     * Initializes the mouse event handling.
     */
    public void init() {
        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    /**
     * Handles mouse click events.
     * Checks if the click is within the menu area and starts the game if so.
     *
     * @param mouseEvent containing click information
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (menu != null && !menu.isGameStarted() &&
                mouseEvent.getY() >= menu.getTop() &&
                mouseEvent.getY() <= menu.getBottom() &&
                mouseEvent.getX() >= menu.getLeft() &&
                mouseEvent.getX() <= menu.getRight()) {
            menu.startGame();
            System.out.println("Click");
        }
    }

    /**
     * Handles mouse movement events (currently unused).
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

    /**
     * Sets the menu instance to interact with.
     *
     * @param menu instance to be set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
