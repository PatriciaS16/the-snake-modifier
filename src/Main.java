import com.codeforall.online.thesnakemodifier.Game;
import com.codeforall.online.thesnakemodifier.MyKeyboard;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Main {
    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle(10,10,800,700);
        rectangle.draw();

        Game game = new Game();

        MyKeyboard myKeyboard = new MyKeyboard();
        myKeyboard.setSnake(game.getSnake());
        myKeyboard.init();

    }
}