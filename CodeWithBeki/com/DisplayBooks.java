package CodeWithBeki.com;

import net.proteanit.sql.DbUtils;
import project.layout.HomePage;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class DisplayBooks extends HomePage {
    private JTable dispBooks;
    private JComboBox sortSearch;
    private JScrollPane scroll;
    public DisplayBooks(){
     super();

       /* try {
            Connection   connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
            Statement statment = connection1.createStatement();
            ResultSet result = statment.executeQuery("Select * from student_table");
             dispBooks = new JTable();
             dispBooks.setModel(DbUtils.resultSetToTableModel(result));
             scroll = new JScrollPane(dispBooks);
            scroll.setBounds(135,190,815,415);
        } catch (SQLException e) {
            System.out.println("NO!");
        }*/

     searchTextField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
             super.keyTyped(e);
             try{
                 Connection   connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/system_users", "root", "Sitotaye19@");
                 String selected = (String)sortSearch.getSelectedItem();
                 String query = "Select * from student_table where "+ selected + "=?;";
                 PreparedStatement statement = connection1.prepareStatement(query);
                 statement.setString(1,searchTextField.getText());
                 ResultSet result = statement.executeQuery();
                 dispBooks.setModel(DbUtils.resultSetToTableModel(result));
                 //statement.close();
             }catch(Exception t){
                 System.out.println("Error");
             }
         }
     });
        scroll = new JScrollPane(dispBooks);
        scroll.setBounds(135,190,815,415);
        buildAll();
        add(sortSearch);
        add(scroll);
        setVisible(true);
    }
    public static void main(String []args){

        new DisplayBooks();
    }

    @Override
    public void edditButton() {
        pathTextField.setText(">Librarian>View books");
        //searchTextField.setText("Search books");
    }

    @Override
    public void buildAll() {
        String [] comboList = {"Name","ID_Number"};
        sortSearch = new JComboBox(comboList);
        sortSearch.setBounds(895,153,60,33);
        sortSearch.setFocusable(false);
    }

}
