import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Created by minh on 7/16/14.
 */
public class Main extends JFrame implements ActionListener {
    private static LinkedHashMap<Integer, String> cardMap = new LinkedHashMap<Integer, String>();
    private static ArrayList<Integer> cardDeck;

    private JButton play = new JButton("Play game");
    private JTextField name = new JTextField();

    public static void main(String[] args) {
        setCardMap();
        Main main = new Main();
    }

    public Main(){
        JPanel banner = new JPanel(new FlowLayout());
        banner.add(new JLabel(new ImageIcon("img/tienlen.jpg")));
        //banner.setBackground(Color.BLACK);
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

    // Display cards from server
    private static void getCards(ArrayList<Integer> cards){
        cardDeck.addAll(cards);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton b = (JButton)ae.getSource();
        if(b == play){
            try {
                Socket client = new Socket("localhost",18888);
                DataInputStream dis = new DataInputStream(client.getInputStream());
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());

                String input = name.getText();

                dos.writeUTF(input);
                //dis.readUTF();
                System.out.println(dis.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //dispose();
        }
    }
}
