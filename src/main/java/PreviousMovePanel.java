import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by minh on 7/27/14.
 */
public class PreviousMovePanel extends JPanel {

    private static PreviousMovePanel instance = null;
    private ArrayList<JLabel> cardLabels;

    public static PreviousMovePanel getInstance(){
        if (instance == null){
            instance = new PreviousMovePanel();
        }
        return instance;
    }

    public PreviousMovePanel(){
        cardLabels = new ArrayList<JLabel>();
    }

    public void displayCards(CardDeck cards){
        setLayout(new FlowLayout());

    }

}
