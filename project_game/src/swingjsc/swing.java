package swingjsc;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;


public class swing extends JFrame {
    JFrame frame = new JFrame("TEXT AREA");
    JPanel panel = new JPanel();
    JComponent areal = new JComponent(null, 10, 30) {
        @Override
        public void setInheritsPopupMenu(boolean value) {
            super.setInheritsPopupMenu(value);
        }
    };

    public swing(){
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
        panel.add(areal);
        //areal.setLineWrap(true);

    }

    public static void main(String[] args) {
        new swing();
    }

}
