package com.codeforall.online.thesnakemodifier;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {

    private Picture background;

    public Grid(Picture background) {
        this.background = background;
        background.draw();
    }
}
