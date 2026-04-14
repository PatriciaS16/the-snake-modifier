package com.codeforall.online.thesnakemodifier.score;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * The ScoreDisplay class is responsible for displaying the current score on screen.
 */
public class ScoreDisplay {

    /**
     * Text object for displaying the score
     */
    private Text scoreText;

    /**
     * Constructs a ScoreDisplay instance at the specified coordinates.
     *
     * @param x The X-coordinate where the score display will be positioned
     * @param y The Y-coordinate where the score display will be positioned
     */
    public ScoreDisplay(int x, int y) {
        scoreText = new Text(x, y, "Score: 0");
        scoreText.setColor(Color.WHITE);
        scoreText.draw();
        scoreText.grow(10, 10);
    }

    /**
     * Updates the score display with the current score.
     */
    public void updateScore() {
        int currentScore = Score.getInstance().getScore();
        scoreText.setText("Score: " + currentScore);
    }

    /**
     * Deletes the score text from the screen.
     */
    public void cleanup() {
        scoreText.delete();
    }
}
