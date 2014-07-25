import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
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
public class Client extends JFrame implements ActionListener, Runnable {

    private static LinkedHashMap<Integer, String> cardMap = new LinkedHashMap<Integer, String>();
    private Communicator communicator;
    private CardDeck cardDeck = new CardDeck();

    private JButton play = new JButton("Play game");
    private JButton test = new JButton("Test"); // test button to test communication
    private JTextField name = new JTextField();

    private static Client instance = null;

    public static Client getInstance(){
        if (instance == null){
            instance = new Client();
        }
        return instance;
    }

    public static void main(String[] args) {
        setCardMap();
        getInstance();
    }

    public Client(){
        JPanel banner = new JPanel(new FlowLayout());
        banner.add(new JLabel(new ImageIcon("img/tienlen.jpg")));
        JPanel menu = new JPanel(new FlowLayout());
        menu.setBackground(Color.black);

        name.setColumns(20);
        play.addActionListener(this);
        test.addActionListener(this);
        menu.add(name);
        menu.add(play);
        menu.add(test);
        add(menu, BorderLayout.SOUTH);
        add(banner, BorderLayout.CENTER);

        setTitle("TIEN LEN - Client");
        setVisible(true);
        setSize(600, 400);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up connection
        // should only set up connection 1 time / client
        Socket client = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            client = new Socket("localhost",18888);
            oos = new ObjectOutputStream(
                    client.getOutputStream());
            ois = new ObjectInputStream(
                    client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        communicator = new Communicator(client, ois, oos);
        new Thread(this).start(); // start the thread/listen to server
    }

    private static void setCardMap(){
        for (int i = 3; i <= 15; i++){
            int temp = i * 10;
            for (int j = 0; j < 4; j++){
                cardMap.put(temp+j, Integer.toString(temp+j));
            }
        }
    }

    public CardDeck getCardDeck(){
        return this.cardDeck;
    }

    public Communicator getCommunicator() {
        return communicator;
    }

    // action is used to write something to the server
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton b = (JButton)ae.getSource();
        if(b == play){
            System.out.println("Play button pressed!!!!!!");
            communicator.write(name.getText());
            this.setVisible(false);// dispose the UI
            System.out.println("After card table is created");
        } else if (b == test){
            Test message = new Test(name.getText());
            communicator.write(message);
        }
    }

    // keep the communication to server alive
    @Override
    public void run() {
        Object message = communicator.read();
        while (message!=null){
            if (message instanceof Test){
                Test test = (Test)message;
                System.out.println(test.getMessage());
            }
            if (message instanceof CardDeck){
                cardDeck = (CardDeck) message;
                for(int i = 0; i < cardDeck.getCards().size();i++){
                    System.out.println(cardDeck.getCards().get(i));
                }
                CardTable ct = new CardTable();
                ct.setVisible(true);
            }
            message = communicator.read();
        }
    }
}