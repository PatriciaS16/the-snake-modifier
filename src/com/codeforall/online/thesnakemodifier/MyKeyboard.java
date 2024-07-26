package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class MyKeyboard implements KeyboardHandler {

    private Keyboard keyboard;

    private Snake snake;

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void init () {
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

        keyboard.addEventListener(right);    
        keyboard.addEventListener(left);
        keyboard.addEventListener(down);
        keyboard.addEventListener(up);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_D:
                System.out.println("Move right!");
                snake.moveRight();
                break;
            case KeyboardEvent.KEY_A:
                System.out.println("Move left!");
                snake.moveLeft();
                break;
            case KeyboardEvent.KEY_S:
                System.out.println("Move down!");
                snake.moveDown();
                break;
            case KeyboardEvent.KEY_W:
                System.out.println("Move up!");
                snake.moveUp();
                break;
            default:
                System.out.println("Unknown key pressed!");
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
