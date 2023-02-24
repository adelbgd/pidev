/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.adminServices;
import services.clientServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnInscription;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnInscriptionAction(ActionEvent event) {
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/gui/Ajout.fxml"
                    ));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @FXML
    private void btnLoginAction(ActionEvent event) {
        String email = tfemail.getText();
        String password = passwordField.getText();

        String cin_admin = "";
        int id_client = -1;
        adminServices as = new adminServices();
        clientServices cs = new clientServices();
        Alert A = new Alert(Alert.AlertType.INFORMATION);
        cin_admin = as.authentification(email, password);

        if (cin_admin != "") {
            A.setContentText("Admin connecté !");
            A.show();
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/gui/GestionAdmin.fxml" ));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
        } else {
            id_client = cs.authentification(email, password);

            if (id_client != -1) {
                A.setContentText("Client connecté ! ");
                A.show();
            } else {
                A.setAlertType(Alert.AlertType.ERROR);
                A.setContentText("email ou password errorné ! ");
                A.show();
            }
        }

    }
}
