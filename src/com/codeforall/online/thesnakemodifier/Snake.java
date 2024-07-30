package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The Snake class represents the snake in the game.
 * It handles the snake's movement, growth, and interactions with the grid.
 */
public class Snake {
    private Picture head;  // The head of the snake
    private Grid grid;     // The grid on which the snake moves
    private List<Picture> body;  // The list of body parts of the snake
    private Timer movementTimer; // Timer for controlling snake movement
    private String direction = "RIGHT"; // Current movement direction of the snake
    private boolean growing = false;  // Flag to determine if the snake is growing

    /**
     * Constructs a Snake instance with the specified head, grid, and initial body parts.
     *
     * @param head The picture representing the head of the snake
     * @param grid The grid on which the snake moves
     */
    public Snake(Picture head, Grid grid) {
        this.head = head;
        this.grid = grid;
        int initialBody = 3;
        this.body = new ArrayList<>(initialBody);

        // Draw the head and body parts of the snake
        head.draw();
        for (Picture part : body) {
            part.draw();
        }

        // Start the movement of the snake
        startMovement();
    }

    /**
     * Changes the direction of the snake to LEFT if it's not already moving RIGHT.
     */
    public void moveLeft() {
        if (!direction.equals("RIGHT")) direction = "LEFT";
    }

    /**
     * Changes the direction of the snake to RIGHT if it's not already moving LEFT.
     */
    public void moveRight() {
        if (!direction.equals("LEFT")) direction = "RIGHT";
    }

    /**
     * Changes the direction of the snake to UP if it's not already moving DOWN.
     */
    public void moveUp() {
        if (!direction.equals("DOWN")) direction = "UP";
    }

    /**
     * Changes the direction of the snake to DOWN if it's not already moving UP.
     */
    public void moveDown() {
        if (!direction.equals("UP")) direction = "DOWN";
    }

    /**
     * Moves the snake in the current direction and handles the growth and boundary checks.
     */
    void move() {
        int dx = 0;  // Change in x coordinate
        int dy = 0;  // Change in y coordinate

        // Determine movement direction
        switch (direction) {
            case "LEFT": dx = -50; break;
            case "RIGHT": dx = 50; break;
            case "UP": dy = -50; break;
            case "DOWN": dy = 50; break;
        }

        int newX = head.getX() + dx;  // New x position of the head
        int newY = head.getY() + dy;  // New y position of the head

        // Debugging output for the intended movement
        System.out.println("Attempting to move. Direction: " + direction);
        System.out.println("New Head Position: X: " + newX + " Y: " + newY);

        // Check if the new position is within bounds
        if (grid.isWithinBounds(newX, newY, head.getWidth(), head.getHeight())) {
            // Handle growth of the snake
            if (growing) {
                // Add a new body part at the current head position
                Picture newBodyPart = new Picture(head.getX(), head.getY(), Game.PREFIX + "SnakeBody.png");
                body.add(0, newBodyPart);
                newBodyPart.draw();
                growing = false; // Reset growing flag
            } else if (!body.isEmpty()) {
                // Move the tail to the position of the new head
                Picture tail = body.remove(body.size() - 1);
                tail.translate(head.getX() - tail.getX(), head.getY() - tail.getY());
                body.add(0, tail);
            }

            // Move the head to the new position
            head.translate(dx, dy);

            // Ensure the head is properly updated
            System.out.println("Head moved to: X: " + head.getX() + " Y: " + head.getY());
        } else {
            System.out.println("Move out of bounds. Movement stopped.");
        }
    }

    /**
     * Sets the flag to grow the snake on the next move.
     */
    public void grow() {
        growing = true;
    }

    /**
     * Starts the movement timer to repeatedly move the snake.
     */
    private void startMovement() {
        movementTimer = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        movementTimer.start();
    }

    /**
     * Gets the head of the snake.
     * @return The picture representing the head of the snake
     */
    public Picture getHead() {
        return head;
    }

    /**
     * Gets the body of the snake.
     * @return The list of pictures representing the body parts of the snake
     */
    public List<Picture> getBody() {
        return body;
    }
}