import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by minh on 7/16/14.
 */
public class Main extends JFrame{
    private static LinkedHashMap<Integer, String> cardMap = new LinkedHashMap<Integer, String>();
    private static ArrayList<Integer> cardDeck;

    private JButton play = new JButton("Play game");

    public static void main(String[] args) {
        setCardMap();
        Main main = new Main();
    }

    public Main(){
        add(new JLabel(new ImageIcon("tienlen.jpg")), BorderLayout.CENTER);

        JPanel menu = new JPanel(new FlowLayout());
        menu.setBackground(Color.black);

        menu.add(play);
        add(menu, BorderLayout.SOUTH);
        setTitle("TIEN LEN - Client");
        setVisible(true);
        setSize(600, 400);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private static void setCardMap(){
        for (int i = 3; i <= 15; i++){
            int temp = i * 10;
            for (int j = 0; j < 4; j++){
                cardMap.put(temp+j, Integer.toString(temp+j));
            }
        }
    }

    // Display cards from server
    private static void getCards(ArrayList<Integer> cards){
        cardDeck.addAll(cards);
    }
}
