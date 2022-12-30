package project.layout;

import CodeWithBeki.com.WellcomePage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class HomeLoginPage extends JFrame {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 650;
    private JLabel usernameLabel, imageLabel, passwordLabel,messageLabel1,messageLabel2;
    protected JLabel loginMessageLabel;
    private JButton helpButton, switchButton;
    protected JTextField usernameTextField;
    private JButton userButton, passwordButton, imageButton;
    protected JButton loginButton;
    private JButton button1, button2, button3, button4, button5, button6;
    protected JPasswordField loginPasswordField;
    private JPanel upperPanel, lowerLeftPanel1, lowerRightPanel, leftSidePanel, rightSidePanel;
    private String loginPageOption;

    //Constructor
    public HomeLoginPage(String title) {
        this.loginPageOption = title;
        Container myContainer = this.getContentPane();
        this.getRootPane().setBorder(new LineBorder(new Color(4, 222, 4)));
        //Customizing window
        setTitle(loginPageOption);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(100, 160);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        //Calling panel creating methods
        buildUpperPanel();
        buildLowerLeftPanel();
        buildLowerRightPanel();
        //add(rightSidePanel);
        //Adding these panels to the container
        add(imageButton);
        add(upperPanel);
        add(lowerLeftPanel1);
        add(leftSidePanel);
        //Calling buildAll method
        buildAll();
        //Adding these created by build all method to the lower Right panel
        add(loginMessageLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(usernameTextField);
        add(loginPasswordField);
        add(loginButton);
        add(helpButton);
        add(switchButton);
        add(userButton);
        add(passwordButton);
        add(imageLabel);
        //Adding lower right panel to the container
        add(lowerRightPanel);
        //Adding components to the panel
        buildQuickButtons();
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(messageLabel1);
        add(messageLabel2);
        add(lowerLeftPanel1);
        //Look and feel for windows
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error setting" +
                    "the look and feel");
            System.exit(0);
        }
        setVisible(true);
    }

    //Creating method to those five panels
    // final implies we cannot override this method in subclas
    private final void buildUpperPanel() {
        upperPanel = new JPanel();
        upperPanel.setBounds(0, 0, 1000, 150);
        upperPanel.setBackground(new Color(6, 87, 194));
        ImageIcon library = new ImageIcon("Project image\\upperImage.jpg");
        imageButton = new JButton(library);
        imageButton.setBounds(0, 0, 1000, 150);
    }

    private final void buildLowerLeftPanel() {
        lowerLeftPanel1 = new JPanel();
        lowerLeftPanel1.setBounds(31, 152, 420, 455);
        lowerLeftPanel1.setBackground(Color.WHITE);
        lowerLeftPanel1.setBackground(new Color(224, 222, 215));
        leftSidePanel = new JPanel();
        leftSidePanel.setBounds(0, 152, 28, 456);
        leftSidePanel.setBackground(new Color(122, 12, 222));
        rightSidePanel = new JPanel();
        rightSidePanel.setBounds(955, 152, 28, 456);
        rightSidePanel.setBackground(new Color(122, 12, 222));

    }

    private final void buildLowerRightPanel() {
        ImageIcon background = new ImageIcon("Project image\\login.png");
        imageLabel = new JLabel(background);
        imageLabel.setBounds(452, 152, 530, 455);
        lowerRightPanel = new JPanel();
        lowerRightPanel.setBounds(452, 152, 500, 455);
        lowerRightPanel.setBackground(new Color(140, 133, 140));
    }

    /*Creating building method known as buildAll to build username label and text field
      as well as password label and text field*/
    public abstract void editButton();

    private final void buildAll() {
        //Create font type and size for labels
        Font labelFont = new Font("monotype corsiva", Font.PLAIN, 23);
        //Creating username labels
        usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(labelFont);
        usernameLabel.setBounds(510, 350, 90, 40);
        usernameLabel.setBackground(new Color(95, 92, 212));
        //Creating password label
        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(510, 391, 90, 40);
        //creating message label
        loginMessageLabel = new JLabel("Login to your account");
        loginMessageLabel.setForeground(new Color(211, 9, 211));
        loginMessageLabel.setFont(labelFont);
        loginMessageLabel.setBounds(650, 315, 260, 40);

        //Creating text field to write
        usernameTextField = new JTextField("");
        usernameTextField.setFont(labelFont);
        usernameTextField.setBounds(650, 350, 220, 40);
        loginPasswordField = new JPasswordField();
        loginPasswordField.setFont(labelFont);
        loginPasswordField.setBounds(650, 395, 220, 40);
        //Creating login and help button
        Font buttonFont = new Font("Time New Roman", Font.PLAIN, 15);
        loginButton = new JButton("Login");
        loginButton.setFont(buttonFont);
        loginButton.setFocusable(false);
        loginButton.setContentAreaFilled(true);
        loginButton.setBackground(new Color(2, 22, 222));
        loginButton.setForeground(new Color(2, 0, 1));
        loginButton.setOpaque(true);
        loginButton.setBounds(670, 445, 150, 40);
        //loginButton.addActionListener(new LoginButtonListener());
        //Creating Help button
        helpButton = new JButton("Help?");
        helpButton.setFont(buttonFont);
        helpButton.setFocusable(false);
        helpButton.setBackground(new Color(222, 22, 222));
        helpButton.setBounds(705, 545, 80, 40);
        helpButton.addActionListener(new HelpButtonListener());
        //Creating Switch Button
        switchButton = new JButton("Switch account?");
        switchButton.setBounds(670, 495, 150, 40);
        switchButton.setFont(buttonFont);
        switchButton.setFocusable(false);
        switchButton.setBackground(new Color(122, 12, 222));
        switchButton.addActionListener(new SwitchButtonListener());
        //Build password and user icon button
        //Build image icon
        ImageIcon userIcon = new ImageIcon("Project image\\userIcon.png");
        userButton = new JButton(userIcon);
        userButton.setBounds(610, 350, 40, 40);
        userButton.setFocusable(false);
        ImageIcon passwordIcon = new ImageIcon("Project image\\passwordIcon.png");
        passwordButton = new JButton(passwordIcon);
        passwordButton.setBounds(610, 395, 40, 40);
        passwordButton.setFocusable(false);
    }

    private void buildQuickButtons() {
        //creating object and customize them
        Font buttonFont = new Font("Time New Roman", Font.PLAIN, 22);
        ImageIcon buton1 = new ImageIcon("Project image\\button1.jpg");
        button1 = new JButton(buton1);
        button1.setBounds(31, 304, 100, 100);
        button1.setFocusable(false);
        //Button 2
        ImageIcon buton2 = new ImageIcon("Project image\\button2.jpg");
        button2 = new JButton(buton2);
        button2.setBounds(31, 406, 100, 100);
        button2.setFocusable(false);
        //Button 3
        ImageIcon buton3 = new ImageIcon("Project image\\button3.jpg");
        button3 = new JButton(buton3);
        button3.setBounds(31, 508, 100, 100);
        button3.setFocusable(false);
        //Button 4
        button4 = new JButton();
        button4.setFont(buttonFont);
        button4.setBounds(131, 304, 319, 100);
        button4.setFocusable(false);
        button4.setContentAreaFilled(false);
        button4.setBackground(new Color(52, 132, 222));
        button4.setOpaque(true);
        //Button 5
        button5 = new JButton();
        button5.setBounds(131, 406, 319, 100);
        button5.setFocusable(false);
        button5.setContentAreaFilled(false);
        button5.setBackground(new Color(52, 132, 222));
        button5.setOpaque(true);

        //Button 6
        button6 = new JButton();
        button6.setBounds(131, 508, 319, 100);
        button6.setFocusable(false);
        button6.setContentAreaFilled(false);
        button6.setBackground(new Color(52, 132, 222));
        button6.setOpaque(true);
        //Welcome message for users label
        Font labelFont = new Font("monotype corsiva", Font.PLAIN, 50);
        messageLabel1 = new JLabel("Welcome to Gui ");
        messageLabel1.setFont(labelFont);
        messageLabel1.setBounds(80, 150, 330, 100);
        messageLabel1.setForeground(new Color(179, 34, 211));

        messageLabel2 = new JLabel("Application Library");
        messageLabel2.setFont(labelFont);
        messageLabel2.setBounds(45, 220, 380, 100);
        messageLabel2.setForeground(new Color(231, 31, 144));
    }
    //Action Listener for Switch account button
    private class HelpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent f) {
            JOptionPane.showMessageDialog(null, "In order to access our library" +
                    " you must have username and password" + " you can ask others for help");
        }
    }

    //Action Listener for Switch account button
    private class SwitchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new WellcomePage();
            dispose();
        }

    }

}

