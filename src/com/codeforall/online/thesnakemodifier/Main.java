package com.codeforall.online.thesnakemodifier;

import com.codeforall.online.thesnakemodifier.input.MyMouse;
import com.codeforall.online.thesnakemodifier.ui.Menu;

/**
 * The Main class is the entry point of the game.
 */
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        MyMouse myMouse = new MyMouse();
        myMouse.setMenu(menu);
        myMouse.init();
    }
}
