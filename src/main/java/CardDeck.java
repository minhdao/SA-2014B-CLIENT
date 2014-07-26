import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Shinichi on 21/07/2014.
 */
public class CardDeck extends Observable implements Serializable {
    private ArrayList<Integer> cards = new ArrayList<Integer>();

    public ArrayList<Integer> getCards() {
        return cards;
    }
}
