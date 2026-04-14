package com.codeforall.online.thesnakemodifier.food;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * The Food interface defines the contract for all food types in the game.
 */
public interface Food {

    /** Returns the picture representing this food on screen. */
    Picture getPicture();

    /** Removes this food from the screen. */
    void hide();

    /** Updates the score when this food is eaten. */
    void applyScore();

    /** Returns how many times the snake should grow when this food is eaten. */
    int getGrowthAmount();
}
