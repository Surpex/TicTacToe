import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameOverGlass extends JComponent {

//    JLabel sizeLabel;
    JButton resetButton;

    public GameOverGlass(JButton button){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.resetButton = button;
        addLabel();
        this.add(resetButton);
    }

    public void paintComponent(Graphics g){
//        Rectangle rect = sizeLabel.getBounds();
        g.setColor(new Color(210, 232, 227, 200));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void addLabel(){
        JLabel label = new JLabel("Game Over");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(100, 0, 40, 0));
        label.setFont(new Font("Tahoma", Font.BOLD, 77));
        label.setForeground(new Color(44, 53, 50, 255));
        add(label);
    }

}
