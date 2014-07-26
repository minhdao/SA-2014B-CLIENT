/**
 * Created by minh on 7/25/14.
 */
public class Client {

    private static Client instance = null;
    private StartGameFrame startGameFrame;
    public static final String serverAddress = "localhost";
    public static final int serverPort = 18888;

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
