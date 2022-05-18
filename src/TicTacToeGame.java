import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JPanel{

    //keeps track of whose turn it is. THis will be swapped
    //every time someone places a mark. If isXTurn == true,
    //the mark will be placed as an X. If not, it's an O
    private static boolean isXTurn = true;

    //the images that will be placed into the TTT squares
    private static ImageIcon xMark;
    private static ImageIcon oMark;

    //holds all the buttons. I've done this primarily to
    //make it easier to check if the game has been won,
    //since I can just reference different indexes of the
    //array instead of trying to pull the button out of the
    //JFrame (if you can even do that)
    private static TicButton[] buttonArray = new TicButton[9];


    public static void main(String[] args){
        //assigns values to the xMark and oMark ImageIcons.
        manipulateIcons();

        //Make new JFrame for main window, set title
        JFrame gameWindow = new JFrame();
        gameWindow.setTitle("Tic Tac Toe!");

        //exits using the System exit method. Can also have it
        //hide on close instead of close.
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //changes the icon
        ImageIcon windowIcon = new ImageIcon("resources/windowIcon.gif");
        gameWindow.setIconImage(windowIcon.getImage());

        //sets window size and if the window can be resized
        gameWindow.setSize(500, 500);
        gameWindow.setResizable(false);

        //removes the 1-2 pixel gap on the right and left of the
        //play squares. Unsure what causes them, but this fixes them.
        gameWindow.getRootPane().setBorder(new EmptyBorder(0, -1, 0, -1));

        //-------Testing gridlayout-------
        gameWindow.setLayout(new GridLayout(3,3, 14, 14));

        //set BG color, changes the color between buttons
        gameWindow.getContentPane().setBackground(Color.BLACK);

        addButtons(gameWindow);

        //displays the window
        gameWindow.setVisible(true);

        //centers on the screen because no other component is
        //specified. Could also use setLocationRelativeTo(compName)
        //which would "..the windows is located in such a way that
        //the center of the window coincides with the center of the
        //[parameter] component". Neat!
//        gameWindow.setLocationRelativeTo(null);

    }


    //TODO: add buttons into global array buttonArray[],
    // add game logic using that array
    private static void addButtons(JFrame gWindow){


        //private static TicButton[] buttonArray = new TicButton[9];

        TicButton button1 = new TicButton();
        TicButton button2 = new TicButton();
        TicButton button3 = new TicButton();
        TicButton button4 = new TicButton();
        TicButton button5 = new TicButton();
        TicButton button6 = new TicButton();
        TicButton button7 = new TicButton();
        TicButton button8 = new TicButton();
        TicButton button9 = new TicButton();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button1);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button2);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button3);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button4);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button5);
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button6);
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button7);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button8);
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button9);
            }
        });

//        //add buttons to array
//        buttonArray[0] = button1;

        //add buttons to JFrame
        gWindow.add(button1);
        gWindow.add(button2);
        gWindow.add(button3);
        gWindow.add(button4);
        gWindow.add(button5);
        gWindow.add(button6);
        gWindow.add(button7);
        gWindow.add(button8);
        gWindow.add(button9);
    }

//    TESTING CODE, NO LONGER NEEDED
//    private static void swapBGTest   (JFrame gWindow){
//        if (isXTurn){
//            gWindow.getContentPane().setBackground(Color.red);
//        } else {
//            gWindow.getContentPane().setBackground(Color.blue);
//        }
//        isXTurn = !isXTurn;
//    }

    //places the mark depending on if it is currently X's turn
    //or O's turn, then it flips the global bool isXTurn to
    //keep track of whose turn it is
    private static void placeMark(TicButton button){
        if (isXTurn) {
            button.setIcon(xMark);
        } else {
            button.setIcon(oMark);
        }
        isXTurn = !isXTurn;
    }

    //gets and resizes the icons, prepares them
    //for use. These will be stored in global variables
    //to limit the need to manipulate the icons every
    //time one is placed on the board of play
    private static void manipulateIcons(){
        ImageIcon icon = new ImageIcon("resources/xMark.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        xMark = new ImageIcon(newImg);

        icon = new ImageIcon("resources/oMark.png");
        img = icon.getImage();
        newImg = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        oMark = new ImageIcon(newImg);
    }
}
