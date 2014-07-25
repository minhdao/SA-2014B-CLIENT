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
    private final ArrayList<Integer> selectedCards = new ArrayList<Integer>();
    private final ArrayList<Integer> positionCards = new ArrayList<Integer>();
    private final JLabel[] cards = new JLabel[13];

    public MainPlayer(){
        setLayout(new FlowLayout());
        for(int i = 0; i < cards.length; i++){
            cards[i] = new JLabel(new ImageIcon("img/deck/"+Client.getInstance().getCardDeck().getCards().get(i)+".png"));
            final int[] count = {0};
            final int[] index = {i};
            cards[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent ae) {

                    if(count[0] == 1){
                        selectedCards.remove(Client.getInstance().getCardDeck().getCards().get(index[0]));
                        positionCards.remove(Client.getInstance().getCardDeck().getCards().indexOf(Client.getInstance().getCardDeck().getCards().get(index[0])));
                        cards[index[0]].setBorder(null);
                        count[0] = 0;
                    }else{
                        Border border = LineBorder.createBlackLineBorder();
                        cards[index[0]].setBorder(border);
                        selectedCards.add(Client.getInstance().getCardDeck().getCards().get(index[0]));
                        positionCards.add(Client.getInstance().getCardDeck().getCards().indexOf(Client.getInstance().getCardDeck().getCards().get(index[0])));
                        count[0]++;
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
            for (int i = 0; i < selectedCards.size(); i++) {
                System.out.println(selectedCards.get(i));

            }
            System.out.println("----POS------");
            for (int i = 0; i < positionCards.size(); i++) {
                System.out.println(positionCards.get(i));

            }
            System.out.println("----------");
            for (int i = 0; i < positionCards.size(); i++) {
                remove(cards[positionCards.get(i)]);

            }
            revalidate();
            repaint();
        }
    }
}
