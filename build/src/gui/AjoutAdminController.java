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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.admin;
import services.adminServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutAdminController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Button retour;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelCin;
    @FXML
    private TextField TfCin;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPwd;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPwd;
    @FXML
    private Button Ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdmin.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }
      @FXML
    private void btnajouter(ActionEvent event) {
         admin u = new admin();
        adminServices ss= new adminServices();
        u.setNom(tfNom.getText());
        u.setCin(TfCin.getText());
        u.setEmail(tfEmail.getText());
        u.setPrenom(tfPrenom.getText());
        u.setPwd(tfPwd.getText());
         if ( tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty() |tfPwd.getText().isEmpty() | tfEmail.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!tfEmail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (tfNom.getText().equals(tfPwd.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {
        ss.ajouter(u);
        JOptionPane.showMessageDialog(null,"User added ! ");
         List<admin> la = ss.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);

       /* ColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("pwd"));
      
        TableViewAdmin.setItems(AdminList);
                }*/
    }
        
               
    
}
}
    
        
    

  


