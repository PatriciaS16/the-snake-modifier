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
     * Constructs a Snake instance with the specified picture
     * Initializes the snake's position and starts its automatic movement
     * @param picture representing the snake
     */
    public Snake(Picture picture) {
        this.picture = picture;
        picture.draw();
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
        switch (direction) {
            case "LEFT":
                picture.translate(-10,0);
                break;
            case  "RIGHT":
                picture.translate(10,0);
                break;
            case "UP":
                picture.translate(0,-10);
                break;
            case "DOWN":
                picture.translate(0,10);
                break;
            default:
                break;
        }
    }

    /**
     * Starts the movement of the snake by creating a timer
     * The timer triggers the move method at regular intervals
     */
    private void startMovement() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        timer.start();
    }

    public void grow() {
    }

    public void reset() {
    }
}
