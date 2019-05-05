package Main;


import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


/** need to instantiate the board class with the Swing JFrame to create UIs **/
public class snakeGame extends JFrame {



    // constructor
   /* private snakeGame() {
        initUI();
    }*/

    private void initUI() {
        JFrame ex = new JFrame();

        ex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ex.getContentPane().add(new Board(), BorderLayout.CENTER);
        // TODO : dynamic resizing of game window
        ex.setResizable(false);
        ex.setTitle("Snake Game");
        //ex.setSize(300,300);
        //setLocationRelativeTo(null);


        ex.pack();
        ex.setVisible(true);



        //ex.add(new Board());
    }


    // main
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            snakeGame sn = new snakeGame();
            sn.initUI();
        });

    }

}


/* other version

 private void initGui() {

        JFrame ex = new JFrame();
        // TODO : dynamic resizing of game window
        ex.setResizable(false);
        ex.getContentPane().add(board, BorderLayout.CENTER);
        ex.setTitle("Snake Game");
        //setLocationRelativeTo(null);
        ex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        ex.pack();
        ex.setVisible(true);

    }


    // main
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            snakeGame sn = new snakeGame();
            sn.initGui();
        });

    }
 */








