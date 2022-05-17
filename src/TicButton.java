import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TicButton extends JButton {
    //color for when the button is still being
    //pressed, before it's been released
    private Color pressedBGColor = new Color(224, 196, 157);
    private Color hoverBGColor = new Color(224, 196, 157);
    private Color normalBGColor = new Color(248, 220, 170);

    public TicButton(){
        this(null);
    }

    public TicButton(String text){
        super(text);
        super.setContentAreaFilled(false);
        setBackground(normalBGColor);
        setBorderPainted(false);
        setFocusable(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        if (getModel().isPressed()) {
            g.setColor(pressedBGColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBGColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0,0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b){
    }

    public Color getHoverBGColor(){
        return hoverBGColor;
    }

    public void setHoverBGColor(Color hoverBGColor){
        this.hoverBGColor = hoverBGColor;
    }

    public Color getPressedBGColor(){
        return pressedBGColor;
    }

    public void setPressedBGColor(Color pressedBGColor){
        this.pressedBGColor = pressedBGColor;
    }

}
