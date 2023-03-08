/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class AfficherController implements Initializable {

    @FXML
    private Button modif1;
    @FXML
    private Button modif2;
    @FXML
    private Label tel;
    @FXML
    private Label email;
    @FXML
    private Label nom;
    @FXML
    private Button homepage;
    @FXML
    private Label id;
    Livraison l;
    Commande c;
    @FXML
    private Label region;
    @FXML
    private Label ville;
    @FXML
    private Label comp;
    @FXML
    private Label status;
    @FXML
    private Label frais;
    /**
     * Initializes the controller class.
     */
    CommandeService sp= new CommandeService();
    LivraisonService sp1= new LivraisonService();
    public int c1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**void getCommande(Commande c){
        
    id.setText(String.valueOf(c.getId_commande()));
    nom.setText(c.getNom_prenom());
    tel.setText(Integer.toString(c.getNum()));
    email.setText(c.getMail());
    }**/
     void getcommande(Commande c){
        //id.setText(Integer.toString(c.getId_commande()));
        nom.setText(c.getNom_prenom());
        tel.setText(Integer.toString(c.getNum()));
        email.setText(c.getMail());
       //int c1= c.getId_commande();
       c.getId_commande();
    }
        void getlivraison(Livraison l){
    region.setText(l.getLieu_livraison());
    ville.setText(l.getVille());
    comp.setText(l.getComp());
    status.setText(l.getStatus_livraison());
    frais.setText(Double.toString(l.getFrais_livraison()));
        
      
    }
    void getall(Livraison l,Commande c){
        
         nom.setText(c.getNom_prenom());
        tel.setText(Integer.toString(c.getNum()));
      email.setText(c.getMail());
       region.setText(l.getLieu_livraison());
      ville.setText(l.getVille());
      comp.setText(l.getComp());
     status.setText(l.getStatus_livraison());
     frais.setText(Double.toString(l.getFrais_livraison()));
        
    }
    @FXML
    private void modif1(ActionEvent event) throws IOException {
                        // Charger l'interface suivante à partir de son fichier FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("commande_modifier.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
    
    Commande selectedprod = (Commande) sp.getcommande(c);
    Commande_modifierController ModifierprodController=loader.getController();
    ModifierprodController.getCommande(selectedprod);
    ModifierprodController.c=selectedprod;
    
    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    
    
   
    }

    @FXML
    private void modif2(ActionEvent event) throws IOException {
                        // Charger l'interface suivante à partir de son fichier FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Livraison_modifierr.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
    
    Livraison selectedprod1 = (Livraison) sp1.getlivraison(l);
    Livraison_modifierrController ModifierprodController=loader.getController();
    ModifierprodController.getLivraison(l);
    ModifierprodController.l=selectedprod1;
    
    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }

    @FXML
    private void homepage(ActionEvent event) {
    }

    
    
}
