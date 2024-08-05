package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * The MyKeyboard class handles keyboard input to control the snake movement in game
 */
public class MyKeyboard implements KeyboardHandler {

    /**
     * Keyboard instance to handle keyboard events
     */
    private Keyboard keyboard;

    /**
     * Snake instance to interact with
     */
    private Snake snake;

    /**
     * Boolean to ensure cheat code is used only once
     */
    private boolean cheatCodeActivated = false;

    /**
     * Initializes the keyboard event handling
     * Sets keyboard instance and add listeners for specific keys
     */
    public void init () {

        // Create a new keyboard instance
        keyboard = new Keyboard(this);

        // Create a keyboard event for the key "D" (move right)
        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // Create a keyboard event for the key "A" (move left)
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // Create a keyboard event for the key "S" (move down)
        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // Create a keyboard event for the key "W" (move up)
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // Create a keyboard event for the key "SPACE" (cheat code)
        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // Add listeners for defined keyboard events
        keyboard.addEventListener(right);    
        keyboard.addEventListener(left);
        keyboard.addEventListener(down);
        keyboard.addEventListener(up);
        keyboard.addEventListener(space);
    }

    /**
     * Handles key press events
     * Determines which key was pressed and calls for the method on snake
     * @param keyboardEvent containing key information
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        // Determine which key was pressed
        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_D:
                // Print a message to indicate if key "D" was pressed
                System.out.println("Move right!");
                // Calls the snake method to move right
                snake.moveRight();
                break;
            case KeyboardEvent.KEY_A:
                // Print a message to indicate if key "A" was pressed
                System.out.println("Move left!");
                // Calls the snake method to move left
                snake.moveLeft();
                break;
            case KeyboardEvent.KEY_S:
                // Print a message to indicate if key "S" was pressed
                System.out.println("Move down!");
                // Calls the snake method to move down
                snake.moveDown();
                break;
            case KeyboardEvent.KEY_W:
                // Print a message to indicate if key "W" was pressed
                System.out.println("Move up!");
                // Calls the snake method to move up
                snake.moveUp();
                break;
            case KeyboardEvent.KEY_SPACE:
                if (!cheatCodeActivated) {
                    // Activate cheat code on space bar press
                    activateCheatCode();
                    cheatCodeActivated = true;
                }
                break;
            default:
                // Print a message if any other key is pressed
                System.out.println("Unknown key pressed!");
                break;
        }
    }

    /**
     * Handles key release events
     * @param keyboardEvent (currently unnecessary)
     */
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    /**
     * Set the snake instance to be controlled by keyboard
     * @param snake instance to be set
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * Activates the cheat code by adding 300 points to the score
     * It's called when the space bar is pressed but only if the cheat code hasn't been activated yet
     */
    private void activateCheatCode() {
        System.out.println("Cheat code activated: Adding 300 points!");
        Score.getInstance().addPoints(300); // Add 300 points to the score
    }
}

