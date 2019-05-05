package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.Random;



/** Java Snake Game with AWT (OLD)
 *
 *
 *
 **/


/** size of each joint : 10 px, start with 3 joints, Game Over message displayed in the middle **/


public class Board extends JPanel implements ActionListener {


    // must implement the method actionPerformed(ActionEvent) in ActionListener


    private final int board_width = 300;
    private final int board_height = 300;
    private final int DOT_SIZE = 10;

    // movement delay in ms
    private final int delay = 140;


    //snake coordinates :
    // here we chose to memorize the snake coordinates using an x and y vector of length snake_length,
    // with x[0],y[0] the head
    private final int x[] = new int[board_width* board_height]; // be sure to have sufficient capacity lol
    private final int y[] = new int[board_width* board_height];
    private int snake_length;

    // dot coordinates
    private int dot_x;
    private int dot_y;


    //keys pressed, starting from the right, going left
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    // score
    int score = 0;

    // game checker
    private boolean inGame = true;

    // timer for continuous movement when no key is pressed (or same direction)
    private Timer timer;







    /** ----------------------------------- **/



    // constructor
    Board() {

        initBoard();
    }



    private void initBoard() {

        //add an event listener for arrow keys
        addKeyListener(new TAdapter());

        // this method allow the application to know where the user is focusing its actions.
        /*Let's say you have implemented a dialog with several text fields and you want the user to enter some text.
        When the user starts typing, one text field needs to have the focus of the application:
        it will be the field that receives the keyboard input.

        When you implement a focus traversal (a convenient way for the user to jump from one text field to the next,
        for example by using the tab button), the user can "jump" to the next text field.
        The application will try to gain the focus for the next field to prepare it to receive text.
        When the next field is not focusable, this request will be denied and the next field will be tested.
        */
        setFocusable(true);



        setBackground(Color.black);


        //set the size
        setPreferredSize(new Dimension(board_width, board_height));

        initGame();
    }



    /** set of methods **/


    //generate the starting position of the snake, generate a dot, paint components
    private void initGame() {

        snake_length = 3;

        //snake starting position : 50 50 going right
        for (int z = 0; z < snake_length; z++) {
            x[z] = 50 - z * DOT_SIZE; // careful to size of "real" pixels
            y[z] = 50;

            // 50 55 60
        }

        //generate the first dot
        generateDot();

        // set the timer to the delay, and between-EventListener delay, which is the same
        timer = new Timer(delay, this);

        timer.start();
    }







    // set up the colors, allow for custom textures to be displayed
    @Override
    public void paintComponent(Graphics g) {
        System.out.println("entered components");

        super.paintComponent(g);

        doDrawing(g);
    }




    private void doDrawing(Graphics g) {

        if (inGame) {


            // set the color of everything we draw
            g.setColor(Color.WHITE);
            // draw dot
            g.drawLine(dot_x, dot_y, dot_x + DOT_SIZE , dot_y + DOT_SIZE);

            for (int z = 0; z < snake_length; z++) {
                g.drawLine(x[z],y[z],x[z],y[z]);
            }



            // bind components implementations using native toolkit methods, for ui you can use createMenu, etc
            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }
    }




    // game over screen
    private void gameOver(Graphics g) {

        // create the message and the font
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        // include it in the graphics
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (board_width - metr.stringWidth(msg)) / 2, board_height / 2);
    }




    // check for dot reached and increase score count
    private void dotReached() {

        if ((x[0] == dot_x) && (y[0] == dot_y)) {

            score++;
            snake_length++;

            generateDot();
        }
    }




    // update snake coordinates: the painting is done by the repaint() method in the Action Listener
    // handle screen exiting
    private void moveSnake() {

        // be careful to move the snake FROM the tail TO the head, or 2 x values will be the same
        for (int z = snake_length - 1; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (left) x[0] -= DOT_SIZE;
        if (right) x[0] += DOT_SIZE;
        if (up) y[0] -= DOT_SIZE;
        if (down) y[0] += DOT_SIZE;


        if (x[0] == 0) x[0] = board_width - 1;
        if (x[0] == board_width - 1) x[0] = 0;
        if (y[0] == 0) y[0] = board_height - 1;
        if (y[0] == board_height - 1) y[0] = 0;

    }





    // check if you hit your tail, call a game over
    private void checkCollision() {

        // opposite direction to finish at 1
        for (int z = snake_length - 1; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }



    // generate new dot location, but be careful to avoid the snake !
    // the program must work for any board dimension
    // TODO : don't spawn dots on top of the snake
    private void generateDot() {
        dot_x = new Random().nextInt(board_width);
        dot_y = new Random().nextInt(board_height);

    }



    // what do we do when a key (action) has been pressed, or every timer tick
    // timer is bound to the ActionEvent e
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("entered action performed");


        if (inGame) {

            System.out.println("in game");

            dotReached();
            checkCollision();
            moveSnake();
        }

        // refreshes the view of the graphic component
        repaint();
    }





    // KeyAdapter is abstract, implements KeyListener
    // TODO : check if the class can be put in its own file since it needs to access the direction booleans, and the keyPressed method cannot be modified to take parameters
    private class TAdapter extends KeyAdapter {

        /* default methods:
            keyPressed
            keyReleased
            keyTyped
            */


        @Override
        public void keyPressed(KeyEvent e) {


            //retrieve the keyboard key code, in form of an int
            // visit https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html for more informations
            int key = e.getKeyCode();

            //changing direction, only turning 90°
            if ((key == KeyEvent.VK_LEFT) && (!right)) { // to turn 180°, 2 keys have to be pressed
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
}

