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
        Random random = new Random();

        // Calculate random position for the food within the grid boundaries
        int x = random.nextInt(grid.getWidth() - 50) + grid.getPadding();
        int y = random.nextInt(grid.getHeight() - 50) + grid.getPadding();

        // Hide the old food if it exists
        if (food != null) {
            hideFood();
        }

        // Create a new food item and draw it
        food = new Picture(x, y, Game.PREFIX + "Fruit.png");
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
