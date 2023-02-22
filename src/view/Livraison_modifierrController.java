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
import models.Livraison;
import services.CommandeService;
import services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author adelb
 */
public class Livraison_modifierrController implements Initializable {

    @FXML
    private TextField modif_lieu;
    @FXML
    private TextField modif_status;
    @FXML
    private TextField modif_mode;
    @FXML
    private TextField modif_frais;
    @FXML
    private DatePicker modif_date;
    @FXML
    private Button retour4;
    @FXML
    private Button modifier_l;

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private ListView<Livraison> list1;
     Livraison l = new Livraison();
     LivraisonService sp = new LivraisonService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }    
    void getLivraison(Livraison l){
        LocalDate d = modif_date.getValue(); 
// c.set(java.sql.Date.valueOf(d));
    modif_frais.setText(Integer.toString(l.getFrais_livraison())); 
    modif_mode.setText(l.getMode_livraison());
    modif_status.setText(l.getStatus_livraison());
    modif_lieu.setText(l.getLieu_livraison());
}

    @FXML
    private void retour4(ActionEvent event) throws IOException {
                // Charger l'interface suivante à partir de son fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("livraison_acceuil.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }
    
    

    @FXML
    private void modifier_l(ActionEvent event) {
          LocalDate k = modif_date.getValue(); 
       
         String lieu = modif_lieu.getText();
         String status = modif_status.getText();
         String mode = modif_mode.getText();
         String frais = modif_frais.getText();
         
         // Vérifier que les champs ne sont pas vides
    if (lieu.isEmpty() || status.isEmpty() || mode.isEmpty()  || frais.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }
    
    // Vérifier que le nombre d etapes est un nombre valide
    int Frais;
        try {
Frais = Integer.parseInt(frais);
        if (Frais <= 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le frais doit être supérieur à zéro.");
            alert.showAndWait();
            return;
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le frais doit être un nombre");
        alert.showAndWait();
        return;
    }
       
        LocalDate d = modif_date.getValue(); 
        l.setDate_livraison(java.sql.Date.valueOf(d));
        l.setLieu_livraison(lieu);
        l.setStatus_livraison(status);
        l.setMode_livraison(mode);
        l.setFrais_livraison(Frais);
        
           sp.updateLivraison(l);
           
           // Afficher un message de confirmation
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("La livraison a été modifié avec succès");
    alert.showAndWait();
    }
    
}
