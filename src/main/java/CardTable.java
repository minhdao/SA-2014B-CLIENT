import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by minh on 7/24/14.
 */
public class CardTable extends JFrame implements ActionListener{

    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem help = new JMenuItem("Help");
    private JMenuItem about = new JMenuItem("About");
    private MainPlayer mp;

    private Communicator talk;

    public CardTable(){
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu options = new JMenu("Options");

        exit.addActionListener(this);
        help.addActionListener(this);
        about.addActionListener(this);

        file.add(exit);
        options.add(help);
        options.add(about);

        bar.add(file);
        bar.add(options);

        mp = new MainPlayer();

        setJMenuBar(bar);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(mp, BorderLayout.SOUTH);

        setTitle("TIEN LEN - Table");
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(false); // set to false by default
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setTalk(Communicator talk) {
        this.talk = talk;
    }

    public MainPlayer getMp() {
        return mp;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton b = (JButton)ae.getSource();
    }
}
