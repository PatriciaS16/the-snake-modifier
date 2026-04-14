package com.codeforall.online.thesnakemodifier.game;

import com.codeforall.online.thesnakemodifier.audio.AudioPlayer;
import com.codeforall.online.thesnakemodifier.food.Food;
import com.codeforall.online.thesnakemodifier.food.FoodFactory;
import com.codeforall.online.thesnakemodifier.snake.Snake;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * CollisionHandler detects and responds to collisions between the snake
 * and food, the grid boundary, or itself.
 */
public class CollisionHandler {

    private final Snake snake;
    private final Grid grid;
    private final Game game;
    private final AudioPlayer audioPlayer;
    private final FoodFactory foodFactory;
    private Food currentFood;

    public CollisionHandler(Snake snake, Grid grid, Food food, Game game, AudioPlayer audioPlayer) {
        this.snake = snake;
        this.grid = grid;
        this.currentFood = food;
        this.game = game;
        this.audioPlayer = audioPlayer;
        this.foodFactory = new FoodFactory(grid);
    }

    /** Checks all collision types and reacts accordingly. */
    public void checkCollisions() {
        checkFoodCollision();
        checkDeadlyCollisions();
    }

    /** Hides the current food item — called during game cleanup. */
    public void cleanup() {
        if (currentFood != null) {
            currentFood.hide();
        }
    }

    private void checkFoodCollision() {
        if (currentFood == null || currentFood.getPicture() == null) {
            return;
        }
        if (!isCollision(snake.getHead(), currentFood.getPicture())) {
            return;
        }

        audioPlayer.playSoundEffects("food");
        currentFood.applyScore();
        for (int i = 0; i < currentFood.getGrowthAmount(); i++) {
            snake.grow();
        }
        currentFood.hide();
        currentFood = foodFactory.createRandomFood();
    }

    private void checkDeadlyCollisions() {
        if (isBoundaryCollision(snake.getHead()) || isSelfCollision()) {
            game.setGameOver(true);
        }
    }

    private boolean isCollision(Picture a, Picture b) {
        return a.getX() < b.getX() + b.getWidth()  &&
               a.getX() + a.getWidth()  > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + a.getHeight() > b.getY();
    }

    private boolean isBoundaryCollision(Picture picture) {
        return picture.getX() < grid.getPadding() ||
               picture.getY() < grid.getPadding() ||
               picture.getX() + picture.getWidth()  > grid.getWidth()  - grid.getPadding() ||
               picture.getY() + picture.getHeight() > grid.getHeight() - grid.getPadding();
    }

    private boolean isSelfCollision() {
        for (Picture bodyPart : snake.getBody()) {
            if (isCollision(snake.getHead(), bodyPart)) {
                return true;
            }
        }
        return false;
    }
}
