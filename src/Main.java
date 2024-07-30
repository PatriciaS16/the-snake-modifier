import com.codeforall.online.thesnakemodifier.Menu;
import com.codeforall.online.thesnakemodifier.MyMouse;

/**
 * The Main class is responsible for the entry point of the game
 */
public class Main {
    public static void main(String[] args) {

        // Create an instance of menu
        Menu menu = new Menu();
        // Create an instance of my mouse
        MyMouse myMouse = new MyMouse();
        // Set the menu instance to my mouse
        myMouse.setMenu(menu);
        // Initialize the mouse event handling
        myMouse.init();

    }
}
