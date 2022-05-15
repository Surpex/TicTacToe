import javax.swing.*;

public class TicTacToeGame extends JPanel{

    public static void main(String[] args){
        JFrame gameWindow = new JFrame("Tic Tac Toe");


        ImageIcon windowIcon = new ImageIcon("resources/windowIcon.gif");
        gameWindow.setIconImage(windowIcon.getImage());

        //exits using the System exit method. Can also have it
        //hide on close instead of close.
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //retrieves the contentPane and adds TicTacToeGame to
        //what is retrieved? Unsure on the specifics.
        gameWindow.getContentPane().add(new TicTacToeGame());

        //x and y specify the coords of the component (on screen?),
        //width and height specify the uhh.. the width and height
        gameWindow.setBounds(500, 500, 500, 500);

        //displays the window
        gameWindow.setVisible(true);

        //centers on the screen because no other component is
        //specified. Could also use setLocationRelativeTo(compName)
        //which would "..the windows is located in such a way that
        //the center of the window coincides with the center of the
        //[parameter] component". Neat!
        gameWindow.setLocationRelativeTo(null);

    }
}
