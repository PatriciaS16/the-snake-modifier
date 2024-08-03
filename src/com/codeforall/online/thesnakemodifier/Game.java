package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 * The Game class is responsible for managing the overall game state, including
 * the snake, grid, food, collision handling, and game loop. It initializes game components
 * and coordinates game updates, including detecting game over conditions.
 */
public class Game {

    public static final String PREFIX = "resources/";  // Directory prefix for resource files

    // The snake object representing the player
    private Snake snake;


    // The grid on which the game is played
    private Grid grid;

    // The food object that the snake can eat
    private Food food;

    // Handles collision detection and game state changes
    private CollisionHandler collisionHandler;

    //The AudioPlayer to use sound during the game
    private AudioPlayer audioPlayer;

    // Flag to indicate if the game is over
    private boolean gameOver = false;

    // Timer for controlling the game loop
    private Timer gameLoopTimer;

    /**
     * Constructs a Game instance and initializes game components.
     * Sets up the grid, snake, food, and collision handler.
     */
    public Game() {
        int padding = 10;

        // Initialize the background picture and grid
        Picture background = new Picture(10, 10, Game.PREFIX + "LighterBackgroundLog.png");
        this.grid = new Grid(background, padding);


        // Initialize the snake with its picture and the grid
        Picture snakePicture = new Picture(400, 350, Game.PREFIX + "SnakeHead.png");
        this.snake = new Snake(snakePicture, grid);



        // Initialize the food object and collision handler
        this.food = new Food(grid);
        this.collisionHandler = new CollisionHandler(snake, grid, food, this);

        this.audioPlayer = new AudioPlayer();
        audioPlayer.addAudio("arcade game",  "/arcadeLoop.wav");
        audioPlayer.addAudio("bonus", "/bonus.wav");
        audioPlayer.addAudio("food", "/Food.wav");
        audioPlayer.addAudio("game over", "/ gameOver.wav");
        audioPlayer.addAudio("intro",  " / intro.wav");
        audioPlayer.addAudio("lose Score", "/ losePoints.wav");

    }

    /**
     * Gets the snake object for the game.
     *
     * @return The snake object
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Gets the food object for the game.
     *
     * @return The food object
     */
    public Food getFood() {
        return food;
    }



    /**
     * Updates the game state by moving the snake and checking for collisions.
     */
    public void updateGame() {
        new Thread(() -> {
        if (!gameOver) {
            audioPlayer.playAudio("arcade game");
        }
    }).start();

        snake.move();                   // Move the snake
        collisionHandler.checkCollisions();// Check for collisions
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over; false otherwise
     */
    public boolean checkGameOver() {
        return gameOver;
    }

    /**
     * Sets the game over state.
     *
     * @param gameOver true to indicate the game is over; false otherwise
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        if (gameOver) {

            audioPlayer.playAudio("game over");
        }
    }

    /**
     * Ends the game, stops the game loop timer, and exits the program.
     */
    public void endGame() {
        if (gameOver) {
            if (gameLoopTimer != null) {
                gameLoopTimer.stop();  // Stop the game loop timer
            }
            System.out.println("Game over!"); // Print game over message

            // Show the game over screen
            GameOverScreen gameOverScreen = new GameOverScreen();
            GameOverMouse gameOverMouse = new GameOverMouse();
            gameOverMouse.setGameOverScreen(gameOverScreen);
            gameOverMouse.init();
        }
    }

    /**
     * Starts the game loop timer which repeatedly updates the game and checks for game over conditions.
     */
    public void startGameLoop() {
        gameLoopTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();          // Update the game state
               if (checkGameOver()) { // Check if the game is over
                   endGame();         // End the game if over
              }
            }
        });
        gameLoopTimer.start(); // Start the game loop timer
    }
}