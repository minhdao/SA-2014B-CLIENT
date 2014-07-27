import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by minh on 7/27/14.
 */
public class PreviousMovePanel extends JPanel implements Observer {

    private static PreviousMovePanel instance = null;
    private ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();

    public static PreviousMovePanel getInstance(){
        if (instance == null){
            instance = new PreviousMovePanel();
        }
        return instance;
    }

    public PreviousMovePanel(){
        displayCards(Player.getInstance().getPreviousMove());
    }

    public void displayCards(Move previousMove){
        if (previousMove != null) {
            setLayout(new FlowLayout());
            for (int i = 0; i < previousMove.getCards().getCards().size();i++){
                cardLabels.add(new JLabel(new ImageIcon("img/deck/"+previousMove.getCards().getCards().get(i)+".png")));
                add(cardLabels.get(i));
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
