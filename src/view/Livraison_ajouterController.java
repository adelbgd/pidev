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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class Livraison_ajouterController implements Initializable {

   Livraison l = new Livraison();

    @FXML
    private TextField status_l;
    
     @FXML
    private TextField label;
     
    @FXML
    private Button retour3;
    
    @FXML
    private Button ajouter_l;
    
    @FXML
    private DatePicker text_date_l;

    @FXML
    private Button calculerFraisLivraison;
    @FXML
    private ComboBox<String> combo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> items = FXCollections.observableArrayList("Nord","Milieu", "Sud");

        // Set the items to the ComboBox
        combo.setItems(items);
    }    

    @FXML
    private void retour3(ActionEvent event) throws IOException {
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
    private void ajouter_l(ActionEvent event) {
       
       String selectedOption = (String) combo.getValue();
        if (selectedOption.isEmpty() || status_l.getText().isEmpty() || text_date_l.getValue() == null   ){
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Livraison ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        }else{
       
        LocalDate d = text_date_l.getValue(); 
        l.setDate_livraison(java.sql.Date.valueOf(d));     
        l.setLieu_livraison(selectedOption);
        l.setStatus_livraison(status_l.getText());
       
        //int frais  = Integer.parseInt(label.getText());
        //l.setFrais_livraison(label);
       
       /**int frais1 = 7 ;
        int frais2 = 10 ;
        int frais3 = 15 ;
        
        if (lieu_l.getText().equals("Le Nord De La Tunise")) {
            l.setFrais_livraison((frais1));
        } else if (lieu_l.getText().equals("Le Milieu De La Tunisie")) {
            l.setFrais_livraison((frais2));
        } else if (lieu_l.getText().equals("Le Sud De La Tunise")) {
            l.setFrais_livraison((frais3));
        }**/
        
           LivraisonService fs= new LivraisonService();
           fs.addLivraison(l);
    }
}
    
    @FXML
    private void calculerFraisLivraison() {
        int frais1 = 7 ;
        int frais2 = 10 ;
        int frais3 = 15 ;
        
        if (combo.getValue().equals("Nord")) {
            label.setText(Integer.toString(frais1));
            l.setFrais_livraison(frais1);
        } else if (combo.getValue().equals("Milieu")) {
            label.setText(Integer.toString(frais2));
            l.setFrais_livraison(frais2);
        } else if (combo.getValue().equals("Sud")) {
            label.setText(Integer.toString(frais3));
            l.setFrais_livraison(frais3);
        } 
        
        
    }
}






   

    