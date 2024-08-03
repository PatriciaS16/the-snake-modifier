package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.Random;

/**
 * The Fruit class represents a basic type of food in the game.
 * It handles spawning, hiding, and retrieving the fruit image.
 */
public class Fruit {

    /**
     * The picture representing the fruit
     */
    private Picture fruit;

    /**
     * The grid on which the fruit will be placed
     */
    private Grid grid;

    /**
     * Constructs a Fruit instance and spawns the initial fruit item.
     *
     * @param grid The grid on which the fruit will be placed
     */
    public Fruit(Grid grid) {

        this.grid = grid;
        spawnFruit();  // Spawn the initial fruit item
    }

    /**
     * Spawns a new fruit item at a random position within the grid boundaries.
     * This method also ensures that the fruit is not placed too close to the grid edges.
     */
    public void spawnFruit() {

        // Create a Random object to generate random numbers for the fruit position
        Random random = new Random();

        // Define a safe margin around the grid edges to prevent placing fruit too close to edges
        int safeMargin = 50;

        // Define the minimum x and y coordinates where fruit can be placed
        int minX = 50;
        int minY = 50;

        // Calculate the maximum x and y coordinates where fruit can be placed
        // This ensures the fruit stays within the grid boundaries considering the safe margin
        int maxX = grid.getWidth() - safeMargin - minX;
        int maxY = grid.getHeight() - safeMargin - minY;

        // Generate a random x coordinate for the fruit within the defined range
        int x = random.nextInt(maxX - minX + 1) + minX;

        // Generate a random y coordinate for the fruit within the defined range
        int y = random.nextInt(maxY - minY + 1) + minY;

        // If there is already a fruit on the screen, hide it before creating a new one
        if (fruit != null) {
            hideFruit();  // Call hideFruit method to remove the old fruit
        }

        // Create a new Picture object for the fruit at the random x and y coordinates
        fruit = new Picture(x, y, Game.PREFIX + "Fruit.png");

        // Draw the new fruit item on the screen
        fruit.draw();
    }

    /**
     * Hides the current fruit item by deleting its picture.
     * Also sets the fruit reference to null after hiding it.
     */
    public void hideFruit() {
        if (fruit != null) {
            // Remove the fruit picture from the display
            fruit.delete();
            // Set the fruit reference to null
            fruit = null;
        }
    }

    /**
     * Gets the current picture representing the fruit.
     *
     * @return The Picture object representing the fruit
     */
    public Picture getPicture() {
        return fruit;
    }
}
