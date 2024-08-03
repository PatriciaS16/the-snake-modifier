package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The CollisionHandler class is responsible for detecting collisions between
 * the snake and food, as well as boundary and self-collisions. It interacts with
 * the Snake, Grid, Food, and Game classes to manage collision logic and handle game state.
 */
public class CollisionHandler {

    // The snake object to check for collisions
    private Snake snake;

    // Audio to play sound when collisions happen
    private AudioPlayer audioPlayer;

    // The grid object to check for boundary collisions
    private Grid grid;

    // The food object to check for collisions with the snake
    private Food food;

    // The game object to manage game state
    private Game game;

    /**
     * Constructs a CollisionHandler instance with the specified snake, grid, food, and game.
     *
     * @param snake The snake object to be used for collision detection
     * @param grid The grid object used for boundary collision detection
     * @param food The food object to check for collisions with the snake
     * @param game The game object to manage game state
     */
    public CollisionHandler(Snake snake, Grid grid, Food food, Game game) {
        this.snake = snake;
        this.grid = grid;
        this.food = food;
        this.game = game;
        this.audioPlayer = new AudioPlayer();
    }

    /**
     * Checks for collisions between the snake and other objects (food, boundaries, self).
     * If a collision with food is detected, it triggers the snake to grow and spawns new food.
     * If a boundary or self-collision is detected, it sets the game over state.
     */
    public void checkCollisions() {
        // Check if the snake's head collides with the food
        if (isCollision(snake.getHead(), food.getPicture())) {
            System.out.println("Collision with food detected!");
            audioPlayer.playSoundEffects("food");
            food.handleSnakeEat(snake);  // Handle the snake eating the food
        }

        // Check for boundary collision or self-collision
        if (isBoundaryCollision(snake.getHead()) || isSelfCollision()) {
            game.setGameOver(true);// End the game if a collision is detected
        }
    }

    /**
     * Determines if there is a collision between two pictures.
     *
     * @param pic1 The first picture to check for collision
     * @param pic2 The second picture to check for collision
     * @return true if there is a collision between the two pictures; false otherwise
     */
    private boolean isCollision(Picture pic1, Picture pic2) {
        return pic1.getX() < pic2.getX() + pic2.getWidth() &&
                pic1.getX() + pic1.getWidth() > pic2.getX() &&
                pic1.getY() < pic2.getY() + pic2.getHeight() &&
                pic1.getY() + pic1.getHeight() > pic2.getY();
    }

    /**
     * Checks if the given picture (e.g., the snake's head) collides with the boundaries of the grid.
     *
     * @param picture The picture to check for boundary collision
     * @return true if the picture collides with any boundary; false otherwise
     */
    private boolean isBoundaryCollision(Picture picture) {
        int x = picture.getX();
        int y = picture.getY();
        int width = picture.getWidth();
        int height = picture.getHeight();

        boolean leftCollision = x < grid.getPadding();
        boolean topCollision = y < grid.getPadding();
        boolean rightCollision = x + width > grid.getWidth() - grid.getPadding();
        boolean bottomCollision = y + height > grid.getHeight() - grid.getPadding();

        return leftCollision || topCollision || rightCollision || bottomCollision;
    }

    /**
     * Checks if the snake's head collides with any of its body parts.
     *
     * @return true if the snake's head collides with any body part; false otherwise
     */
    private boolean isSelfCollision() {
        Picture head = snake.getHead();
        for (Picture bodyPart : snake.getBody()) {
            if (isCollision(head, bodyPart)) {
                return true;  // Collision detected with a body part
            }
        }
        return false;  // No collision detected
    }
}
