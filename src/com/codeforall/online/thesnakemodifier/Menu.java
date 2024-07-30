package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The Menu class represents the game menu screen
 * It has a rectangle has background and a picture for the menu
 */
public class Menu {

    /**
     * Rectangle covering the menu area
     */
    private Rectangle rectangle;

    /**
     * Picture button displayed in the menu
     */
    private Picture picture;

    /**
     * Picture for menu background
     */
    private Picture background;

    /**
     * Picture to for borderTop
     */
    private Picture borderTop;

    /**
     * Picture for borderBottom
     */
    private Picture borderBottom;

    /**
     * Picture for borderLeft
     */
    private Picture borderLeft;

    /**
     * Picture for borderRight
     */
    private Picture borderRight;

    /**
     * Picture for title
     */
    private Picture title;

    /**
     * To indicate if game started
     */
    private boolean gameStarted;

    /**
     * Constructs a Menu
     * Initializes and draws the rectangle and picture for the menu
     */
    public Menu() {

        // Initialize and draw the rectangle
        this.rectangle = new Rectangle(10,10,800,700);
        this.rectangle.draw();

        // Initialize and draw LighterBackground
        this.background = new Picture(10,10, Game.PREFIX + "LighterBackground.png");
        this.background.draw();

        // Initialize and draw a title
        this.title = new Picture(10,10,Game.PREFIX + "Title.png");
        this.title.draw();

        // Initialize and draw pictures for borders
        this.borderTop = new Picture(10,10, Game.PREFIX + "Top.png");
        this.borderTop.draw();

        this.borderLeft = new Picture(10,10, Game.PREFIX + "Left.png");
        this.borderLeft.draw();

        this.borderRight = new Picture(110,10, Game.PREFIX + "Right.png");
        this.borderRight.draw();

        this.borderBottom = new Picture(10,10, Game.PREFIX + "Bottom.png");
        this.borderBottom.draw();

        // Initialize and draw the picture StartButton within the rectangle
        this.picture = new Picture(250 ,370, Game.PREFIX + "StartButton.png");
        this.picture.draw();

        // Set the gameStarted to false initially
        this.gameStarted = false;
    }

    /**
     * Get the top coordinate of the menu picture
     * @return the top of the menu picture
     */
    public int getTop() {
        return picture.getY();
    }

    /**
     * Get the left coordinate of the menu picture
     * @return the left of the menu picture
     */
    public int getLeft() {
        return picture.getX();
    }

    /**
     * Get the right coordinate of the menu picture
     * @return the right of the menu picture
     */
    public int getRight() {
        return picture.getX() + picture.getWidth();
    }

    /**
     * Get the bottom coordinate of the menu picture
     * @return the bottom of the menu picture
     */
    public int getBottom() {
        return picture.getY() + picture.getHeight();
    }

    /**
     * Checks if the game has started
     * @return true if game has started, else false
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Starts the game if the game hasn't started
     * Removes the menu rectangle and picture and initializes game logic
     */
    public void startGame() {

        // Check if the game hasn't started yer
        if (!gameStarted) {
            // Set gameStarted to true
            gameStarted = true;
            // Remove the menu rectangle and picture from the screen
            this.rectangle.delete();
            this.picture.delete();
            // Print a message to indicate if game has started
            System.out.println("Game started!");

            // Call startGameLogic method to initialize and start the game
            startGameLogic();
        }
    }

    /**
     * Initialize and start game logic
     *
     */
    private void startGameLogic() {

        // Create and draw a new rectangle for the game area
        Rectangle rectangle = new Rectangle(10,10,800,700);
        rectangle.draw();

        // Initialize a new game instance
        Game game = new Game();

        // Initialize keyboard controls for the game
        MyKeyboard myKeyboard = new MyKeyboard();
        myKeyboard.setSnake(game.getSnake());
        myKeyboard.init();
    }
}
