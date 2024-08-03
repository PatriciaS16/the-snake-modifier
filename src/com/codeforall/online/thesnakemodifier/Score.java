package com.codeforall.online.thesnakemodifier;

import javax.swing.*;
import java.io.*;

public class Score {

    /**
     * variable to store the current score
     */
    private int score;

    /**
     * store the high score that the user achieves
     */
    private int highScore;


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

