/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    @FXML
    private TextField Username;
    @FXML
    private TextField Password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LogIN(ActionEvent event) {
         String username = Username.getText();
         String password = Password.getText();

    /*if (username.equals("admin") && password.equals("password")) {
        // Login successful
        status.setText("Login successful!");
        // TODO: Do something after successful login
    } else {
        // Login failed
        status.setText("Invalid username or password");
    }*/
    }
    
}
