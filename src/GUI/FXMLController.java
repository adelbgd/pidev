/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import static java.lang.String.format;
import static java.lang.String.format;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.produit;
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class FXMLController implements Initializable {
    
    produitservice ps = new produitservice();
    @FXML
    private TextField nom_prod;
    @FXML
    private TextField description_prod;
    @FXML
    private TextField statut_prod;
    @FXML
    private TextField valeur_prod;
    @FXML
    private DatePicker date_prod;
    @FXML
    private Button ajouter_produit;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ajouter_produit(ActionEvent event) {
        produit p = new produit();
        if (nom_prod.getText().isEmpty() || statut_prod.getText().isEmpty() || valeur_prod.getText().isEmpty() || description_prod.getText().isEmpty() || date_prod.getValue() == null   ){
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Produit  ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        }else{
        p.setNom(nom_prod.getText());
        p.setDescription(description_prod.getText());
        p.setStatut(statut_prod.getText());
        p.setValeur(Float.parseFloat(valeur_prod.getText()));
        LocalDate date1= date_prod.getValue();
        // p.setDate(date.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
        Date date = format.parse(date1.toString());
        
        p.setDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        
        
        ps.addproduit(p);
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Produit ajouté ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Produit ajouté avec succés !!");
       alert.show();}
        
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/home.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
        
        
        
    }
}
