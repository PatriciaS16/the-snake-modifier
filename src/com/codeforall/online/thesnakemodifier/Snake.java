package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Snake {

    private Picture picture;

    public Snake(Picture picture) {
        this.picture = picture;
        picture.draw();
    }

    public void moveLeft(){
        picture.translate(-10,0);
    }

    public void moveRight() {
        picture.translate(10,0);
    }

    public void moveUp() {
        picture.translate(0,-10);
    }

    public void moveDown() {
        picture.translate(0,10);
    }

    public void grow() {
    }

    public void reset() {
    }

}
