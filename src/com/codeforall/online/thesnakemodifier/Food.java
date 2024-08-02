package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Random;

/**
 * The Food class represents the food items in the game.
 * It handles the spawning, hiding, and interaction of food with the snake.
 */
public class Food {
    // The picture representing the food
    private Picture food;

    // The grid on which the food is placed
    private Grid grid;

    /**
     * Constructs a Food instance and spawns the initial food.
     *
     * @param grid The grid on which the food will be placed
     */
    public Food(Grid grid) {
        this.grid = grid;
        spawnFood();  // Spawn the initial food item
    }

    /**
     * Spawns a new food item at a random position within the grid boundaries.
     * If there is already food present, it will hide the old food first.
     */
    public void spawnFood() {
        // Create a Random object to generate random numbers
        Random random = new Random();

        // Size of safe margin around the grid edges to prevent placing food too close to edges
        int safeMargin = 50;

        // Define the minimum x and y coordinates where food can be placed
        int minX = 50;
        int minY = 50;

        // Calculate the maximum x and y coordinates where food can be placed
        // This ensures the food stays within the grid boundaries considering the safe margin
        int maxX = grid.getWidth() - safeMargin - minX;
        int maxY = grid.getHeight() - safeMargin - minY;

        // Generate a random x coordinate for the food within the defined range
        int x = random.nextInt(maxX - minX + 1) + minX;

        // Generate a random y coordinate for the food within the defined range
        int y = random.nextInt(maxY - minY + 1) + minY;

        // If there is already food on the screen, hide it before creating a new one
        if (food != null) {
            hideFood();  // Call hideFood method to remove the old food
        }

        // Create a new Picture object for the food at the random x and y coordinates
        food = new Picture(x, y, Game.PREFIX + "Fruit.png");

        // Draw the new food item on the screen
        food.draw();
    }

    /**
     * Hides the current food item by deleting its picture.
     * This method sets the food picture to null after hiding it.
     */
    public void hideFood() {
        if (food != null) {
            // Remove the food picture from the display
            food.delete();
            // Set the food reference to null
            food = null;
        }
    }

    /**
     * Handles the event when the snake eats the food.
     * The snake grows, and a new food item is spawned.
     *
     * @param snake The snake that has eaten the food
     */
    public void handleSnakeEat(Snake snake) {
        snake.grow();       // Grow the snake
        spawnFood();        // Spawn a new food item
    }

    /**
     * Gets the current picture representing the food.
     *
     * @return The Picture object representing the food
     */
    public Picture getPicture() {
        return food;
    }
}
