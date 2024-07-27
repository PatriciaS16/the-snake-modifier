package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The Grid class represents the boundaries within the snake movement
 * It manages the size of the grid and checks if objects are within bounds
 */
public class Grid {

    /**
     * Picture background representing the background of the grid
     */
    private Picture background;

    /**
     * The padding around the edges of the grid
     */
    private int padding;

    /**
     * Constructs a grid instance with specified background and padding
     * Initializes grid and draws the background
     * @param background The picture representing the grid background
     * @param padding The padding to apply around the edges of the grid
     */
    public Grid(Picture background, int padding) {
        this.background = background;
        this.padding = padding;
        background.draw();
    }

    /**
     * Checks if the coordinates and dimensions are within the grid boundaries
     *
     * @param x The X coordinate of the object
     * @param y The Y coordinate of the object
     * @param width The Width of the object
     * @param height The Height of the object
     * @return True if object is within bounds, else false
     */
    public boolean isWithinBounds(int x, int y, int width, int height) {
        return x >= padding &&
                y >= padding &&
                (x + width) <= (background.getWidth() - padding + 20) &&
                (y + height) <= (background.getHeight() - padding + 20);
    }
}
