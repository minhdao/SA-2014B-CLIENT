import javax.swing.*;
import java.awt.*;

/**
 * Created by minh on 7/25/14.
 */
public class CardTableFrame extends JFrame {

    private static CardTableFrame instance = null;

    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem help = new JMenuItem("Help");
    private JMenuItem about = new JMenuItem("About");
    private PlayerCardPanel playerCardPanel;

    public static CardTableFrame getInstance(){
        if (instance == null){
            instance = new CardTableFrame();
        }
        return instance;
    }

    public CardTableFrame(){
        init();
    }

    public void init(){
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu options = new JMenu("Options");

        file.add(exit);
        options.add(help);
        options.add(about);
        bar.add(file);
        bar.add(options);
        setJMenuBar(bar);
        setLayout(new BorderLayout());
        playerCardPanel = new PlayerCardPanel();
        add(playerCardPanel, BorderLayout.SOUTH);
        add(GameStatusPanel.getInstance(), BorderLayout.WEST);
        add(PreviousMovePanel.getInstance(), BorderLayout.CENTER);
//        Player.getInstance().addObserver(PreviousMovePanel.getInstance()); // previous move panel observe Player
//        setBackground(Color.GREEN);
        setTitle("TIEN LEN - Table");
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
