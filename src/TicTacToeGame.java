import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JPanel{

    private static boolean isXTurn = true;
    private static ImageIcon xMark;
    private static ImageIcon oMark;

    public TicTacToeGame(){
        manipulateIcons();
    }

    public static void main(String[] args){

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

    private static void addButtons(JFrame gWindow){

        TicButton button1 = new TicButton();

//        ImageIcon icon = new ImageIcon("resources/xMark.png");
//        Image img = icon.getImage();
//        Image newImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//        ImageIcon xMark = new ImageIcon(newImg);
//
//        button1.setIcon(xMark);

//        button1.setHorizontalTextPosition(JButton.CENTER);
//        button1.setVerticalTextPosition(JButton.CENTER);

//        button1.setIcon(xMark);

        TicButton button2 = new TicButton("2");
        TicButton button3 = new TicButton("3");
        TicButton button4 = new TicButton("4");
        TicButton button5 = new TicButton("5");
        TicButton button6 = new TicButton("6");
        TicButton button7 = new TicButton("7");
        TicButton button8 = new TicButton("8");
        TicButton button9 = new TicButton("9");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeMark(button1);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);

            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapBGTest(gWindow);
            }
        });

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

    private static void swapBGTest(JFrame gWindow){
        if (isXTurn){
            gWindow.getContentPane().setBackground(Color.red);
        } else {
            gWindow.getContentPane().setBackground(Color.blue);
        }
        isXTurn = !isXTurn;
    }

    private static void placeMark(TicButton button){
        //where button is the button that called this function
        button.setIcon(xMark);
    }

//    TODO: Doesn't work correctly. Doesn't seem like the constructor
//     correctly builds the icons? Not totally sure. Learn to use the
//     debugger please, thanks.

    //gets and resizes the icons, prepares them
    //for use. These will be stored in global variables
    //to limit the need to manipulate the icons every
    //time one is placed on the board of play.
    private static void manipulateIcons(){
        ImageIcon icon = new ImageIcon("resources/xMark.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        xMark = new ImageIcon(newImg);

//        icon = new ImageIcon("resources/oMark.png");
//        img = icon.getImage();
//        newImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//        oMark = new ImageIcon(newImg);
    }
}
