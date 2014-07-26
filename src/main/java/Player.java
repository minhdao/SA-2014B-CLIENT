/**
 * Created by minh on 7/15/14.
 */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Instance of this class represents a real life player
 * Each player is a thread and should be carefully manage TODO
 **/

public class Player extends Observable{

    private String name;
    private Socket socket;
    private ArrayList<Integer> cardDeck;
    private ArrayList<Integer> playingCards;

    public Player(String name, Socket socket){
        this.name = name;
        this.socket = socket;
        cardDeck = new ArrayList<Integer>();
        playingCards = new ArrayList<Integer>();
    }
}
