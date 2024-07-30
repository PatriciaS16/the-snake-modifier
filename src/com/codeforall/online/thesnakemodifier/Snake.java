package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Snake Class represents a snake in the game. Is handles the snake movement
 * and manages the direction movement
 */
public class Snake {

    /**
     * The picture object representing the snake head
     */
    private Picture picture;

    /**
     * The timer to control the movement of the snake
     */
    private Timer timer;

    /**
     * The current direction of the snake movement
     */
    private String direction = "RIGHT";

    /**
     * The grid where the snake is moving
     */
    private Grid grid;


    /**
     * Constructs a Snake instance with the specified picture
     * Initializes the snake's position and starts its automatic movement
     *
     * @param picture representing the snake
     * @param grid where snake is moving
     */
    public Snake(Picture picture, Grid grid) {
        // Initialize the snake head picture
        this.picture = picture;
        //Initialize the grid where the snake moves
        this.grid = grid;
        // Draw the snake head on the screen
        picture.draw();
        // Start the snake movement
        startMovement();
    }

    /**
     * Sets the direction of the snake to the left
     * The snake will move left in the next movement update
     */
    public void moveLeft(){
        direction = "LEFT";
    }

    /**
     * Sets the direction of the snake to the right
     * The snake will move right in the next movement update
     */
    public void moveRight() {
        direction = "RIGHT";
    }

    /**
     * Sets the direction of the snake to up
     * The snake will move up in the next movement update
     */
    public void moveUp() {
        direction = "UP";
    }

    /**
     * Sets the direction of the snake to down
     * The snake will move down in the next movement update
     */
    public void moveDown() {
        direction = "DOWN";
    }

    /**
     * Moves the snake in the chosen direction
     */
    private void move() {

        /**
         * Initialize movement 'D' for x and y coordinates
         */
        int dx = 0;
        int dy = 0;

        // Determine the movement direction and set dx and dy
        switch (direction) {
            case "LEFT":
                // Move left by decreasing x
                dx = - 10;
                break;
            case "RIGHT":
                // Move right by increasing x
                dx = 10;
                break;
            case "UP":
                // Move up by decreasing y
                dy = - 10;
                break;
            case "DOWN":
                // Move down by increasing y
                dy = 10;
                break;
            default:
                break;
        }

        // Calculate the new position of the snake head
        int newX = picture.getX() + dx;
        int newY = picture.getY() + dy;

         // Check if new position is within the grid boundaries
        if (grid.isWithinBounds(newX, newY, picture.getWidth(), picture.getHeight())) {
            // Move the snake head to new position
            picture.translate(dx, dy);
        }
    }

    /**
     * Starts the movement of the snake by creating a timer
     * The timer triggers the move method at regular intervals
     */
    private void startMovement() {
        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        timer.start();
    }
}
