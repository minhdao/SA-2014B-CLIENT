/**
 * Created by minh on 7/15/14.
 */

import java.util.Observable;

/**
 * Instance of this class represents a real life player
 * Each player is a thread and should be carefully manage TODO
 **/

public class Player extends Observable{

    private static Player instance = null;
    private CardDeck cardDeck;
    private Communicator communicator;

    public static Player getInstance(){
        if (instance == null){
            instance = new Player();
        }
        return instance;
    }

    public Player(){
        cardDeck = new CardDeck();

        // these are to test only
        // TODO remove when done
        cardDeck.getCards().add(30);
        cardDeck.getCards().add(40);
        cardDeck.getCards().add(50);
        cardDeck.getCards().add(60);
        cardDeck.getCards().add(70);
        cardDeck.getCards().add(80);
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    // method to update card deck
    public void updateCardDeck(CardDeck playedCards){
        for (int i =0; i < playedCards.getCards().size(); i++){
            this.cardDeck.getCards().remove((Object)playedCards.getCards().get(i));
        }
        printCardDeck();
        setChanged();
        notifyObservers(cardDeck);
    }

    // method for testing - print out card deck of player
    public void printCardDeck(){
        System.out.println("Total cards: " + cardDeck.getCards().size());
        for (int i =0; i < cardDeck.getCards().size(); i++){
            System.out.println(cardDeck.getCards().get(i));
        }
    }
}
