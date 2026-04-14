package com.codeforall.online.thesnakemodifier.food;

import com.codeforall.online.thesnakemodifier.game.Game;
import com.codeforall.online.thesnakemodifier.game.Grid;
import com.codeforall.online.thesnakemodifier.score.Score;

/**
 * Chili is a special food item. Eating it grows the snake by 2 and awards 100 points.
 */
public class Chili extends AbstractFood {

    public Chili(Grid grid) {
        super(grid, Game.PREFIX + "Chili.png");
    }

    @Override
    public void applyScore() {
        Score.getInstance().snakeEatsChili();
    }

    @Override
    public int getGrowthAmount() {
        return 2;
    }
}
