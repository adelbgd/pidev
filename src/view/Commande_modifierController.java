/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Commande;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author adelb
 */
public class Commande_modifierController implements Initializable {

    @FXML
    private TextField modif_client;
    @FXML
    private TextField modif_produit;
    @FXML
    private DatePicker modif_date_c;
    @FXML
    private Button retour2;
    @FXML
    private Button modifier_c;

     Commande c = new Commande();
     CommandeService sp = new CommandeService();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    void getCommande(Commande c){
        LocalDate d = modif_date_c.getValue(); 
       
  //c.setDate(java.sql.Date.valueOf(d));
    modif_client.setText(c.getClient()); 
    modif_produit.setText(c.getProduit());
}
    
    @FXML
    private void retour2(ActionEvent event) throws IOException {
        // Charger l'interface suivante à partir de son fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commande_acceuil.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }
    

    @FXML
    private void modifier_c(ActionEvent event) {
        
        LocalDate k = modif_date_c.getValue(); 
       
         String produit = modif_produit.getText();
         String type = modif_client.getText();
         
         
          // Vérifier que les champs ne sont pas vides
    if (produit.isEmpty() || type.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }
         
         
         
         
         
         
         
       
        LocalDate d = modif_date_c.getValue(); 
        c.setDate_commande(java.sql.Date.valueOf(d));
        c.setClient(modif_client.getText());
        c.setProduit(modif_produit.getText());
       
        
      
           sp.updateCommande(c);
           
           // Afficher un message de confirmation
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("La commande a été modifié avec succès");
    alert.showAndWait();
    }
        
    
    
}
