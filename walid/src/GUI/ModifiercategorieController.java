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
public class ModifiercategorieController implements Initializable {

    categorieservice ps = new categorieservice();
    
    @FXML
    private TextField nom_c;
    @FXML
    private TextField id_ca;
    
    @FXML
    private Button modifiercatego;
    @FXML
    private Button retour;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    @FXML
    private void modifiercatego(ActionEvent event) {
        Categorie c = new Categorie();
        c.setNom_catego(nom_c.getText());
        
        c.setId_catego(Integer.parseInt(id_ca.getText()));
       
        ps.updatecategorie(c.getId_catego(), c.getNom_catego());
        
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Categorie modifié ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Categorie modifié avec succés !!");
       alert.show();
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
}
