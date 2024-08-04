package com.codeforall.online.thesnakemodifier;

import java.util.Random;

/**
 * The FoodFactory class is responsible for creating food objects.
 * It can generate specific types of food based on the provided FoodType.
 */
public class FoodFactory {

    /**
     * The grid on which the food items will be placed
     */
    private Grid grid;

    /**
     * Constructs a FoodFactory instance with the specified grid.
     *
     * @param grid The grid on which the food items will be placed
     */
    public FoodFactory(Grid grid) {
        this.grid = grid;
    }

    /**
     * Creates a food object based on the provided FoodType.
     *
     * @param foodType The type of food to create (CHILI or FRUIT)
     * @return An Object representing the food item (Chili or Fruit)
     */
    public Object createFood(FoodType foodType) {
        switch (foodType) {
            case CHILI:
                return new Chili(grid); // Create and return a Chili object
            case FRUIT:
            default:
                return new Fruit(grid); // Create and return a Fruit object (default case)
        }
    }

    /**
     * Creates a random food object, with a small chance of creating a Chili.
     *
     * @return An Object representing a random food item (Chili or Fruit)
     */
    public Object createRandomFood() {

        Random random = new Random();

        // Determine food type: 10% chance of Chili and 90% chance of Fruit
        FoodType type = random.nextDouble() < 0.1 ? FoodType.CHILI : FoodType.FRUIT;
        return createFood(type); // Create and return the selected food type
    }
}
