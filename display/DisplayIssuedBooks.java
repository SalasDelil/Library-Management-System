package display;

import CodeWithBeki.com.HomeLibrarianPage;
import net.proteanit.sql.DbUtils;
import project.layout.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DisplayIssuedBooks extends HomePage {
    private JTable dispBooks;
    private JScrollPane scroll;
    private JComboBox sortSearch;
    private int days;

    public DisplayIssuedBooks() {
        super();
         try{
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
//        searchTextField.addKeyListener(new KeyAdapter() {
//            public void keyTyped(KeyEvent event){
//        try {
//            Calendar issuedDate = Calendar.getInstance();
//            String date = "" + (issuedDate.get(Calendar.MONTH) + 1) + "-" + (issuedDate.get(Calendar.DATE)) + "-" + (issuedDate.get(Calendar.YEAR));
//            System.out.println(date);
//
//            issuedDate.add(Calendar.DATE, days);
//            String returnDate = "" + (issuedDate.get(Calendar.MONTH) + 1) + "-" + (issuedDate.get(Calendar.DATE)) + "-" + (issuedDate.get(Calendar.YEAR));
//            System.out.println(returnDate);
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "Sitotaye19@");
//            Statement statement = connection.createStatement();
//
//            String query = "INSERT INTO issued_books_table VALUES('"+ bookId +"','"+ studentId +"','"+ Department +"','" +date+"','"+returnDate+"');";
//
//            statement.executeUpdate(query);
//
//            String delete = "delete from Issued_Books where Student_ID='ugr/8538/12';";
//            statement.executeUpdate(delete);
//
//            String update = "update Issued_Books set Student_ID='ugr/4550/12',Book_ID='555h' where Student_ID='beke'";
//            statement.executeUpdate(update);
//            //statement.close();
//        } catch (Exception t) {
//            System.out.println("Error");
//        }
//            }
//        });
        buildAll();
        edditButton();
        add(sortSearch);
        add(scroll);
        setVisible(true);
    }
    //Override method from parent abstract class
    @Override
    public void buildAll() {
        String [] comboList = {"Name", "ISBN no", "Author"};
        sortSearch = new JComboBox(comboList);
        sortSearch.setBounds(895,153,60,33);
        sortSearch.setFocusable(false);
        //Override method from parent class
    }
    @Override
    public void edditButton() {
        pathTextField.setText(">Home Librarian>issued books");
        //searchTextField.setText("Quick search");
        backButton.addActionListener(new ButtonListener());
        homeButton.addActionListener(new ButtonListener());
    }
    //Defining class for button Listener
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent button) {
            if(button.getSource()==backButton){
                new HomeLibrarianPage();
                dispose();
            }
            else if(button.getSource()==homeButton){
                new DisplayIssuedBooks();
                dispose();
            }

        }
    }
}
