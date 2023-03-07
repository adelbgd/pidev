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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.admin;
import services.adminServices;
import services.clientServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifAdminController implements Initializable {

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
    private Button modifierbt;
    private adminServices ad = new adminServices();
    admin a ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        a = GestionAdminController.selectedAdmin;
        TfCin.setText(a.getCin());
        tfEmail.setText(a.getEmail());
        tfNom.setText(a.getNom());
        tfPrenom.setText(a.getPrenom());
        tfPwd.setText(a.getPwd());
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
    private void btnmodifier(ActionEvent event) {
        /*adminServices ss = new adminServices();
    admin selectedAdmin = TableViewAdmin.getSelectionModel().getSelectedItem();

    if (selectedAdmin == null) {
        // If no admin is selected, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Veuillez sélectionner un administrateur !");
        al.showAndWait();
    } else if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPwd.getText().isEmpty()) {
        // If any of the text fields are empty, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Veuillez remplir tous les champs !");
        al.showAndWait();
    } else*/ if (!tfEmail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                    + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")) {
        // If the email address is not valid, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Veuillez entrer une adresse e-mail valide !");
        al.showAndWait();
    } else if (tfNom.getText().equals(tfPwd.getText())) {
        // If the username and password are the same, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Le nom d'utilisateur et le mot de passe ne peuvent pas être identiques !");
        al.showAndWait();
    } else {
        // Modify the selected admin record
        a.setCin(TfCin.getText());
        a.setNom(tfNom.getText());
        a.setPrenom(tfPrenom.getText());
        a.setEmail(tfEmail.getText());
        a.setPwd(tfPwd.getText());
        ad.modifier(a.getCin(),a.getNom(),a.getPrenom(),a.getEmail(),a.getPwd());

        // Display a success message
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setHeaderText(null);
        al.setContentText("L'administrateur a été modifié avec succès !");
        al.showAndWait();

        // Refresh the TableView with the updated data
        List<admin> la = ad.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);

        
    }
    
}
}
