package com.codeforall.online.thesnakemodifier.game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The Grid class represents the boundaries within which the snake moves.
 * It manages the size of the grid and checks if objects are within bounds.
 */
public class Grid {

    /**
     * Picture representing the background of the grid
     */
    private Picture background;

    /**
     * The padding around the edges of the grid
     */
    private int padding;

    /**
     * Constructs a Grid instance with the specified background and padding.
     * Draws the background immediately.
     *
     * @param background The picture representing the grid background
     * @param padding    The padding to apply around the edges of the grid
     */
    public Grid(Picture background, int padding) {
        this.background = background;
        this.padding = padding;
        background.draw();
    }

    /**
     * Checks if the coordinates and dimensions are within the grid boundaries.
     *
     * @param x      The X coordinate of the object
     * @param y      The Y coordinate of the object
     * @param width  The width of the object
     * @param height The height of the object
     * @return true if the object is within bounds, false otherwise
     */
    public boolean isWithinBounds(int x, int y, int width, int height) {
        int gridWidth = background.getWidth();
        int gridHeight = background.getHeight();

        boolean withinLeft = x >= padding - 10;
        boolean withinTop = y >= padding - 10;
        boolean withinRight = x + width <= gridWidth - padding + 20;
        boolean withinBottom = y + height <= gridHeight - padding + 20;

        return withinLeft && withinTop && withinRight && withinBottom;
    }

    public int getPadding() {
        return padding;
    }

    public int getWidth() {
        return background.getWidth();
    }

    public int getHeight() {
        return background.getHeight();
    }

    /**
     * Deletes the grid background from the screen.
     */
    public void cleanup() {
        background.delete();
    }
}
