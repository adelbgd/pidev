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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Commande;
import models.Livraison;
import services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author adelb
 */
public class AffichelivController implements Initializable {

    @FXML
    private Button modif2;
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
     
     Livraison l;
     LivraisonService sp=new LivraisonService();
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
        // TODO
    }    
         void getlivraison(Livraison l){
        
        id.setText(Integer.toString(l.getId_livraison()));
        region.setText(l.getLieu_livraison());
        ville.setText(l.getVille());
        comp.setText(l.getComp());
        status.setText(l.getStatus_livraison());
        frais.setText(Float.toString(l.getFrais_livraison()));
    }
    @FXML
    private void modif2(ActionEvent event) throws IOException {
        
                                        // Charger l'interface suivante à partir de son fichier FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("livraison_modifierr.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
    
    
    Livraison_modifierrController ModifierprodController=loader.getController();
    ModifierprodController.getLivraison(sp.readbyid(Integer.valueOf(id.getText())));
    
    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
        
        
        
    }

    @FXML
    private void cont(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
        // Show the new stage and hide the current stage
        
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
        
    
     Livraison l1;
     l1 =sp.readbyid(Integer.valueOf(id.getText()));
     int b =l1.getId_livraison();
       sp.deleteLivraison(b); 
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide(); 
        
    }
    
}
