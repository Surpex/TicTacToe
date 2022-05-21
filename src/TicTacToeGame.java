import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

//    public static JFrame gameWindow = new JFrame();
    public static GameWindowFrame gameWindow = new GameWindowFrame();

    //holds all the buttons. I
    private static TicButton[] buttonArray = new TicButton[9];

    //parallel array that holds a representation of the board.
    //X represents the cross, O represents the circle. This
    //will allow for easier checking of win states. boardArray
    //is initialized with the character 'u'.
    private static char[] boardArray = new char[9];

    public static void main(String[] args){
        driver();
    }

    //shows the GlassPane with a reset button
    private static void displayGameOver(){
        gameWindow.getGlassPane().setVisible(true);
    }

    //creates the GlassPane that will be displayed upon
    //completing/drawing a game. It will intercept and
    //consumes mouse input to stop the game from being
    //played in the background
    private static void buildGlassPane(){
        GameOverGlass glass;
        glass = new GameOverGlass(buildResetButton());
        gameWindow.setGlassPane(glass);
        glass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.consume();
            }
        });
    }

    //breaks the styling for the reset button out to clean the
    //calling method up. Also handles the button click that will
    //reset the game. This could also be done with a new class that
    //extend JButton or TicButton, but it seems strange to create a
    //class for a single button.
    private static TicButton buildResetButton(){
        TicButton resetGameButton = new TicButton("Play Again");
        resetGameButton.setPressedBGColor(new Color(44, 53, 50, 255));
        resetGameButton.setHoverBGColor(new Color(15, 100, 102, 255));
        resetGameButton.setBackground(new Color(15, 100, 102, 255));
        resetGameButton.setForeground(new Color(210, 232, 227, 255));
        resetGameButton.setMargin(new Insets(15, 20, 20, 20));
        resetGameButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
        resetGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        resetGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.getGlassPane().setVisible(false);
                resetGame();
            }
        });

        return resetGameButton;
    }

    //creates buttons, adds them to global array buttonArray[],
    // attaches event listeners to them, adds them to gWindow
    private static void addButtons(GameWindowFrame gWindow){
        for (int i = 0; i < 9; i++){
            buttonArray[i] = new TicButton();
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
                displayGameOver();
            } else if (!checkWin()){
              if (checkDraw()){
                  //do draw state
                  System.out.println("The game was a draw");
                  displayGameOver();
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
    private static void driver(){
        //assigns values to the xMark and oMark ImageIcons.
        manipulateIcons();

        //initializes parallel array
        initArray();

        //shows the board, populated with buttons
        displayBoard();

        //makes the GlassPane, which is stored in global var glass.
        //This allows us to create the GlassPane one time and use it
        //enable/disable it at will.
        buildGlassPane();
    }

    //adds buttons to the game board, then displays it
    private static void displayBoard(){
        addButtons(gameWindow);
        gameWindow.setVisible(true);
    }

    //changes the icon sizing so we don't have to do it
    //every time a piece is placed
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

    //initializes the parallel array to all u's
    //(u meaning "unchanged", no mark placed)
    private static void initArray(){
        for (int i = 0; i < 9; i++){
            boardArray[i] = 'u';
        }
    }

    //runs the necessary methods to reset the game,
    //resets the turn counter to start with X turn
    //on the next game
    private static void resetGame(){
        initArray();
        removeMarks();
        isXTurn = true;
    }

    //removes the marks from the board
    private static void removeMarks(){
        for (int i = 0; i < buttonArray.length; i++) {
            buttonArray[i].setIcon(null);
        }
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