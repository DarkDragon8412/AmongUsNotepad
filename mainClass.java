import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class mainClass extends JComponent {

    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;

    public mainClass(Color color, String name, int xPosition, int yPosition) {
        setBorder(new LineBorder(color, 4));
        setBackground(Color.WHITE);
        setBounds(xPosition, yPosition, 50, 50);
        setOpaque(true);

        setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel(name.toUpperCase());
        nameLabel.setForeground(color);
        add(nameLabel);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;

                setLocation(myX + deltaX, myY + deltaY);
            }
            @Override
            public void mouseMoved(MouseEvent e) { }
        });
    }
}

class mainMethod {
    static List<JComponent> comps = new ArrayList<>();
    static JFrame f = new JFrame("Among Us Notepad");
    static JPanel glass = (JPanel) f.getGlassPane();
    static Color[] colors = {Color.RED, Color.BLUE, new Color(39, 102, 28), Color.green, Color.PINK,
            new Color(232, 74, 0),
            Color.YELLOW, Color.DARK_GRAY, Color.LIGHT_GRAY, new Color(94, 23, 101),
            new Color(106, 68, 38), Color.CYAN};
    static String[] colorNames = {"red", "blue", "dgreen", "green", "pink", "orange", "yellow", "dgray", "white", "purple", "brown", "cyan"};

    public static void main(String[] args) {
        JPanel[] panels = new JPanel[4]; //1:UNK 2:DEAD 3:INN 4:SUS
        JLabel[] labels = new JLabel[4];
        for (int i = 0; i < panels.length; i++) {
            labels[i] = new JLabel();
            panels[i] = new JPanel();
            labels[i].setForeground(Color.WHITE);
            panels[i].setBorder(new LineBorder(Color.WHITE, 3));
            panels[i].setBackground(Color.BLACK);
            labels[i].setFont(new Font(labels[i].getFont().getFontName(), labels[i].getFont().getStyle(), 40));
            panels[i].setLayout(new GridBagLayout());
            panels[i].add(labels[i]);
            f.add(panels[i]);
        }
        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) { if(e.getKeyCode() == KeyEvent.VK_U) reset(); }
            @Override
            public void keyReleased(KeyEvent e) { }
        });
        labels[0].setText("UNKNOWN");
        labels[1].setText("DEAD");
        labels[2].setText("INNOCENT");
        labels[3].setText("SUSPICIOUS");
        f.setAlwaysOnTop(true);
        f.setLayout(new GridLayout(panels.length,1));
        glass.setVisible(true);
        glass.setLayout(null);
        makeComponents();
        f.setResizable(false);
        f.setSize(350, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
    private static void reset() {
        int xPosition = 5;
        int yPosition = 5;
        int i = 0;
        for (Component c:comps) {
            c.setLocation(xPosition, yPosition);
            xPosition += 55;
            if (i == 5) {
                yPosition += 55;
                xPosition = 5;
            }
            i++;
        }
    }
    private static void makeComponents() {
        int xPosition = 5;
        int yPosition = 5;
        for (int i = 0; i < colors.length; i++) {
            mainClass mc = new mainClass(colors[i], colorNames[i], xPosition, yPosition);
            glass.add(mc);
            xPosition += 55;
            if (i == 5) {
                yPosition += 55;
                xPosition = 5;
            }
            comps.add(mc);
        }
    }

}
