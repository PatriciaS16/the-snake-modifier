package com.codeforall.online.thesnakemodifier.movement;

import com.codeforall.online.thesnakemodifier.game.Game;
import com.codeforall.online.thesnakemodifier.game.Grid;
import com.codeforall.online.thesnakemodifier.snake.Snake;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The SnakeMovement class handles all movement logic for the snake.
 * It tracks the current direction and moves the snake accordingly each game tick.
 */
public class SnakeMovement {

    /**
     * The snake instance to move
     */
    private Snake snake;

    /**
     * The grid used for boundary checking
     */
    private Grid grid;

    /**
     * The current movement direction
     */
    private Direction direction = Direction.RIGHT;

    /**
     * Constructs a SnakeMovement instance.
     *
     * @param snake The snake to control
     * @param grid  The grid for boundary checking
     */
    public SnakeMovement(Snake snake, Grid grid) {
        this.snake = snake;
        this.grid = grid;
    }

    /**
     * Changes direction to LEFT if not currently moving RIGHT.
     */
    public void moveLeft() {
        if (direction != Direction.RIGHT) direction = Direction.LEFT;
    }

    /**
     * Changes direction to RIGHT if not currently moving LEFT.
     */
    public void moveRight() {
        if (direction != Direction.LEFT) direction = Direction.RIGHT;
    }

    /**
     * Changes direction to UP if not currently moving DOWN.
     */
    public void moveUp() {
        if (direction != Direction.DOWN) direction = Direction.UP;
    }

    /**
     * Changes direction to DOWN if not currently moving UP.
     */
    public void moveDown() {
        if (direction != Direction.UP) direction = Direction.DOWN;
    }

    /**
     * Moves the snake one step in the current direction.
     * Handles growth and boundary checking.
     */
    public void move() {
        int dx = 0;
        int dy = 0;

        switch (direction) {
            case LEFT:  dx = -50; break;
            case RIGHT: dx = 50;  break;
            case UP:    dy = -50; break;
            case DOWN:  dy = 50;  break;
        }

        Picture head = snake.getHead();
        int newX = head.getX() + dx;
        int newY = head.getY() + dy;

        System.out.println("Attempting to move. Direction: " + direction);
        System.out.println("New Head Position: X: " + newX + " Y: " + newY);

        if (grid.isWithinBounds(newX, newY, head.getWidth(), head.getHeight())) {
            if (snake.isGrowing()) {
                Picture newBodyPart = new Picture(head.getX(), head.getY(), Game.PREFIX + "SnakeBody.png");
                snake.getBody().add(0, newBodyPart);
                newBodyPart.draw();
                snake.resetGrowing();
            } else if (!snake.getBody().isEmpty()) {
                Picture tail = snake.getBody().remove(snake.getBody().size() - 1);
                tail.translate(head.getX() - tail.getX(), head.getY() - tail.getY());
                snake.getBody().add(0, tail);
            }

            head.translate(dx, dy);
            System.out.println("Head moved to: X: " + head.getX() + " Y: " + head.getY());
        } else {
            System.out.println("Move out of bounds. Movement stopped.");
        }
    }
}
