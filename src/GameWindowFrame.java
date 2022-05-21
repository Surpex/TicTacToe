import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameWindowFrame extends JFrame {
    public GameWindowFrame(){
        setTitle("Tic Tac Toe!");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(500, 500);
        setResizable(false);

        getRootPane().setBorder(new EmptyBorder(0, -1, 0, -1));

        setLayout(new GridLayout(3, 3, 14, 14));

        getContentPane().setBackground(new Color(44, 53, 50, 255));

        setIconImage(new ImageIcon("resources/windowIcon.gif").getImage());
    }
}
