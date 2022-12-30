package CodeWithBeki.com;
//Importing from project.layout package

import project.layout.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Defining class
public class HomeAdminPage extends HomePage  {
    private JButton booksButton,manageLibrarianButton,manageUserButton, changePasswordButton;
    private JButton issuedBookButton,createOrResetButton;
    //Defining constructor
    public HomeAdminPage(){
         super();//same as default
        //Calling override method
        edditButton();
        //Calling build all method of this class
        buildAll();
        add(booksButton);
        add(manageLibrarianButton);
        add(manageUserButton);
        add(changePasswordButton);
        setVisible(true);

    }
    //Abstract method from the parent class
    @Override
    public void edditButton() {
        pathTextField.setText(">Admin>");//Shows path where you are
        searchTextField.setText("Quick search");
        //Adding listener for back button
        backButton.addActionListener(new ButtonListener());
        //Adding listener for home button
        homeButton.addActionListener(new ButtonListener());
    }
    //Override method to Build all
    @Override
    public void buildAll() {
    Font buttonFont = new Font("monotype corsiva",Font.ITALIC,22);
    //Creating view book button
        booksButton = new JButton("View Books");
        booksButton.setFont(buttonFont);
        booksButton.setOpaque(true);
        booksButton.setBackground(new Color(241, 5, 241));
        booksButton.setFocusable(false);
        booksButton.setBounds(134,190,266,110);
    //Creating add book button
        manageLibrarianButton= new JButton("Manage Librarian");
        manageLibrarianButton.setFont(buttonFont);
        manageLibrarianButton.setBackground(Color.GRAY);
        manageLibrarianButton.setFocusable(false);
        manageLibrarianButton.setBounds(401,190,266,110);
    //Creating return book button
        manageUserButton = new JButton("Manage user");
        manageUserButton .setFont(buttonFont);
        manageUserButton.setBackground(new Color(241, 5, 241));
        manageUserButton.setFocusable(false);
        manageUserButton.setOpaque(true);
        manageUserButton .setBounds(668,190,265,110);
    //creating issue book button
        changePasswordButton = new JButton("Change password");
        changePasswordButton.setOpaque(true);
        changePasswordButton.setBackground(new Color(241, 5, 241));
        changePasswordButton.setFocusable(false);
        changePasswordButton .setFont(buttonFont);
        changePasswordButton.setBounds(134,301,266,110);
    //Creating issued book button
    issuedBookButton = new JButton("Issued Books");
        issuedBookButton.setOpaque(true);
        issuedBookButton.setBackground(new Color(88, 15, 236));
        issuedBookButton.setFocusable(false);
        issuedBookButton.setFont(buttonFont);
        issuedBookButton .setBounds(343,301,207,100);
    //Creating create or reset button
    createOrResetButton = new JButton("Create/Reset");
        createOrResetButton.setOpaque(true);
        createOrResetButton.setBackground(new Color(241, 5, 241));
        createOrResetButton.setFocusable(false);
        createOrResetButton.setFont(buttonFont);
        createOrResetButton .setBounds(551,301,207,100);
}
        //Defining inner class for action events
      private class ButtonListener implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent e) {
              if (e.getSource() == backButton) {
                  new AdminLoginPage("Admin Login Page");
                  dispose();
              } else if (e.getSource() == homeButton) {
                  new HomeAdminPage();
                  dispose();
              }
          }
      }
}
