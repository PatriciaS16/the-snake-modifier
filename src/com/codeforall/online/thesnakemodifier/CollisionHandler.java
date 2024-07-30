package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.LinkedList;

public class CollisionHandler  {

    /**
     * Checks if the snake has collided with itself.
     *
     * @param headX The X coordinate of the snake's head.
     * @param headY The Y coordinate of the snake's head.
     * @param body The list of all snake segments (excluding the head).
     * @return True if the snake has collided with itself; otherwise, false.
     */


    public static boolean checkSelfCollision(int headX, int headY, LinkedList<Picture> body) {
        for (int i = 1; i < body.size(); i++) { // Start from index 1 to skip the head
            Picture part = body.get(i);
            if (part.getX() == headX || part.getY() == headY) {
                return true; // Collision detected
            }
        }
        return false; // No collision detected
    }
}


