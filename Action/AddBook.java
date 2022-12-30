package Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddBook extends HomeAction {
    private JButton submitButton, cancelButton;
    private JLabel bookNameLabel, authorLabel, numberOfLabel;
    private JLabel isbn_NumberLabel, publicationYearLabel;
    private JTextField textField1, textField2, textField3, textField4;
    private JTextField textField5, textField6, textField7;

    public AddBook(String title) {
        super(title);
        buildAll();
        add(bookNameLabel);
        add(isbn_NumberLabel);
        add(authorLabel);
        add(publicationYearLabel);
        add(numberOfLabel);
        add(textField1);
        add(textField2);
        add(textField3);
        add(textField4);
        add(textField5);
       /* add(textField6);
        add(textField7);*/
        add(cancelButton);
        add(submitButton);
        add(lowerImageLabel);
        add(lowerPanel);
        setVisible(true);
    }

    public void buildAll() {
        Font fontStyle = new Font("monotype corsiva", Font.ITALIC, 20);
        bookNameLabel = new JLabel("Book name");
        bookNameLabel.setBounds(30, 180, 100, 40);
        bookNameLabel.setFont(fontStyle);
        //
        authorLabel = new JLabel("Author");
        authorLabel.setBounds(30, 230, 100, 40);
        authorLabel.setFont(fontStyle);
        //
        isbn_NumberLabel = new JLabel("ISBN_Number");
        isbn_NumberLabel.setBounds(30, 280, 130, 40);
        isbn_NumberLabel.setFont(fontStyle);
        //
        publicationYearLabel = new JLabel("Year of publication");
        publicationYearLabel.setBounds(350, 180, 150, 40);
        publicationYearLabel.setFont(fontStyle);
        //
        numberOfLabel = new JLabel("Number of Books");
        numberOfLabel.setBounds(350, 230, 150, 40);
        numberOfLabel.setFont(fontStyle);
        //
        textField1 = new JTextField();
        textField1.setBounds(150, 180, 150, 40);
        textField1.setFont(fontStyle);
        //
        textField2 = new JTextField();
        textField2.setBounds(150, 230, 150, 40);
        textField2.setFont(fontStyle);
        //
        textField3 = new JTextField();
        textField3.setBounds(150, 280, 150, 40);
        textField3.setFont(fontStyle);
        //
        textField4 = new JTextField();
        textField4.setBounds(490, 180, 150, 40);
        textField4.setFont(fontStyle);
        //
        textField5 = new JTextField();
        textField5.setBounds(490, 230, 150, 40);
        textField5.setFont(fontStyle);
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

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent button) {
            String text1, text2,text3, text4;
            int text5=0;
            text1 = (String) textField1.getText();
            text2 = (String) textField2.getText();
            text3 = (String) textField3.getText();
            text4 = (String) textField4.getText();

            //String department = (String)departmentList.getSelectedItem();
            if (button.getSource() == submitButton) {
                if(!text1.equals("")&!text2.equals("")&!text3.equals("")&!text4.equals("")){
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
                        Statement statement = connection.createStatement();
                        ResultSet result = statement.executeQuery("select ISBN_Number from available_books_table where ISBN_Number ='" + text3 + "';");
                        if (!result.next()) {
                            try{text5 = Integer.parseInt(textField5.getText());
                            }catch(NumberFormatException f){
                                JOptionPane.showMessageDialog(null," Error! number of books should be integer type");
                            }
                        String query = "INSERT INTO available_books_table VALUES('" + text1 + "','" + text2 + "','" + text3 + "','" + text4 + "'," + text5 + ");";
                        statement.executeUpdate(query);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Book added successfully!");
                    }
                        else{
                            ResultSet resultset = statement.executeQuery("select Quantity from available_books_table where ISBN_Number ='"+text3+"';");
                            resultset.next();
                            int quantity = resultset.getInt("Quantity");
                            int sum = quantity + text5;
                            System.out.println(sum);
                           statement.executeUpdate("Update available_books_table set Quantity = "+sum+" where ISBN_Number ='" + text3 + "';");
                            dispose();
                            JOptionPane.showMessageDialog(null, "Quantity updated successfully!");
                        }
                    }catch(SQLException exp){
                        JOptionPane.showMessageDialog(null,"Unable to add books\n"+"Please contact admin!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"All space provided must be filled");
                }
            }
            else if(button.getSource()==cancelButton){
               dispose();
            }
        }
    }
}
