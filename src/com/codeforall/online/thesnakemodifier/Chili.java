package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.Random;

/**
 * The Chili class represents a special type of food in the game.
 * It handles spawning, hiding, and retrieving the chili image.
 */
public class Chili {

    /**
     * The picture representing the chili
     */
    private Picture chili;

    /**
     *  The grid on which the chili will be place
     */
    private Grid grid;

    /**
     * Constructs a Chili instance and spawns the initial chili item.
     *
     * @param grid The grid on which the chili will be placed
     */
    public Chili(Grid grid) {

        this.grid = grid;
        spawnChili();  // Spawn the initial chili item
    }

    /**
     * Spawns a new chili item at a random position within the grid boundaries.
     * This method also ensures that the chili is not placed too close to the grid edges.
     */
    public void spawnChili() {

        // Create a Random object to generate random numbers for the chili position
        Random random = new Random();

        // Define a safe margin around the grid edges to prevent placing chili too close to edges
        int safeMargin = 50;

        // Define the minimum x and y coordinates where chili can be placed
        int minX = 50;
        int minY = 50;

        // Calculate the maximum x and y coordinates where chili can be placed
        // This ensures the chili stays within the grid boundaries considering the safe margin
        int maxX = grid.getWidth() - safeMargin - minX;
        int maxY = grid.getHeight() - safeMargin - minY;

        // Generate a random x coordinate for the chili within the defined range
        int x = random.nextInt(maxX - minX + 1) + minX;

        // Generate a random y coordinate for the chili within the defined range
        int y = random.nextInt(maxY - minY + 1) + minY;

        // If there is already a chili on the screen, hide it before creating a new one
        if (chili != null) {
            hideChili();  // Call hideChili method to remove the old chili
        }

        // Create a new Picture object for the chili at the random x and y coordinates
        chili = new Picture(x, y, Game.PREFIX + "Chili.png");

        // Draw the new chili item on the screen
        chili.draw();
    }

    /**
     * Hides the current chili item by deleting its picture.
     * Also sets the chili reference to null after hiding it.
     */
    public void hideChili() {
        if (chili != null) {
            // Remove the chili picture from the display
            chili.delete();
            // Set the chili reference to null
            chili = null;
        }
    }

    /**
     * Gets the current picture representing the chili.
     *
     * @return The Picture object representing the chili
     */
    public Picture getPicture() {
        return chili;
    }
}
