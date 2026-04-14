package com.codeforall.online.thesnakemodifier.food;

import com.codeforall.online.thesnakemodifier.game.Grid;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.Random;

/**
 * AbstractFood contains the shared spawn and hide logic for all food types.
 * Subclasses provide the image path, score behaviour, and growth amount.
 */
public abstract class AbstractFood implements Food {

    protected Picture picture;
    private final Grid grid;
    private final String imagePath;

    /**
     * @param grid      The grid used to determine valid spawn positions
     * @param imagePath Path to the image file for this food type
     */
    protected AbstractFood(Grid grid, String imagePath) {
        this.grid = grid;
        this.imagePath = imagePath;
        spawn();
    }

    /** Spawns this food at a random position inside the grid. */
    protected void spawn() {
        Random random = new Random();

        int safeMargin = 50;
        int minX = 50;
        int minY = 50;
        int maxX = grid.getWidth() - safeMargin - minX;
        int maxY = grid.getHeight() - safeMargin - minY;

        int x = random.nextInt(maxX - minX + 1) + minX;
        int y = random.nextInt(maxY - minY + 1) + minY;

        if (picture != null) {
            hide();
        }

        picture = new Picture(x, y, imagePath);
        picture.draw();
    }

    @Override
    public Picture getPicture() {
        return picture;
    }

    @Override
    public void hide() {
        if (picture != null) {
            picture.delete();
            picture = null;
        }
    }
}
