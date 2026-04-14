package com.codeforall.online.thesnakemodifier.food;

import com.codeforall.online.thesnakemodifier.game.Game;
import com.codeforall.online.thesnakemodifier.game.Grid;
import com.codeforall.online.thesnakemodifier.score.Score;

/**
 * Fruit is a basic food item. Eating it grows the snake by 1 and awards 50 points.
 */
public class Fruit extends AbstractFood {

    public Fruit(Grid grid) {
        super(grid, Game.PREFIX + "Fruit.png");
    }

    @Override
    public void applyScore() {
        Score.getInstance().snakeEatsApple();
    }

    @Override
    public int getGrowthAmount() {
        return 1;
    }
}
