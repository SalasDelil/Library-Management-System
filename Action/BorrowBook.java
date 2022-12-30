package Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;

public class BorrowBook extends HomeAction{
    private JButton submitButton,cancelButton;
    private JLabel pearsonNameLabel, bookNameLabel,id_numberLabel;
    private JLabel isbn_NumberLabel, departmentLabel,authorLabel,maxDate;
    private JTextField textField1,textField2,textField3;
    private JTextField textField4,textField5,textField6;
    private JComboBox departmentList;
    public BorrowBook(String title){
        super(title);
        buildAll();
        add(pearsonNameLabel);
        add(bookNameLabel);
        add(id_numberLabel);
        add(isbn_NumberLabel);
        add(departmentLabel);
        add(authorLabel);
        add(maxDate);
        add(textField1);
        add(textField2);
        add(departmentList);
        add(textField3);
        add(textField4);
        add(textField5);
        add(textField6);
        add(cancelButton);
        add(submitButton);
        add(lowerPanel);
        setVisible(true);
    }
    public void buildAll() {
        Font fontStyle = new Font("monotype corsiva",Font.ITALIC,20);
        pearsonNameLabel = new JLabel("Student name");
        pearsonNameLabel.setBounds(40,180,100,40);
        pearsonNameLabel.setFont(fontStyle);
        pearsonNameLabel.setForeground(Color.WHITE);
        //
        id_numberLabel = new JLabel("Id number");
        id_numberLabel.setBounds(40,230,100,40);
        id_numberLabel.setFont(fontStyle);
        id_numberLabel.setForeground(Color.WHITE);
        //
        departmentLabel = new JLabel("Department");
        departmentLabel.setBounds(40,280,100,40);
        departmentLabel.setFont(fontStyle);
        departmentLabel.setForeground(Color.WHITE);
        //
        bookNameLabel = new JLabel("Book name");
        bookNameLabel.setBounds(380,160,150,40);
        bookNameLabel.setFont(fontStyle);
        bookNameLabel.setForeground(Color.WHITE);
        //
        isbn_NumberLabel = new JLabel("ISBN number");
        isbn_NumberLabel.setBounds(370,210,150,40);
        isbn_NumberLabel.setFont(fontStyle);
        isbn_NumberLabel.setForeground(Color.WHITE);

        authorLabel = new JLabel("Author");
        authorLabel.setBounds(380,260,150,40);
        authorLabel.setFont(fontStyle);
        authorLabel.setForeground(Color.WHITE);
        //
        maxDate = new JLabel("Max borrow date");
        maxDate.setBounds(350,310,150,40);
        maxDate.setFont(fontStyle);
        maxDate.setForeground(Color.WHITE);
        //
        textField1 = new JTextField();
        textField1.setBounds(140,180,150,40);
        textField1.setFont(fontStyle);
        //
        textField2 = new JTextField();
        textField2.setBounds(140,230,150,40);
        textField2.setFont(fontStyle);
        //
        String [] departments ={"Electrical","Software",
                                 "Biomedical","Chemical",
                                 "Civil", "Mechanical"};
        departmentList = new JComboBox(departments) ;
        departmentList.setBounds(140,280,150,40);
        departmentList.setFocusable(false);
        departmentList.setFont(fontStyle);
        //
        textField3 = new JTextField();
        textField3.setBounds(480,160,150,40);
        textField3.setFont(fontStyle);
        //
        textField4 = new JTextField();
        textField4.setBounds(480,210,150,40);
        textField4.setFont(fontStyle);
        //
        textField5 = new JTextField();
        textField5.setBounds(480,260,150,40);
        textField5.setFont(fontStyle);
        //
        textField6 = new JTextField();
        textField6.setBounds(480,310,150,40);
        textField6.setFont(fontStyle);
        //
        submitButton = new JButton("Submit");
        submitButton.setBounds(240,380,100,40);
        submitButton.setFocusable(false);
        submitButton.setFont(fontStyle);
        submitButton.setOpaque(true);
        submitButton.setBackground(new Color(31, 234, 10));
        submitButton.addActionListener(new ButtonListener());
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(400,380,100,40);
        cancelButton.setFocusable(false);
        cancelButton.setFont(fontStyle);
        cancelButton.setBackground(new Color(157, 9, 190));
        cancelButton.addActionListener(new ButtonListener());
    }
    //Defining private class
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent button) {
            String text1, text2,text3,text4,text5;
            int days;
            text1 = (String) textField1.getText();
            text2 = (String) textField2.getText();
            text3 = (String) textField4.getText();
            text4 = (String) textField5.getText();
            text5 = (String) textField6.getText();
            days = Integer.parseInt(textField6.getText());
            String department = (String)departmentList.getSelectedItem();
            if(button.getSource()==submitButton){
                if(!text1.equals("")&!text2.equals("")&!text3.equals("")&!text4.equals("")&!text5.equals("")&!department.equals("")){

                    try {
                        Calendar issuedDate = Calendar.getInstance();
                        String date = "" + (issuedDate.get(Calendar.MONTH) + 1) + "-" + (issuedDate.get(Calendar.DATE)) + "-" + (issuedDate.get(Calendar.YEAR));
                        System.out.println(date);

                        issuedDate.add(Calendar.DATE, days);
                        String returnDate = "" + (issuedDate.get(Calendar.MONTH) + 1) + "-" + (issuedDate.get(Calendar.DATE)) + "-" + (issuedDate.get(Calendar.YEAR));
                        System.out.println(returnDate);

                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
                        Statement statement = connection.createStatement();

                        String query = "INSERT INTO issued_books_table VALUES('"+text1+"','"+text2+"','"+ department +"','"+ text3 +"','"+ text4 +"','"+ text5 +"','" +date+"','"+returnDate+"');";
                        statement.executeUpdate(query);
                        //to remove order after recording borrow form
                        String delete = "delete from ordered_books_table where Student_ID='"+text2+"';";
                        statement.executeUpdate(delete);
                        statement.close();
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null,"This id is already registered!");
                    }
                    dispose();
                JOptionPane.showMessageDialog(null,"Record have been saved successfully!");}
                else {
                    JOptionPane.showMessageDialog(null,"All space must be filled!");
                }
            }
            else if(button.getSource()==cancelButton){
                dispose();
            }

        }
    }
}
