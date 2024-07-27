import com.codeforall.online.thesnakemodifier.Game;
import com.codeforall.online.thesnakemodifier.Grid;
import com.codeforall.online.thesnakemodifier.MyKeyboard;
import com.codeforall.online.thesnakemodifier.Snake;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Main {
    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle(10,10,800,700);
        rectangle.draw();


        Grid background = new Grid(new Picture(300,10, Game.PREFIX + "LighterBackground.png"));

        Snake snake = new Snake(new Picture(400,350, Game.PREFIX + "SnakeHead.png"));

        MyKeyboard myKeyboard = new MyKeyboard();
        myKeyboard.setSnake(snake);
        myKeyboard.init();
    }
}