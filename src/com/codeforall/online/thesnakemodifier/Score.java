package com.codeforall.online.thesnakemodifier;

import javax.swing.*;
import java.io.*;

public class Score {

    /**
     * The score class is responsible for:
     * gain points everytime the snake eats a fruit, save and update the high score
     */

    private int score;

    private static Score instance;

    private int highScore;


    private Score() {
        score = 0;
    }

    public static Score getInstance() {
        if (instance == null) {
            instance = new Score();
        }
        return instance;
    }

    /**
     * method to handle the score when the snake eats an apple
     */

    public void snakeEatsApple() {
        score += 50;
    }


    public int getScore() {
        return score;
    }

    /**
     * method to reset the score when the user loses the game
     */
    public void resetScore() {
        score = 0;
    }



    /**
     * Method to handle the points gained when the snake eats an apple
     */
    public int SnakeEatsApple(int score) {
        this.score = score;
        score += 50;
        return score;
    }

    /**
     * Method to read the high score fom the file HighScore.txt
     * Create a buffered reader to read strings from the system
     * If the file is not found, returns 0
     */

    public String GetHighScore() throws IOException {
        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("HighScore.txt");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        } catch (Exception e) {
            return "0";
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * method to check if the score is higher than the high score
     * IF true than update the high score
     */
    public void CheckScore() throws IOException {
        if (score > highScore) {
            String message = JOptionPane.showInputDialog("You set a new high score");
            highScore += score;

            // save the score
            File scoreFile = new File("HighScore.txt");
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            FileWriter writeFile = null;
            BufferedWriter writer = null;
            try {
                writeFile = new FileWriter(scoreFile);
                writer = new BufferedWriter(writeFile);
                writer.write(this.highScore);
            }
            catch (Exception e) {
                // errors
            }
            finally {
                try {
                    if (writer != null)
                        writer.close();
                }
                catch  (Exception e){

                }
            }
        }
    }
}

