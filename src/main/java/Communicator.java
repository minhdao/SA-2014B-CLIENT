import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Shinichi on 21/07/2014.
 */
public class Communicator {
    private Socket client;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public Communicator(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        this.client = socket;
        this.ois = ois;
        this.oos = oos;
    }

    public void write(Object obj) {
        try {
            oos.writeObject(obj);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Object read() {
        try {
            return ois.readObject();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void close() {
        try {
            client.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
