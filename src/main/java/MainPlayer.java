/**
 * Created by minh on 7/24/14.
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainPlayer extends JPanel implements ActionListener {
    private JButton play = new JButton("Submit");
    private JButton pass = new JButton("Pass");
    private final CardDeck selectedCards = new CardDeck();
    private final ArrayList<Integer> positionCards = new ArrayList<Integer>();
    private final JLabel[] cards = new JLabel[13];
    private Boolean[] isSelected = new Boolean[13];
    private Move move;

    public MainPlayer(){
        setLayout(new FlowLayout());
        for(int i = 0; i < cards.length; i++){
            cards[i] = new JLabel(new ImageIcon("img/deck/"+Client.getInstance().getCardDeck().getCards().get(i)+".png"));
            final int index = i;
            final int value = Client.getInstance().getCardDeck().getCards().get(i);
            isSelected[i] = false;
            cards[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent ae) {

                    if(isSelected[index] == true){
                        selectedCards.getCards().remove((Object)value); // remove object
                        cards[index].setBorder(null);
                        isSelected[index] = false;
                    }else{
                        Border border = LineBorder.createBlackLineBorder();
                        cards[index].setBorder(border);
                        selectedCards.getCards().add(Client.getInstance().getCardDeck().getCards().get(index));
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
    public void actionPerformed(ActionEvent ae) {
        JButton b = (JButton)ae.getSource();
        if(b == play){
            System.out.println("-----SELECT-----");
            for (int i = 0; i < selectedCards.getCards().size(); i++) {
                System.out.println(selectedCards.getCards().get(i));
            }

            move = new Move(Client.getInstance().getName(), selectedCards);
            Client.getInstance().getCommunicator().write(move); // write player's move to server to check
            System.out.println("----POS------");
            for (int i = 0; i < positionCards.size(); i++) {
                System.out.println(positionCards.get(i));

            }
//            System.out.println("----------");
//            for (int i = 0; i < positionCards.size(); i++) {
//                remove(cards[positionCards.get(i)]);
//
//            }
            revalidate();
            repaint();
        }
    }
}
