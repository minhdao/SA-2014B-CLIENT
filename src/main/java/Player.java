/**
 * Created by minh on 7/15/14.
 */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Instance of this class represents a real life player
 * Each player is a thread and should be carefully manage TODO
 **/

public class Player implements Serializable {

    private String name;
    private transient Socket socket;
    private transient InputStream is;
    private transient OutputStream os;
    private transient ObjectInputStream ois = null;
    private transient ObjectOutputStream oos = null;
    private ArrayList<Integer> cardDeck;
    private ArrayList<Integer> playingCards;
    private boolean isPlaying;
    private boolean isDone;
    private boolean isWaiting;

    public Player(String name, Socket socket){
        this.name = name;
        this.socket = socket;
        cardDeck = new ArrayList<Integer>();
        playingCards = new ArrayList<Integer>();
    }

    public void printCardDeck(){
        System.out.println("Total: "+cardDeck.size());
        for (int i =0; i <cardDeck.size(); i++){
            System.out.println(cardDeck.get(i));
        }
    }
}
