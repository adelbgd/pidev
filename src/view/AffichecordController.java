/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Commande;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author adelb
 */
public class AffichecordController implements Initializable {

    @FXML
    private Button modif1;
    @FXML
    private Label tel;
    @FXML
    private Label email;
    @FXML
    private Label nom;
    
    Commande c;
    CommandeService sp=new CommandeService();
    @FXML
    private Label id;
    @FXML
    private Button cont;
    @FXML
    private Button supp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
        void getcommande(Commande c){
        id.setText(Integer.toString(c.getId_commande()));
        nom.setText(c.getNom_prenom());
        tel.setText(Integer.toString(c.getNum()));
        email.setText(c.getMail());
    
    }
        // TODO

    @FXML
    private void modif1(ActionEvent event) throws IOException {
                                // Charger l'interface suivante à partir de son fichier FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("commande_modifier.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
    
    
    Commande_modifierController ModifierprodController=loader.getController();
    ModifierprodController.getCommande(sp.readbyid(Integer.valueOf(id.getText())));
    
    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    
    


      
    }

    @FXML
    private void cont(ActionEvent event) throws IOException {
                                        // Charger l'interface suivante à partir de son fichier FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("livraison_ajouter.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
        // Show the new stage and hide the current stage
        
    Livraison_ajouterController ModifierprodController=loader.getController();
    ModifierprodController.getcommande(sp.readbyid(Integer.valueOf(id.getText())));
        
        
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
        // Show the new stage and hide the current stage
        
    
     Commande c1;
     c1 =sp.readbyid(Integer.valueOf(id.getText()));
     int b =c1.getId_commande();
       sp.deleteCommande(b); 
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide(); 
        
        
        
        
        
        
        
    }
    
}
