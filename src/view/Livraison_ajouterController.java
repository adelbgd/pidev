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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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

    @FXML
    private TextField lieu_l;
    @FXML
    private TextField status_l;
    @FXML
    private TextField mode_l;
    @FXML
    private TextField frais_l;
    @FXML
    private Button retour3;
    @FXML
    private Button ajouter_l;
    @FXML
    private DatePicker text_date_l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        Livraison l = new Livraison();
       
        LocalDate d = text_date_l.getValue(); 
        l.setDate_livraison(java.sql.Date.valueOf(d));     
        l.setLieu_livraison(lieu_l.getText());
        l.setStatus_livraison(status_l.getText());
        l.setMode_livraison(mode_l.getText());
        int frais = Integer.parseInt(frais_l.getText());
        l.setFrais_livraison(frais);
        
           LivraisonService fs= new LivraisonService();
           fs.addLivraison(l);
    }

    }

    