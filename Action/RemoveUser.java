package Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RemoveUser extends HomeAction{
    private JButton submitButton, cancelButton;
    private JLabel pearsonNameLabel, id_numberLabel, departmentLabel, phoneNumberLabel,passwordLabel;
    private JTextField textField1, textField2, textField3,textField4;
    private JComboBox departmentList;

    public RemoveUser(String title) {
        super(title);
        buildAll();
        add(pearsonNameLabel);
        add(id_numberLabel);
        add(passwordLabel);
        add(departmentLabel);
        add(phoneNumberLabel);
        add(textField1);
        add(textField2);
        add(textField3);
        add(textField4);
        add(departmentList);
        add(cancelButton);
        add(submitButton);
        add(lowerImageLabel1);
        add(lowerPanel);
        setVisible(true);
    }

    public void buildAll() {
        Font fontStyle = new Font("monotype corsiva", Font.ITALIC, 20);
        pearsonNameLabel = new JLabel("Student name");
        pearsonNameLabel.setBounds(40, 180, 100, 40);
        pearsonNameLabel.setFont(fontStyle);
        //
        id_numberLabel = new JLabel("Id number");
        id_numberLabel.setBounds(40, 230, 100, 40);
        id_numberLabel.setFont(fontStyle);
        //
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 280, 100, 40);
        passwordLabel.setFont(fontStyle);
        //
        phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(340, 180, 150, 40);
        phoneNumberLabel.setFont(fontStyle);
        //
        departmentLabel = new JLabel("Department");
        departmentLabel.setBounds(380, 230, 150, 40);
        departmentLabel.setFont(fontStyle);
        //
        textField1 = new JTextField();
        textField1.setBounds(140, 180, 150, 40);
        textField1.setFont(fontStyle);
        //
        textField2 = new JTextField();
        textField2.setBounds(140, 230, 150, 40);
        textField2.setFont(fontStyle);
        //
        String[] departments = {"Electrical", "Software",
                "Biomedical", "Chemical",
                "Civil", "Mechanical"};
        departmentList = new JComboBox(departments);
        departmentList.setBounds(480, 230, 150, 40);
        departmentList.setFocusable(false);
        departmentList.setFont(fontStyle);
        //
        textField3 = new JTextField();
        textField3.setBounds(140, 280, 150, 40);
        textField3.setFont(fontStyle);
        //
        textField4 = new JTextField();
        textField4.setBounds(480, 180, 150, 40);
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
            text4 =(String) textField4.getText();
            String department = (String) departmentList.getSelectedItem();
            if (button.getSource() == submitButton) {
                if (!text1.equals("") & !text2.equals("") & !text3.equals("") &!text4.equals("")&!department.equals("")) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
                        Statement statement = connection.createStatement();
                        //to remove order after recording return form
                        String delete = "delete from student_table where Id_Number='"+text2+"';";
                        statement.executeUpdate(delete);
                        statement.close();
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "There is no user with this Id_Number!");
                    }
                    dispose();
                    JOptionPane.showMessageDialog(null, "Removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "All space must be filled!");
                }
            } else if (button.getSource() == cancelButton) {
                dispose();
            }

        }
    }

}
