package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;


/** Java Snake Game with AWT (OLD)
 *
 *
 *
 */

/** size of each joint : 10 px, start with 3 joints, Game Over message displayed in the middle **/


public class Board extends JPanel implements ActionListener {

    // must implement the method actionPerformed(ActionEvent) in ActionListener


    private final int board_width = 300;
    private final int board_height = 300;

    private final int DOT_SIZE = 5;




    //keys pressed, starting from the right, going left
    private boolean Up = false;
    private boolean Down = false;
    private boolean Right = false;
    private boolean Left = true;


    //score
    private int score = 0;

    //dot coordinates
    private int dot_x;
    private int dot_y;

    //snake coordinates :
    // here we chose to memorize the snake coordinates using an x and y vector of length snake_length,
    // with x[0],y[0] the head
    private int snake_length = 3;
    private final int[] x = new int[board_height*board_width]; // be sure to have sufficient capacity lol
    private final int[] y = new int[board_height*board_width];


    //game checker
    private boolean inGame = false;


    //random generator
    Random randomGenerator = new Random();


    /** ----------------------------------- **/

    // constructor
    public Board() {
        initBoard();
    }

    private void initBoard() {

        //add an event listener for arrow keys
        addKeyListener(new TAdapter());

        // this method allow the application to know where the user is focusing its actions.
        /*
        Let's say you have implemented a dialog with several text fields and you want the user to enter some text.
        When the user starts typing, one text field needs to have the focus of the application:
        it will be the field that receives the keyboard input.

        When you implement a focus traversal (a convenient way for the user to jump from one text field to the next,
        for example by using the tab button), the user can "jump" to the next text field.
        The application will try to gain the focus for the next field to prepare it to receive text.
        When the next field is not focusable, this request will be denied and the next field will be tested.
         */
        setFocusable(true);

        //set the size
        setPreferredSize(new Dimension(board_width,board_height));

        initGame();

    }


    // what do we do when a key (action) has been pressed
    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            dotReached();;
            checkCollision();
            moveSnake();
        }




    }







    /** set of methods **/

    //generate the starting position of the snake, generate a dot, paint components
    private void initGame() {}


    // generate new dot location, but be careful to avoid the snake !
    // the program must work for any board dimension
    // TODO : don't spawn dots on top of the snake
    private void generateDot() {
        dot_x = randomGenerator.nextInt(board_width);
        dot_y = randomGenerator.nextInt(board_height);
    }


    // set up the colors
    private void drawComponents(Graphics g) {}


    // game over screen
    private void gameOver() {}

    // increase score count
    private void dotReached() {}


    // move the snake by painting the pixels according to the direction
    private void moveSnake() {}


    // check if you hit your tail
    private void checkCollision() {}




















}
