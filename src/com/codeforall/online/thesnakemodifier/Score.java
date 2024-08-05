package com.codeforall.online.thesnakemodifier;

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
     */
    public void snakeEatsApple() {
        score += 50;
    }

    /**
     * Increases the score when the snake eats a chili.
     */
    public void snakeEatsChili() {
        score += 100;
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
     * Gets the high score.
     * @return the high score
     */
    public int getHighScore() {
        return highScore;
    }
}