import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by minh on 7/25/14.
 */
public class PlayerCardPanel extends JPanel implements ActionListener, Observer {

    private JButton play;
    private JButton pass;
//    private CardDeck selectedCards = new CardDeck();
    private JLabel[] cards;
    private Boolean[] isSelected = new Boolean[13];
    private Move move;

    public PlayerCardPanel(){
        paintGUI();
        Player.getInstance().addObserver(this);
    }

    public void paintGUI(){
        play = new JButton("Submit");
        pass = new JButton("Pass");
        cards = new JLabel[13];

        setLayout(new FlowLayout());
        for(int i = 0; i < Player.getInstance().getCardDeck().getCards().size(); i++){
            cards[i] = new JLabel(new ImageIcon("img/deck/"+Player.getInstance().getCardDeck().getCards().get(i)+".png"));
            final int index = i;
            final int value = Player.getInstance().getCardDeck().getCards().get(i);
            isSelected[i] = false;
            cards[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent ae) {

                    if(isSelected[index] == true){
//                        selectedCards.getCards().remove((Object)value); // remove object
                        Player.getInstance().getSelectedCards().getCards().remove((Object)value);
                        cards[index].setBorder(null);
                        isSelected[index] = false;
                    }else{
                        Border border = LineBorder.createBlackLineBorder();
                        cards[index].setBorder(border);
//                        selectedCards.getCards().add(Player.getInstance().getCardDeck().getCards().get(index));
                        Player.getInstance().getSelectedCards().getCards().add(Player.getInstance().getCardDeck().getCards().get(index));
                        System.out.println("Card to be added in is: " + Player.getInstance().getCardDeck().getCards().get(index));
                        isSelected[index] = true;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            add(cards[i]);
        }
        play.addActionListener(this);
        add(play);
        add(pass);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b == play){
            System.out.println("-----SUBMIT BUTTON PRESSED-----");
            System.out.print("Selected cards: ");
            for (int i = 0; i < Player.getInstance().getSelectedCards().getCards().size(); i++) {
                System.out.print(Player.getInstance().getSelectedCards().getCards().get(i) + " ");
            }
            System.out.println();
            // send selected cards to server
            Player.getInstance().getCommunicator().write(new Move("Minh", Player.getInstance().getSelectedCards()));
            System.out.println("Wrote to server");
            // update card deck of the player based on played cards
//            Player.getInstance().updateCardDeck(selectedCards);

//            move = new Move(Client.getInstance().getName(), selectedCards);
//            Client.getInstance().getCommunicator().write(move); // write player's move to server to check
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof CardDeck){
            removeAll();
            paintGUI();
            validate();
            repaint();
            System.out.println("Update ran!!!!");

        }
    }
}
