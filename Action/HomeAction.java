package Action;

import javax.swing.*;
import java.awt.*;

public class HomeAction extends JFrame {
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 500;
    protected JPanel upperPanel, lowerPanel;
    protected JLabel lowerImageLabel, upperImageLabel, lowerImageLabel1;
    private String title;

    public HomeAction(String title) {
        this.title = title;
        Container container = this.getContentPane();
        setResizable(false);
        setTitle(title);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildPanels();
        add(upperImageLabel);
        add(upperPanel);
        add(lowerPanel);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error setting" +
                    "the look and feel");
            System.exit(0);
        }
        setVisible(false);
    }

    protected void buildPanels() {
        ImageIcon label1 = new ImageIcon("Project image\\popupl.jpg");
        upperImageLabel = new JLabel(label1);
        upperImageLabel.setBounds(0, 0, 700, 150);
        upperPanel = new JPanel();
        upperPanel.setBounds(0, 0, 700, 150);
        upperPanel.setBackground(new Color(175, 226, 245));
        ImageIcon label2 = new ImageIcon("Project image\\popup.jpg");
        lowerImageLabel = new JLabel(label2);
        lowerImageLabel.setBounds(0, 151, 700, 349);
        lowerPanel = new JPanel();
        lowerPanel.setBounds(0, 151, 700, 349);
        lowerPanel.setBackground(new Color(212, 228, 229));
        ImageIcon label3 = new ImageIcon("Project image\\popup2.jpg");
        lowerImageLabel1 = new JLabel(label3);
        lowerImageLabel1.setBounds(0, 151, 700, 349);
    }
}
