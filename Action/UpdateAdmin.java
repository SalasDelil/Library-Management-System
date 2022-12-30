package Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateAdmin extends HomeAction{
    private JButton submitButton, cancelButton;
    private JLabel pearsonNameLabel, usernameLabel, passwordLabel, phoneNumberLabel;
    private JTextField textField1, textField2, textField3, textField4;

    public UpdateAdmin(String title) {
        super(title);
        buildAll();
        add(pearsonNameLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(phoneNumberLabel);
        add(textField1);
        add(textField2);
        add(textField3);
        add(textField4);
        add(cancelButton);
        add(submitButton);
        add(lowerImageLabel);
        add(lowerPanel);
        setVisible(true);
    }

    public void buildAll() {
        Font fontStyle = new Font("monotype corsiva", Font.ITALIC, 20);
        pearsonNameLabel = new JLabel("Name");
        pearsonNameLabel.setBounds(60, 180, 100, 40);
        pearsonNameLabel.setFont(fontStyle);
        //
        usernameLabel = new JLabel("username");
        usernameLabel.setBounds(40, 280, 100, 40);
        usernameLabel.setFont(fontStyle);
        //
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(370, 180, 100, 40);
        passwordLabel.setFont(fontStyle);
        //
        phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(350, 280, 150, 40);
        phoneNumberLabel.setFont(fontStyle);
        //
        textField1 = new JTextField();
        textField1.setBounds(120, 180, 150, 40);
        textField1.setFont(fontStyle);
        //
        textField2 = new JTextField();
        textField2.setBounds(120, 280, 150, 40);
        textField2.setFont(fontStyle);
        //
        //
        textField3 = new JTextField();
        textField3.setBounds(460, 180, 150, 40);
        textField3.setFont(fontStyle);
        //
        textField4 = new JTextField();
        textField4.setBounds(460, 280, 150, 40);
        textField4.setFont(fontStyle);
        //
        submitButton = new JButton("Submit");
        submitButton.setBounds(240, 380, 100, 40);
        submitButton.setFocusable(false);
        submitButton.setFont(fontStyle);
        submitButton.setOpaque(true);
        submitButton.setBackground(new Color(31, 234, 10));
        submitButton.addActionListener(new ButtonListener());
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(400, 380, 100, 40);
        cancelButton.setFocusable(false);
        cancelButton.setFont(fontStyle);
        cancelButton.setBackground(new Color(157, 9, 190));
        cancelButton.addActionListener(new ButtonListener());
    }

    //Defining private class
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent button) {
            String text1, text2, text3, text4, text5;
            int days;
            text1 = (String) textField1.getText();
            text2 = (String) textField2.getText();
            text3 = (String) textField3.getText();
            text4 = (String) textField4.getText();
            if (button.getSource() == submitButton) {
                if (!text1.equals("") & !text2.equals("") & !text3.equals("") & !text4.equals("")) {

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
                        Statement statement = connection.createStatement();
                        String query = "INSERT INTO admin_table VALUES('" + text1 + "','" + text2 + "','" + text3 + "','" + text4 + "');";
                        System.out.println("Yes");
                        statement.executeUpdate(query);
                        statement.close();
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "This id is already registered!");
                    }
                    dispose();
                    JOptionPane.showMessageDialog(null, "Record have been saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "All space must be filled!");
                }
            } else if (button.getSource() == cancelButton) {
                dispose();
            }

        }
    }
}
