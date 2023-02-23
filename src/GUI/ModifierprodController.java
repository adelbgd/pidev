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
public class ModifierprodController implements Initializable {

    produitservice ps = new produitservice();
    
    @FXML
    private TextField nom_p;
    @FXML
    private TextField description_p;
    @FXML
    private TextField statut_p;
    @FXML
    private TextField valeur_p;
    @FXML
    private DatePicker date_p;
    @FXML
    private TextField id_pr;
    @FXML
    private Button modifierproduit;
    @FXML
    private Button retour;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
      /**@FXML
    private void modifierproduit(ActionEvent event) {
        
        
        String nom = nom_prod.getText();
        String description = description_prod.getText();
        String statut= statut_prod.getText();
        Float valeur = Float.parseFloat(valeur_prod.getText());
        Integer id = Integer.parseInt(id_prod.getText());
        LocalDate date1= date_prod.getValue();
        // p.setDate(date.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
        Date date = format.parse(date1.toString());
        
        ps.updateproduit(id, nom, description, statut, valeur, date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
    }**/
    @FXML
    private void modifierproduit(ActionEvent event) {
        produit p = new produit();
        if (nom_p.getText().isEmpty() || statut_p.getText().isEmpty() || valeur_p.getText().isEmpty() || id_pr.getText().isEmpty() || date_p.getValue() == null  || description_p.getText().isEmpty() ) {
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Produit  ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        } 
        
        else{
        
        p.setNom(nom_p.getText());
        p.setDescription(description_p.getText());
        p.setStatut(statut_p.getText());
        p.setValeur(Float.parseFloat(valeur_p.getText()));
        p.setId_prod(Integer.parseInt(id_pr.getText()));
        LocalDate date1= date_p.getValue();
        // p.setDate(date.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
        Date date = format.parse(date1.toString());
        
        p.setDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        ps.updateproduit(p.getId_prod(), p.getNom(), p.getDescription(), p.getStatut(), p.getValeur(), p.getDate());
        
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Produit modifié ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Produit modifié avec succés !!");
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
