package com.codeforall.online.thesnakemodifier.snake;

import com.codeforall.online.thesnakemodifier.game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.ArrayList;
import java.util.List;

/**
 * The Snake class represents the snake in the game.
 * It holds the snake's head, body parts, and growth state.
 */
public class Snake {

    /**
     * The head of the snake
     */
    private Picture head;

    /**
     * The list of body parts of the snake
     */
    private List<Picture> body;

    /**
     * Flag to determine if the snake should grow on the next move
     */
    private boolean growing = false;

    /**
     * Constructs a Snake instance with the specified head picture.
     * Draws the head and initial tail piece.
     *
     * @param head The picture representing the head of the snake
     */
    public Snake(Picture head) {
        this.head = head;
        this.body = new ArrayList<>();

        head.draw();

        Picture tail = new Picture(head.getX() - head.getWidth(), head.getY(), Game.PREFIX + "SnakeEnd.png");
        this.body.add(0, tail);

        for (Picture part : body) {
            part.draw();
        }
    }

    /**
     * Sets the flag to grow the snake on the next move.
     */
    public void grow() {
        growing = true;
    }

    /**
     * Returns whether the snake is currently set to grow.
     * @return true if the snake should grow, false otherwise
     */
    public boolean isGrowing() {
        return growing;
    }

    /**
     * Resets the growing flag after the snake has grown.
     */
    public void resetGrowing() {
        growing = false;
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

    /**
     * Deletes all snake visuals from the screen.
     */
    public void cleanup() {
        head.delete();
        for (Picture part : body) {
            part.delete();
        }
        body.clear();
    }
}
