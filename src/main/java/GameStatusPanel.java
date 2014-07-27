import com.sun.deploy.panel.JSmartTextArea;

import javax.swing.*;

/**
 * Created by minh on 7/27/14.
 */
public class GameStatusPanel extends JPanel {

    private static GameStatusPanel instance = null;
    private JLabel textArea;

    public static GameStatusPanel getInstance(){
        if (instance == null){
            instance = new GameStatusPanel();
        }
        return instance;
    }

    public GameStatusPanel(){
        textArea = new JLabel();
        add(textArea);
    }

    public JLabel getTextArea() {
        return textArea;
    }

    public void setTextArea(JLabel textArea) {
        this.textArea = textArea;
    }
}
