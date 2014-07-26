/**
 * Created by minh on 7/25/14.
 */
public class Client {

    private static Client instance = null;
    private StartGameFrame startGameFrame;

    public static Client getInstance(){
        if (instance == null){
            instance = new Client();
        }
        return instance;
    }

    public static void main(String[] args) {

        getInstance().startGameFrame = new StartGameFrame();

    }
}
