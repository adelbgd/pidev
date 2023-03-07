/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import models.admin;
import services.adminServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InscriAdminController implements Initializable {

    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpwd;
    @FXML
    private Button cancelbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btns_inscrire(ActionEvent event) {
         admin u = new admin();
        adminServices ss= new adminServices();
        u.setNom(tfnom.getText());
        u.setCin(tfcin.getText());
        u.setEmail(tfemail.getText());
        u.setPrenom(tfprenom.getText());
        u.setPwd(tfpwd.getText());
         if ( tfnom.getText().isEmpty() | tfprenom.getText().isEmpty() |tfpwd.getText().isEmpty() | tfemail.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!tfemail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (tfnom.getText().equals(tfpwd.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {
        ss.ajouter(u);
        JOptionPane.showMessageDialog(null,"Admin added ! ");
         
        
    }
    }

    @FXML
    private void btncancel(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root= loader.load();
        cancelbtn.getScene().setRoot(root);
    }
}
    

