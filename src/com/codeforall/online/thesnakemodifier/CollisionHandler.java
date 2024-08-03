package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The CollisionHandler class manages the detection and handling of collisions
 * between the snake and food items and boundary collisions.
 */
public class CollisionHandler {

    /**
     * The snake instance
     */
    private Snake snake;

    /**
     * The grid on which the snake moves
     */
    private Grid grid;

    /**
     *  Current food item either fruit or chili
     */
    private Object currentFood;

    /**
     * The game instance for game state management
     */
    private Game game;

    /**
     * For playing sound effects
     */
    private AudioPlayer audioPlayer;

    /**
     * Constructs a CollisionHandler instance with the specified parameters.
     *
     * @param snake The snake instance
     * @param grid The grid on which the snake moves
     * @param food The current food item
     * @param game The game instance
     */
    public CollisionHandler(Snake snake, Grid grid, Object food, Game game) {
        this.snake = snake;
        this.grid = grid;
        this.currentFood = food;
        this.game = game;
        this.audioPlayer = new AudioPlayer(); // Initialize the audio player
    }

    /**
     * Checks for collisions between the snake and food, boundary and self-collisions.
     */
    public void checkCollisions() {
        if (currentFood != null) {
            if (currentFood instanceof Fruit) {
                if (isCollision(snake.getHead(), ((Fruit) currentFood).getPicture())) {
                    System.out.println("Collision with fruit detected!");
                    audioPlayer.playSoundEffects("food"); // Play sound effect
                    ((Fruit) currentFood).hideFruit(); // Hide the fruit
                    snake.grow(); // Grow the snake normally
                    currentFood = new FoodFactory(grid).createRandomFood(); // Create new food
                }
            } else if (currentFood instanceof Chili) {
                if (isCollision(snake.getHead(), ((Chili) currentFood).getPicture())) {
                    System.out.println("Chili eaten! Snake grows twice as large.");
                    audioPlayer.playSoundEffects("food"); // Play sound effect
                    snake.grow(); // Grow the snake once
                    snake.grow(); // Grow the snake again to account for the chili effect
                    ((Chili) currentFood).hideChili(); // Hide the chili
                    currentFood = new FoodFactory(grid).createRandomFood(); // Create new food
                }
            }
        }

        // Check for boundary collisions
        if (isBoundaryCollision(snake.getHead()) || isSelfCollision()) {
            game.setGameOver(true); // End the game if collision detected
        }
    }

    /**
     * Determines if there is a collision between two pictures.
     *
     * @param pic1 The first picture
     * @param pic2 The second picture
     * @return true if there is a collision, false otherwise
     */
    private boolean isCollision(Picture pic1, Picture pic2) {
        return pic1.getX() < pic2.getX() + pic2.getWidth() &&
                pic1.getX() + pic1.getWidth() > pic2.getX() &&
                pic1.getY() < pic2.getY() + pic2.getHeight() &&
                pic1.getY() + pic1.getHeight() > pic2.getY();
    }

    /**
     * Checks if the snake's head has collided with the grid boundaries.
     *
     * @param picture The picture representing the snake's head
     * @return true if there is a boundary collision, false otherwise
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
     * Checks if the snake has collided with itself.
     *
     * @return true if there is a self-collision, false otherwise
     */
    private boolean isSelfCollision() {
        Picture head = snake.getHead();
        for (Picture bodyPart : snake.getBody()) {
            if (isCollision(head, bodyPart)) {
                return true;
            }
        }
        return false;
    }
}
