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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Commande;
import models.Livraison;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author adelb
 */
public class Commande_ajouterController implements Initializable {

    @FXML
    private TextField produit_c;
    @FXML
    private TextField client_c;
    @FXML
    private DatePicker text_date_c;
    @FXML
    private Button retour1;
    @FXML
    private Button ajouter_c;
    @FXML
    private TextField id_liv_com;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour1(ActionEvent event) throws IOException {
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
    private void ajouter_c(ActionEvent event) {
        Commande c = new Commande();
        if (produit_c.getText().isEmpty() || client_c.getText().isEmpty() || text_date_c.getValue() == null   ){
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Commande ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        }else{
       
        LocalDate d = text_date_c.getValue(); 
        c.setDate_commande(java.sql.Date.valueOf(d));     
        c.setClient(client_c.getText());
        c.setProduit(produit_c.getText());
        Livraison l = new Livraison();
        l.setId_livraison(Integer.parseInt(id_liv_com.getText()));
        c.setId_livraison(l);
        
           CommandeService fs= new CommandeService();
           fs.addCommande(c);
    }
  }  
}
  
