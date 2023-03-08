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
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class Commande_modifierController implements Initializable {

    @FXML
    private TextField modif_client;
    @FXML
    private TextField modif_produit;
    private DatePicker modif_date_c;
    @FXML
    private Button retour2;
    @FXML
    private Button modifier_c;

     Commande c ;
     
     CommandeService sp = new CommandeService();
    @FXML
    private TextField modif_mail;
    @FXML
    private Label idCom;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private boolean isEmailValid(String email) {
    String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                        "[a-zA-Z0-9_+&*-]+)*@" + 
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                        "A-Z]{2,7}$";
    return email.matches(pattern);
}
    Pattern pattern = Pattern.compile("^\\d{8}$");
    void getCommande(Commande c){
        //LocalDate d = modif_date_c.getValue(); 
       
  //c.setDate(java.sql.Date.valueOf(d));
    idCom.setText(Integer.toString(c.getId_commande()));
    modif_client.setText(c.getNom_prenom()); 
    modif_produit.setText(Integer.toString(c.getNum()));
    modif_mail.setText(c.getMail()); 
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
    private void modifier_c(ActionEvent event) throws IOException {
        
        
        
        
        
                
        
        //LocalDate k = modif_date_c.getValue(); 
       
         String produit = modif_produit.getText();
         String type = modif_client.getText();
         String mail =modif_mail.getText();
         
     if (modif_client.getText().isEmpty() ){
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Commande ");
        alert.setHeaderText("ATTENTION !!");
        alert.setContentText("Veuillez remplir toutes les cases !!");
        alert.show();
    } else if (!isEmailValid(modif_mail.getText())) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Commande ");
        alert.setHeaderText("Adresse e-mail invalide");
        alert.setContentText("Veuillez saisir une adresse e-mail valide.");
        alert.show();
    } 
    else if (!pattern.matcher(modif_produit.getText()).matches()) {
    Alert alert = new Alert (Alert.AlertType.INFORMATION);
    alert.setTitle("Commande ");
    alert.setHeaderText("Numero invalide");
    alert.setContentText("Veuillez saisir un numero contenant 8 entiers exactement.");
    alert.show();

    }
     
     
     else {
        
        // Charger l'interface suivante à partir de son fichier FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("livraison_ajouter.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
    
    
  
    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();

        
        
        
        Commande c1 = sp.readbyid(Integer.valueOf(idCom.getText()));
        c1.setId_commande(Integer.valueOf(idCom.getText()));
        c1.setNom_prenom(modif_client.getText());
        c1.setNum(Integer.parseInt(modif_produit.getText()));
        c1.setMail(modif_mail.getText());
        
        
         
         
           sp.updateCommande(c1);
           
          /** AffichecordController a = loader.getController();
           a.getcommande(c); 
           a.c=c;**/
  
     
    }
       
    
}
}
       