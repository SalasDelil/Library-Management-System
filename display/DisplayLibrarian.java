package display;

import CodeWithBeki.com.HomeAdminPage;
import net.proteanit.sql.DbUtils;
import project.layout.HomePage;
import Action.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class DisplayLibrarian extends HomePage {
    private JTable dispBooks;
    private JScrollPane scroll;
    private JComboBox sortSearch;
    private JButton addLibrarianButton, removeLibrarianButton,updateLibrarianButton;

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
       /* try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("Select * from available_books_table");
            dispBooks = new JTable();
            dispBooks.setModel(DbUtils.resultSetToTableModel(result1));
            scroll = new JScrollPane(dispBooks);
            scroll.setBounds(135, 190, 815, 415);
            add(scroll);
        } catch (Exception exception) {
            System.out.println("Error");
        }*/
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
                    String query = "Select * from librarian_table where Name like '%+" + searchTextField.getText() + "%'or Username like '%"+searchTextField.getText()+"%';";
                    Statement statement = connection.createStatement();

                    if (searchTextField.getText() != null) {
                        ResultSet result1 = statement.executeQuery(query);
                        dispBooks = new JTable();
                        dispBooks.setModel(DbUtils.resultSetToTableModel(result1));
                        scroll = new JScrollPane(dispBooks);
                        scroll.setBounds(135, 190, 815, 415);
                        add(scroll);
                    } else {
                        ResultSet result1 = statement.executeQuery("Select * from librarian_table;");
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
        buildAll();
        edditButton();
        add(sortSearch);
        add(scroll);
        add(addLibrarianButton);
        add(removeLibrarianButton);
        add(updateLibrarianButton);
        add(leftSidePanel2);
        setVisible(true);
    }

    //Override method from parent class
    @Override
    public void buildAll() {
        String[] comboList = {"Name", "username"};
        sortSearch = new JComboBox(comboList);
        sortSearch.setBounds(895, 153, 60, 33);
        sortSearch.setFocusable(false);
        addLibrarianButton = new JButton("Add");
        addLibrarianButton.setBounds(32,190,96,50);
        addLibrarianButton.setOpaque(true);
        addLibrarianButton.setBackground(new Color(182, 35, 215));
        addLibrarianButton.setFocusable(false);
        addLibrarianButton.addActionListener(new ButtonListener());
        //
        removeLibrarianButton = new JButton("Remove");
        removeLibrarianButton.setBounds(32,245,96,50);
        removeLibrarianButton.setOpaque(true);
        removeLibrarianButton.setBackground(new Color(44, 46, 217));
        removeLibrarianButton.setFocusable(false);
        removeLibrarianButton.addActionListener(new ButtonListener());
        //
        updateLibrarianButton = new JButton("Update");
        updateLibrarianButton.setBounds(32,300,96,50);
        updateLibrarianButton.setOpaque(true);
        updateLibrarianButton.setBackground(new Color(44, 46, 217));
        updateLibrarianButton.setFocusable(false);

    }

    @Override
    public void edditButton() {
        pathTextField.setText(">Home admin>manage librarian");
        //searchTextField.setText("Quick search");
        backButton.addActionListener(new ButtonListener());
        homeButton.addActionListener(new ButtonListener());
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent button) {
            if(button.getSource()==backButton){
                new HomeAdminPage();
                dispose();
            }
            else if(button.getSource()==homeButton){
                new DisplayLibrarian();
                dispose();
            }
            else if(button.getSource()==addLibrarianButton){
                new AddLibrarian("Add Librarian");
            }
            else if(button.getSource()==removeLibrarianButton){
                 new RemoveLibrarian("Remove Librarian");
            }
        }
    }
}


