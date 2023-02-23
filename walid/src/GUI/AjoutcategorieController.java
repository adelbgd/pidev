/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Categorie;
import models.produit;
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class AjoutcategorieController implements Initializable {
    
    categorieservice ps = new categorieservice();
    
    @FXML
    private TextField nom_catego;
    
    @FXML
    private Button ajouter_catego;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Homecategorie.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
        
        
        
    }
    
    
    @FXML
    private void ajouter_catego(ActionEvent event) {
        Categorie c = new Categorie();
        c.setNom_catego(nom_catego.getText());
        
        ps.addcategorie(c);
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Categorie ajouté ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Categorie ajouté avec succés !!");
       alert.show();
    }
}
