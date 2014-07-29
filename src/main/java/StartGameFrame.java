import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by minh on 7/25/14.
 */
public class StartGameFrame extends JFrame implements MouseListener {

    private JButton play = new JButton("Play game");
    private JTextField name = new JTextField();

    public StartGameFrame(){
        init();
    }

    public void init(){

        JPanel banner = new JPanel(new FlowLayout());
        banner.add(new JLabel(new ImageIcon("img/tienlen.jpg")));
        JPanel menu = new JPanel(new FlowLayout());
        menu.setBackground(Color.black);

        name.setColumns(20);
        play.addMouseListener(this);
        menu.add(name);
        menu.add(play);
        add(menu, BorderLayout.SOUTH);
        add(banner, BorderLayout.CENTER);

        setTitle("TIEN LEN - Client");
        setVisible(true);
        setSize(600, 400);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Player.getInstance();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setVisible(false);
        Player.getInstance();
//        Player.getInstance().getCommunicator().write("Minh");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
