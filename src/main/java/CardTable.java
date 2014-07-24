import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by minh on 7/24/14.
 */
public class CardTable extends JFrame implements ActionListener{

    private JButton play = new JButton("Done");
    private JTextField name = new JTextField();
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem help = new JMenuItem("Help");
    private JMenuItem about = new JMenuItem("About");

    private Communicator talk;

//    public static void main(String[] args) {
//        CardTable main = new CardTable();
//    }

    public CardTable(){
//        JMenuBar bar = new JMenuBar();
//        JMenu file = new JMenu("File");
//        JMenu options = new JMenu("Options");

        exit.addActionListener(this);
        help.addActionListener(this);
        about.addActionListener(this);

//        file.add(exit);
//        options.add(help);
//        options.add(about);
//
//        bar.add(file);
//        bar.add(options);

        MainPlayer mp = new MainPlayer();

//        setJMenuBar(bar);

        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        name.setColumns(20);
        play.addActionListener(this);
        add(name);
        add(play);
        add(mp);

        setTitle("TIEN LEN - Table");
        setVisible(false); // set to false by default
        setSize(1024, 768);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setTalk(Communicator talk) {
        this.talk = talk;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton b = (JButton)ae.getSource();
        if(b == play){
            String in = "Test";
        }
    }
}
