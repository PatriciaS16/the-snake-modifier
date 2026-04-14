package com.codeforall.online.thesnakemodifier.input;

import com.codeforall.online.thesnakemodifier.movement.SnakeMovement;
import com.codeforall.online.thesnakemodifier.score.Score;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * The MyKeyboard class handles keyboard input to control snake movement in the game.
 */
public class MyKeyboard implements KeyboardHandler {

    /**
     * Keyboard instance to handle keyboard events
     */
    private Keyboard keyboard;

    /**
     * SnakeMovement instance to control snake direction
     */
    private SnakeMovement snakeMovement;

    /**
     * Boolean to ensure cheat code is used only once
     */
    private boolean cheatCodeActivated = false;

    /**
     * Initializes the keyboard event handling.
     * Sets up listeners for all relevant keys.
     */
    public void init() {
        keyboard = new Keyboard(this);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent easymode = new KeyboardEvent();
        easymode.setKey(KeyboardEvent.KEY_K);
        easymode.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(right);
        keyboard.addEventListener(left);
        keyboard.addEventListener(down);
        keyboard.addEventListener(up);
        keyboard.addEventListener(space);
        keyboard.addEventListener(easymode);
    }

    /**
     * Handles key press events and delegates to the appropriate movement method.
     *
     * @param keyboardEvent containing key information
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_D:
                System.out.println("Move right!");
                snakeMovement.moveRight();
                break;
            case KeyboardEvent.KEY_A:
                System.out.println("Move left!");
                snakeMovement.moveLeft();
                break;
            case KeyboardEvent.KEY_S:
                System.out.println("Move down!");
                snakeMovement.moveDown();
                break;
            case KeyboardEvent.KEY_W:
                System.out.println("Move up!");
                snakeMovement.moveUp();
                break;
            case KeyboardEvent.KEY_SPACE:
                if (!cheatCodeActivated) {
                    activateCheatCode();
                    cheatCodeActivated = true;
                }
                break;
            case KeyboardEvent.KEY_K:
                if (!Score.getInstance().isDoubleScoreActive()) {
                    System.out.println("Double score activated!");
                    Score.getInstance().activateDoubleScore();
                } else {
                    System.out.println("Double score already used.");
                }
                break;
            default:
                System.out.println("Unknown key pressed!");
                break;
        }
    }

    /**
     * Handles key release events (currently unused).
     */
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    /**
     * Sets the SnakeMovement instance to be controlled by this keyboard.
     *
     * @param snakeMovement the SnakeMovement instance to set
     */
    public void setSnakeMovement(SnakeMovement snakeMovement) {
        this.snakeMovement = snakeMovement;
    }

    /**
     * Activates the cheat code by adding 300 points to the score.
     * Only works once per game.
     */
    private void activateCheatCode() {
        System.out.println("Cheat code activated: Adding 300 points!");
        Score.getInstance().addPoints(300);
    }
}
