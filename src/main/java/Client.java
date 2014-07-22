import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by minh on 7/16/14.
 */
public class Client extends JFrame implements ActionListener {

    private static LinkedHashMap<Integer, String> cardMap = new LinkedHashMap<Integer, String>();
    private Communicator communicator;

    private JButton play = new JButton("Play game");
    private JTextField name = new JTextField();

    public static void main(String[] args) {
        setCardMap();
        Client main = new Client();
    }

    public Client(){
        JPanel banner = new JPanel(new FlowLayout());
        banner.add(new JLabel(new ImageIcon("img/tienlen.jpg")));
        JPanel menu = new JPanel(new FlowLayout());
        menu.setBackground(Color.black);

        name.setColumns(20);
        play.addActionListener(this);
        menu.add(name);
        menu.add(play);
        add(menu, BorderLayout.SOUTH);
        add(banner, BorderLayout.CENTER);

        setTitle("TIEN LEN - Client");
        setVisible(true);
        setSize(600, 400);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void setCardMap(){
        for (int i = 3; i <= 15; i++){
            int temp = i * 10;
            for (int j = 0; j < 4; j++){
                cardMap.put(temp+j, Integer.toString(temp+j));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton b = (JButton)ae.getSource();
        if(b == play){
            try {
                Socket client = new Socket("localhost",18888);
                ObjectOutputStream oos = new ObjectOutputStream(
                        client.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(
                        client.getInputStream());
                communicator = new Communicator(client, ois, oos);
                CardDeck cardDeck = (CardDeck)communicator.read();
                for (int i = 0; i<cardDeck.getCards().size(); i++){
                    System.out.println(cardDeck.getCards().get(i));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
