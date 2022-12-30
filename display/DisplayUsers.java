package display;//Package for given class

import CodeWithBeki.com.HomeAdminPage;
import net.proteanit.sql.DbUtils;
import project.layout.HomePage;
import Action.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class DisplayUsers extends HomePage {
    private JTable dispBooks;
    private JComboBox sortSearch;
    private JScrollPane scroll;
    private JButton addUserButton, removeUserButton,updateUserButton;
    DefaultTableModel model;
    public DisplayUsers() {
        super();
        //................
        try {
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
            Statement statment = connection1.createStatement();
            ResultSet result = statment.executeQuery("Select * from student_table");
            dispBooks = new JTable();
            dispBooks.setModel(DbUtils.resultSetToTableModel(result));
            scroll = new JScrollPane(dispBooks);
            scroll.setBounds(135, 190, 815, 415);
        } catch (SQLException e) {
            System.out.println("NO!");
        }
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
                    String query = "Select * from student_table where Name like '%+" + searchTextField.getText() + "%'or Id_Number like '%"+searchTextField.getText()+"%';";
                    Statement statement = connection.createStatement();

                    if (searchTextField.getText() != null) {
                        ResultSet result1 = statement.executeQuery(query);
                        dispBooks = new JTable();
                        dispBooks.setModel(DbUtils.resultSetToTableModel(result1));
                        scroll = new JScrollPane(dispBooks);
                        scroll.setBounds(135, 190, 815, 415);
                        add(scroll);
                    } else {
                        ResultSet result1 = statement.executeQuery("Select * from student_table;");
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
        //................

        buildAll();
        edditButton();
        add(sortSearch);
        add(scroll);
        add(addUserButton);
        add(removeUserButton);
        add(updateUserButton);
        add(leftSidePanel2);
        setVisible(true);
    }
    //Override method from parent class
    @Override
    public void edditButton() {
        pathTextField.setText(">Admin>manage users");
        //searchTextField.setText("Search books");
        backButton.addActionListener(new ButtonListener());
        homeButton.addActionListener(new ButtonListener());
    }
    //Override method from parent class
    @Override
    public void buildAll() {
        String[] comboList = {"Name", "ID_Number"};
        sortSearch = new JComboBox(comboList);
        sortSearch.setBounds(895, 153, 60, 33);
        sortSearch.setFocusable(false);
        addUserButton = new JButton("Add");
        addUserButton.setBounds(32,190,96,50);
        addUserButton.setOpaque(true);
        addUserButton.setBackground(new Color(182, 35, 215));
        addUserButton.setFocusable(false);
        addUserButton.addActionListener(new ButtonListener());
        //
        removeUserButton = new JButton("Remove");
        removeUserButton.setBounds(32,245,96,50);
        removeUserButton.setOpaque(true);
        removeUserButton.setBackground(new Color(44, 46, 217));
        removeUserButton.setFocusable(false);
        removeUserButton.addActionListener(new ButtonListener());
        //
        updateUserButton = new JButton("Update");
        updateUserButton.setBounds(32,300,96,50);
        updateUserButton.setOpaque(true);
        updateUserButton.setBackground(new Color(44, 46, 217));
        updateUserButton.setFocusable(false);
    }
    //Defining private class for button listener
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent button) {
            if (button.getSource() == backButton) {
                new HomeAdminPage();
                dispose();
            } else if (button.getSource() == homeButton) {
                new DisplayUsers();
                dispose();
            }
            else if(button.getSource()==addUserButton){
                new AddUser("Add user");
            }
            else if(button.getSource()==removeUserButton){
                  new RemoveUser("Remove User");
            }
        }
    }
}
