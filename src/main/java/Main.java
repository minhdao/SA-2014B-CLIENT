import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by minh on 7/16/14.
 */
public class Main {
    private static LinkedHashMap<Integer, String> cardMap = new LinkedHashMap<Integer, String>();
    private static ArrayList<Integer> cardDeck;

    public static void main(String[] args) {
        setCardMap();
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
