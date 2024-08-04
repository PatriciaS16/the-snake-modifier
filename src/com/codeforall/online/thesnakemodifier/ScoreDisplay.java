package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * The ScoreDisplay class is responsible for displaying the current score.
 */
public class ScoreDisplay {

    /**
     * Text object for displaying the score
     */
    private Text scoreText;

    /**
     *  X-coordinate for the position of the score display
     */
    private int scoreX;

    /**
     *  Y-coordinate for the position of the score display
     */
    private int scoreY;

    /**
     * Constructs a ScoreDisplay instance with specified coordinates.
     *
     * @param x The X-coordinate where the score display will be positioned
     * @param y The Y-coordinate where the score display will be positioned
     */
    public ScoreDisplay(int x, int y) {

        //  Set the X-coordinate
        this.scoreX = x;
        // Set the Y-coordinate
        this.scoreY = y;

        // Create a Text object to display the score at the specified position
        scoreText = new Text(scoreX, scoreY, "Score: 0");
        scoreText.setColor(Color.WHITE);  // Set the color of the text to white
        scoreText.draw();  // Draw the text on the screen
        scoreText.grow(10, 10);  // Increase the size of the text for better visibility
    }

    /**
     * Updates the score display with the current score.
     */
    public void updateScore() {

        // Get the current score from the Score singleton instance
        int currentScore = Score.getInstance().getScore();
        // Update the text with the new score value
        scoreText.setText("Score: " + currentScore);
    }
}