package com.codeforall.online.thesnakemodifier.food;

import com.codeforall.online.thesnakemodifier.game.Grid;
import java.util.Random;

/**
 * FoodFactory creates Food instances based on a given FoodType or at random.
 */
public class FoodFactory {

    private final Grid grid;

    public FoodFactory(Grid grid) {
        this.grid = grid;
    }

    /**
     * Creates a food item of the specified type.
     */
    public Food createFood(FoodType foodType) {
        switch (foodType) {
            case CHILI:  return new Chili(grid);
            case FRUIT:
            default:     return new Fruit(grid);
        }
    }

    /**
     * Creates a random food item: 10% chance of Chili, 90% chance of Fruit.
     */
    public Food createRandomFood() {
        FoodType type = new Random().nextDouble() < 0.1 ? FoodType.CHILI : FoodType.FRUIT;
        return createFood(type);
    }
}
