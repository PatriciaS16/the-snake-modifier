package com.codeforall.online.thesnakemodifier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * The score class is responsible for
 * gain points everytime the snake eats a fruit, save and update the high score
 */
public class Score {

    /**
     * The current score of the player
     */
    private int score;

    /**
     * Singleton instance of the Score class
     */
    private static Score instance;

    /**
     * The highest score achieved in the game
     */
    private int highScore;

    /**
     * Flag to track if double score is active
     */
    private boolean doubleScoreActive = false;

    /**
     * Timer for double score duration
     */
    private Timer doubleScoreTimer;

    /**
     * Flag to ensure double score can be used only once
     */
    private boolean doubleScoreUsed = false;

    /**
     * Private constructor for the Score class.
     * Initializes the score and loads the high score from file.
     */
    private Score() {
        score = 0;
        loadHighScore();
    }

    /**
     * Retrieves the singleton instance of the Score class.
     * @return the singleton instance of the Score class
     */
    public static Score getInstance() {
        if (instance == null) {
            instance = new Score();
        }
        return instance;
    }

    /**
     * Increases the score when the snake eats an apple.
     * The score is doubled when double score mode is active
     */
    public void snakeEatsApple() {
        // Increase the score by 100 points if double score mode is active
        // Otherwise, increase the score by 50 points
        score += (doubleScoreActive ? 100 : 50);
    }

    /**
     * Increases the score when the snake eats a chili.
     * The score is active when double score mode is active
     */
    public void snakeEatsChili() {
        // Increase the score by 200 points if double score mode is active
        // Otherwise, increase the score by 100 points
        score += (doubleScoreActive ? 200 : 100);
    }

    /**
     *   Adds a specified number of points to the score
     */
    public void addPoints(int points) {
        score += points;
    }

    /**
     * Gets the current score.
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Resets the score to zero.
     */
    public void resetScore() {
        score = 0;
    }

    /**
     * Checks if the current score is higher than the high score.
     * Updates the high score and saves it to the file if necessary.
     * @throws IOException if an I/O error occurs while saving the high score
     */
    public void checkScore() throws IOException {
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
    }

    /**
     * Loads the high score from the "HighScore.txt" file.
     */
    private void loadHighScore() {
        File file = new File("HighScore.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    highScore = Integer.parseInt(line);
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            highScore = 0;
        }
    }

    /**
     * Saves the high score to the "HighScore.txt" file.
     * @throws IOException if an I/O error occurs while saving the high score
     */
    private void saveHighScore() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("HighScore.txt"))) {
            writer.write(String.valueOf(highScore));
        }
    }

    /**
     * Activates the double score mode for 10 seconds if it hasn't been used yet.
     */
    public void activateDoubleScore() {

        if (!doubleScoreUsed) {
            doubleScoreActive = true;    // Enable double score mode
            doubleScoreUsed = true;      // Mark double score mode as used to prevent reactivation

            // Stop any existing double score timer
            if (doubleScoreTimer != null) {
                doubleScoreTimer.stop();
            }

            // Create a timer to deactivate double score mode after 10 seconds
            doubleScoreTimer = new Timer(10000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doubleScoreActive = false; // Deactivate double score mode
                }
            });

            doubleScoreTimer.setRepeats(false); // Ensure the timer runs only once
            doubleScoreTimer.start(); // Start the timer
        }
    }

    /**
     * Checks whether the double score mode is currently active.
     * @return true if double score mode is active, false otherwise
     */
    public boolean isDoubleScoreActive() {

        // Return the status of doubleScoreActive to indicate if mode is enabled
        return doubleScoreActive;
    }
    /**
     * Gets the high score.
     * @return the high score
     */
    public int getHighScore() {
        return highScore;
    }
}