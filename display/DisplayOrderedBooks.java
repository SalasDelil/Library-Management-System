package display;

import CodeWithBeki.com.HomeLibrarianPage;
import net.proteanit.sql.DbUtils;
import project.layout.HomePage;
import Action.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DisplayOrderedBooks extends HomePage {
    private JTable dispBooks;
    private JScrollPane scroll;
    private JComboBox sortSearch;
    private JButton borrowButton;

    public DisplayOrderedBooks() {
        super();
        try {
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
            Statement statment = connection1.createStatement();
            ResultSet result = statment.executeQuery("Select * from ordered_books_table");
            dispBooks = new JTable();
            dispBooks.setModel(DbUtils.resultSetToTableModel(result));
            scroll = new JScrollPane(dispBooks);
            scroll.setBounds(135, 190, 815, 415);
        } catch (SQLException e) {
            System.out.println("NO!");
        }
        buildAll();
        edditButton();
        add(borrowButton);
        add(leftSidePanel2);
        add(sortSearch);
        add(scroll);
        setVisible(true);
    }
     //Override method from parent class
    @Override
    public void buildAll() {
        String [] comboList = {"Name", "ISBN no", "Author"};
        sortSearch = new JComboBox(comboList);
        sortSearch.setBounds(895,153,60,33);
        sortSearch.setFocusable(false);
        borrowButton = new JButton("Borrow");
        borrowButton.setBounds(32,190,96,50);
        borrowButton.setOpaque(true);
        borrowButton.setBackground(new Color(182, 35, 215));
        borrowButton.setFocusable(false);
        borrowButton.addActionListener(new ButtonListener());
    }
    //Override method from parent class
    @Override
    public void edditButton() {
        pathTextField.setText(">Home Librarian>ordered books");
        //searchTextField.setText("Quick search");
        backButton.addActionListener(new ButtonListener());
        homeButton.addActionListener(new ButtonListener());
    }
    //Defining class for button Listener
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent button) {
            if(button.getSource()==backButton){
                new HomeLibrarianPage();
                dispose();
            }
            else if(button.getSource()==homeButton){
                new DisplayOrderedBooks();
                dispose();
            }
            else if(button.getSource()==borrowButton){
               new BorrowBook("Record borrow form");
            }

        }
    }
}
