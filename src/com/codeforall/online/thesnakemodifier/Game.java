package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

/**
 * The Game class sets up the game environment, including the grid and the snake
 * It initializes the game components and manages their interactions
 */
public class Game {

    /**
     * PREFIX for resources paths used to locate the images
     */
    public static final String PREFIX = "resources/"; // remove the 'resources/' when it's time to build

    /**
     * Snake instance representing the snake in the game
     */
    private Snake snake;

    /**
     * The grid instance representing the grid of the game
     */
    private Grid grid;

    /**
     * Game over instance
     */
    private boolean gameOver = false;

    /**
     * Constructs a game instance
     * Initializes the grid and snake
     * Sets up the game environment with specified padding and image
     */
    public Game() {

        // Define the padding around the edges of the grid
        int padding = 10;

        // Create the background picture for the grid
        Picture background = new Picture(10, 10, Game.PREFIX + "LighterBackground.png");
        // Initialize the grid with the background picture and padding
        this.grid = new Grid(background, padding);

        // Create the picture object for the snake with its initial position
        Picture snakePicture = new Picture(400, 350, Game.PREFIX + "SnakeHead.png");
        // Initialize the snake with its picture and grid
        this.snake = new Snake(snakePicture, grid);
    }

    /**
     * Retrieves the snake instance associated with the game
     *
     * @return the snake instance
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Game over logic that check for collisions and ends the game if detects a collision
     */

    public void gameOver() {
        int x = snake.getHeadX();
        int y = snake.getHeadY();
        LinkedList<Picture> snakeBody = snake.getBody();

        Picture headPicture = snakeBody.getFirst();
        int headWidth = headPicture.getWidth();
        int headHeight = headPicture.getHeight();

        if (CollisionHandler.checkSelfCollision(x, y, snakeBody) ||
                !grid.isWithinBounds(x, y, headWidth, headHeight)) {
            endGame();
        }
    }

    public void endGame() {
        if (gameOver) {
            System.exit(0);
        }
    }

}

