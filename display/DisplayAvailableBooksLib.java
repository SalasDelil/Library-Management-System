package display;

import CodeWithBeki.com.HomeLibrarianPage;
import net.proteanit.sql.DbUtils;
import project.layout.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class DisplayAvailableBooksLib extends HomePage {
    private JTable dispBooks;
    private JScrollPane scroll;
    private JComboBox sortSearch;

    public DisplayAvailableBooksLib() {
        super();
        /*try {
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
            Statement statment = connection1.createStatement();
            ResultSet result = statment.executeQuery("Select * from available_books_table");
            dispBooks = new JTable();
            dispBooks.setModel(DbUtils.resultSetToTableModel(result));
            scroll = new JScrollPane(dispBooks);
            scroll.setBounds(135, 190, 815, 415);
        } catch (SQLException e) {
            System.out.println("NO!");
        }*/
        //................
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("Select * from available_books_table");
            dispBooks = new JTable();
            dispBooks.setModel(DbUtils.resultSetToTableModel(result1));
            scroll = new JScrollPane(dispBooks);
            scroll.setBounds(135, 190, 815, 415);
            add(scroll);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,"Unable to open database to JTable");
        }
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
                    String query = "Select * from available_books_table where Book_Name like '%+" + searchTextField.getText() + "%' or Author like '%" + searchTextField.getText() + "%' or Publication_Year like '%"+searchTextField.getText()+"%';";
                    Statement statement = connection.createStatement();

                    if (searchTextField.getText() != null) {
                        ResultSet result1 = statement.executeQuery(query);
                        dispBooks = new JTable();
                        dispBooks.setModel(DbUtils.resultSetToTableModel(result1));
                        scroll = new JScrollPane(dispBooks);
                        scroll.setBounds(135, 190, 815, 415);
                        add(scroll);
                    } else {
                        ResultSet result1 = statement.executeQuery("Select * from available_books_table;");
                        dispBooks = new JTable();
                        dispBooks.setModel(DbUtils.resultSetToTableModel(result1));
                        scroll = new JScrollPane(dispBooks);
                        scroll.setBounds(135, 190, 815, 415);
                        add(scroll);
                    }
                } catch (SQLException ex) {
                    System.out.println("Search field not working");
                }
            }
        });
        ///................
        buildAll();
        edditButton();
        add(sortSearch);
        add(scroll);
        setVisible(true);
    }

    //override method from parent class
    @Override
    public void buildAll() {
        String[] comboList = {"Name", "ISBN no", "Author"};
        sortSearch = new JComboBox(comboList);
        sortSearch.setBounds(895, 153, 60, 33);
        sortSearch.setFocusable(false);

    }

    @Override
    public void edditButton() {
        pathTextField.setText(">Home Librarian>available books");
        //searchTextField.setText("Quick search");
        backButton.addActionListener(new ButtonListener());
        homeButton.addActionListener(new ButtonListener());

    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent button) {
            if(button.getSource()==backButton){
                new HomeLibrarianPage();
                dispose();
            }
            else if(button.getSource()==homeButton){
                new DisplayAvailableBooksLib();
                dispose();
            }

        }
    }
}
