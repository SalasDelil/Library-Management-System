package CodeWithBeki.com;

import net.proteanit.sql.DbUtils;
import project.layout.HomePage;

import javax.swing.*;
import java.sql.*;

public class DisplayIssuedBooks extends HomePage {
    private JTable dispBooks;
    private JScrollPane scroll;

    public DisplayIssuedBooks() {
        super();
        try {
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
            Statement statment = connection1.createStatement();
            ResultSet result = statment.executeQuery("Select * from issued_books_table");
            dispBooks = new JTable();
            dispBooks.setModel(DbUtils.resultSetToTableModel(result));
            scroll = new JScrollPane(dispBooks);
            scroll.setBounds(135, 190, 815, 415);
        } catch (SQLException e) {
            System.out.println("NO!");
        }
        add(scroll);
        setVisible(true);
    }
    public static void main(String []args){
        new DisplayIssuedBooks();
    }

    @Override
    public void buildAll() {

    }

    @Override
    public void edditButton() {

    }
}
