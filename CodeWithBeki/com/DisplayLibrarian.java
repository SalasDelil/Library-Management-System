package CodeWithBeki.com;

import net.proteanit.sql.DbUtils;
import project.layout.HomePage;

import javax.swing.*;
import java.sql.*;

public class DisplayLibrarian extends HomePage {
    private JTable dispBooks;
    private JScrollPane scroll;

    public DisplayLibrarian() {
        super();
        try {
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
            Statement statment = connection1.createStatement();
            ResultSet result = statment.executeQuery("Select * from librarian_table");
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
        new DisplayLibrarian();
    }

    @Override
    public void buildAll() {

    }

    @Override
    public void edditButton() {

    }
}
