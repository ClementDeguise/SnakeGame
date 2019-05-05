
package Main;


import javax.swing.*;
import java.awt.*;



/** need to instantiate the board class with the Swing JFrame to create UIs **/
public class snakeGame extends JFrame {


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


    }


    // main
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            snakeGame sn = new snakeGame();
            sn.initUI();
        });

    }

}