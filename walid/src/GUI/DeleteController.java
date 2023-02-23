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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.produit;
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class DeleteController implements Initializable {
    
    produitservice ps = new produitservice();
    @FXML
    private TextField id_p;
    @FXML
    private Button supprimer_prod;
    @FXML
    private Button retour1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void supprimer_prod(ActionEvent event) {
       produit p = new produit();
        
        p.setId_prod(Integer.parseInt(id_p.getText()));
        ps.deletebyIdproduit(p.getId_prod());
       
       Alert alert = new Alert (AlertType.INFORMATION);
       alert.setTitle("Produit supprimé ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Produit supprimé avec succés !!");
       alert.show();
    }
    @FXML
    private void retour1(ActionEvent event) throws IOException {
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
