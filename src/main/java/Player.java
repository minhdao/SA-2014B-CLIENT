/**
 * Created by minh on 7/15/14.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;

/**
 * Instance of this class represents a real life player
 * Each player is a thread and should be carefully manage TODO
 **/

public class Player extends Observable implements Runnable {

    private static Player instance = null;
    private CardDeck cardDeck;
    private CardDeck selectedCards;
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Communicator communicator;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
            instance.setUpCommunicator();
            new Thread(instance).start();
        }
        return instance;
    }

    public Player() {
        cardDeck = new CardDeck();
        selectedCards = new CardDeck();

        // these are to test only
        // TODO remove when done
//        cardDeck.getCards().add(30);
//        cardDeck.getCards().add(40);
//        cardDeck.getCards().add(50);
//        cardDeck.getCards().add(60);
//        cardDeck.getCards().add(70);
//        cardDeck.getCards().add(80);
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    private void setUpCommunicator() {
        try {
            clientSocket = new Socket(Client.serverAddress, Client.serverPort);
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            communicator = new Communicator(clientSocket, ois, oos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Communicator getCommunicator() {
        return communicator;
    }

    public CardDeck getSelectedCards() {
        return selectedCards;
    }

    // method to update card deck
    public void updateCardDeck(CardDeck playedCards) {
        for (int i = 0; i < playedCards.getCards().size(); i++) {
            this.cardDeck.getCards().remove((Object) playedCards.getCards().get(i));
        }
        printCardDeck();
        setChanged();
        notifyObservers(cardDeck);
    }

    // method for testing - print out card deck of player
    public void printCardDeck() {
        System.out.println("Total cards: " + cardDeck.getCards().size());
        for (int i = 0; i < cardDeck.getCards().size(); i++) {
            System.out.println(cardDeck.getCards().get(i));
        }
    }

    @Override
    public void run() {
        Object message = communicator.read();
        while (message != null) {
            if (message instanceof Test) {
                Test test = (Test) message;
                System.out.println(test.getMessage());
            }
            if (message instanceof CardDeck) {
                cardDeck = (CardDeck) message;
                for (int i = 0; i < cardDeck.getCards().size(); i++) {
                    System.out.println(cardDeck.getCards().get(i));
                }
                CardTableFrame.getInstance();
            }
            if (message instanceof String){
                String string = (String) message;
                System.out.println(string);
            }
            if (message instanceof Status){
                Status status = (Status) message;

                // to see what status is
                System.out.println("Status: ");
                System.out.println(status.toString());

                if (status == Status.Valid){
//                    updateCardDeck();
                }
            }
            message = communicator.read();
        }
    }
}