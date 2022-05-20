import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
TODO: clear the board when the game has been won or tied.
 Probably do this by clearing the actual icons from the
 buttons, re-initializing the parallel array, resetting
 isXTurn. Tried removing everything from the JFrame with
 frame.removeAll() and rebuilding the frame, but this breaks
 the ability to add icons to the board. Probably easier
 and more effective to just clear icons etc.
*/

public class TicTacToeGame extends JPanel{

    //keeps track of whose turn it is. THis will be swapped
    //every time someone places a mark. If isXTurn == true,
    //the mark will be placed as an X. If not, it's an O
    private static boolean isXTurn = true;

    //the images that will be placed into the TTT squares
    private static ImageIcon xMark;
    private static  ImageIcon oMark;

    private static final char xChar = 'x';
    private static final char oChar = 'o';

    public static JFrame gameWindow = new JFrame();

    //holds all the buttons. I
    private static TicButton[] buttonArray = new TicButton[9];

    //parallel array that holds a representation of the board.
    //X represents the cross, O represents the circle. This
    //will allow for easier checking of win states. boardArray
    //is initialized with the character 'u'.
    private static char[] boardArray = new char[9];


    public static void main(String[] args){
        driver();

        buildBoard();
    }

    //creates buttons, adds them to global array buttonArray[],
    // attaches event listeners to them, adds them to gWindow
    private static void addButtons(JFrame gWindow){
        for (int i = 0; i < 9; i++){
            buttonArray[i] = new TicButton();
            //for debugging
//            buttonArray[i] = new TicButton(Integer.toString(i));
            int finalI = i;
            buttonArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    placeMark(buttonArray[finalI], finalI);
                }
            });
            gWindow.add(buttonArray[i]);
        }
    }

    //places the mark depending on if it is currently X's turn
    //or O's turn, then it flips the global bool isXTurn to
    //keep track of whose turn it is
    private static void placeMark(TicButton button, int arrPos){
        if (boardArray[arrPos] == 'u'){
            if (isXTurn) {
                makeMove(button, xMark, arrPos);
            } else {
                makeMove(button, oMark, arrPos);
            }

            //check if the game has been won
            if(!checkDraw() && !checkWin()){
                isXTurn = !isXTurn;
            } else if(checkWin()){
                //do win state
                System.out.println("Game has been won");
            } else if (!checkWin()){
              if (checkDraw()){
                  //do draw state
                  System.out.println("The game was a draw");
              }
            }
        }
    }

    private static void makeMove(TicButton button, ImageIcon pMark, int arrPos){
        button.setIcon(pMark);
        if (isXTurn){
            boardArray[arrPos] = xChar;
        } else {
            boardArray[arrPos] = oChar;
        }
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

    private static void initArray(){
        for (int i = 0; i < 9; i++){
            boardArray[i] = 'u';
        }
    }

    private static void driver(){
        //assigns values to the xMark and oMark ImageIcons.
        manipulateIcons();

        //initializes parallel array
        initArray();
    }

    private static void buildBoard(){
        //Make new JFrame for main window, set title
//        JFrame gameWindow = new JFrame();
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
        //play squares. Unsure what causes them, but this fixes them
        gameWindow.getRootPane().setBorder(new EmptyBorder(0, -1, 0, -1));

        //-------Testing gridlayout-------
        gameWindow.setLayout(new GridLayout(3,3, 14, 14));

        //set BG color, changes the color between buttons
        gameWindow.getContentPane().setBackground(Color.BLACK);

        addButtons(gameWindow);

        //displays the window
        gameWindow.setVisible(true);
    }

    private static void clearBoard(){
        gameWindow.getContentPane().removeAll();
        gameWindow.repaint();
    }

    private static boolean checkWin(){
        System.out.println("From checkWin()");
        boolean hasWon = false;
        if (checkVerticals() || checkHorizontals() || checkDiagonals()){
            hasWon = true;
        }
        return hasWon;
    }

    private static boolean checkDraw(){
        for (int i = 0; i < 9; i++){
            if (boardArray[i] == 'u'){
                return false;
            }
        }
        return true;
    }

    //How the array maps to the board:
    //[0,1,2]
    //[3,4,5]
    //[6,7,8]
    private static boolean checkVerticals(){

        for (int i = 0; i < 3; i++){
            if (boardArray[i] != 'u'){
                if ((boardArray[i] == boardArray[i + 3]) && (boardArray[i + 3] == boardArray[i + 6])){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkHorizontals(){
        for (int i = 0; i < 7; i += 3){
            if (boardArray[i] != 'u'){
                if((boardArray[i] == boardArray[i + 1]) && (boardArray[i + 1] == boardArray[i + 2])){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonals(){
        if (boardArray[0] != 'u' && boardArray[2] != 'u'){
            if ((boardArray[0] == boardArray[4]) && (boardArray[4] == boardArray[8])){
                return true;
            }
            else if ((boardArray[2] == boardArray[4]) && (boardArray[4] == boardArray[8])){
                return true;
            }
        }
        return false;
    }
}