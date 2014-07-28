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
    private ArrayList<JLabel> cardLabels;

    public static PreviousMovePanel getInstance(){
        if (instance == null){
            instance = new PreviousMovePanel();
        }
        return instance;
    }

    public PreviousMovePanel(){
        displayCards(Player.getInstance().getPreviousMove());
        Player.getInstance().addObserver(this); // this panel now observe Player
    }

    public void displayCards(Move previousMove){
        if (previousMove != null) {
            cardLabels = new ArrayList<JLabel>();
            setLayout(new FlowLayout());
            for (int i = 0; i < previousMove.getCards().getCards().size();i++){
                System.out.print("cards to be added to cardLabels: " + previousMove.getCards().getCards().get(i));
                cardLabels.add(new JLabel(new ImageIcon("img/deck/"+previousMove.getCards().getCards().get(i)+".png")));
                add(cardLabels.get(i));
            }
            System.out.println();
            System.out.println("displayCards is running!!!!");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Move){
            Move previousMove =  (Move)arg;
            System.out.println("-----Inside previous move panel-----");
            // code to test
            for (int i =0; i< previousMove.getCards().getCards().size(); i++){
                System.out.print("Cards in previous move: ");
                System.out.print(previousMove.getCards().getCards().get(i) + " ");
            }
            System.out.println();
            removeAll();
            displayCards(previousMove);
            validate();
            repaint();
            System.out.println("--------Update previous move panel--------");
        }
    }
}
