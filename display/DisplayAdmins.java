package display;

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

public class DisplayAdmins extends HomePage {
    private JTable dispBooks;
    private JComboBox sortSearch;
    private JScrollPane scroll;
    private JButton addAdminButton, removeAdminButton,updateAdminButton;
    DefaultTableModel model;
    public DisplayAdmins() {
        super();
        //................
        try {
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
            Statement statment = connection1.createStatement();
            ResultSet result = statment.executeQuery("Select * from admin_table");
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
                        ResultSet result1 = statement.executeQuery("Select * from admin_table;");
                        dispBooks = new JTable();
                        dispBooks.setModel(DbUtils.resultSetToTableModel(result1));
                        scroll = new JScrollPane(dispBooks);
                        scroll.setBounds(135, 190, 815, 415);
                        add(scroll);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Search field not working");
                }
            }
        });
        //................

        buildAll();
        edditButton();
        add(sortSearch);
        add(scroll);
        add(addAdminButton);
        add(removeAdminButton);
        add(updateAdminButton);
        add(leftSidePanel2);
        setVisible(true);
    }
    //Override method from parent class
    @Override
    public void edditButton() {
        pathTextField.setText(">Admin>manage admins");
        //searchTextField.setText("Search books");
        backButton.addActionListener(new ButtonListener());
        homeButton.addActionListener(new ButtonListener());
    }
    //Override method from parent class
    @Override
    public void buildAll() {
        String[] comboList = {"Name", "username"};
        sortSearch = new JComboBox(comboList);
        sortSearch.setBounds(895, 153, 60, 33);
        sortSearch.setFocusable(false);
        addAdminButton = new JButton("Add");
        addAdminButton.setBounds(32,190,96,50);
        addAdminButton.setOpaque(true);
        addAdminButton.setBackground(new Color(182, 35, 215));
        addAdminButton.setFocusable(false);
        addAdminButton.addActionListener(new ButtonListener());
        //
        removeAdminButton = new JButton("Remove");
        removeAdminButton.setBounds(32,245,96,50);
        removeAdminButton.setOpaque(true);
        removeAdminButton.setBackground(new Color(44, 46, 217));
        removeAdminButton.setFocusable(false);
        removeAdminButton.addActionListener(new ButtonListener());
        //
        updateAdminButton = new JButton("Update");
        updateAdminButton.setBounds(32,300,96,50);
        updateAdminButton.setOpaque(true);
        updateAdminButton.setBackground(new Color(44, 46, 217));
        updateAdminButton.setFocusable(false);
    }
    //Defining private class for button listener
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent button) {
            if (button.getSource() == backButton) {
                new HomeAdminPage();
                dispose();
            } else if (button.getSource() == homeButton) {
                new DisplayAdmins();
                dispose();
            }
            else if(button.getSource()==addAdminButton){
                new AddAdmin("Add Admin");
            }
            else if(button.getSource()==removeAdminButton){
                 new RemoveAdmin("Remove Admin");
            }
        }
    }
}
