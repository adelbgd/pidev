/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Commande;
import interfaces.CommandeInterface;
import java.util.ArrayList;
import javafx.scene.control.Label;
import services.CommandeService;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author adelb
 */
public class Commande_acceuilController implements Initializable {

    @FXML
    private TextField recherche_text;
    @FXML
    private Button rechercher_c;
    @FXML
    private ListView<Commande> list;
    @FXML
    private Button supprimer_c;
    @FXML
    private Button quitter;
    @FXML
    private Button ajouter_c0;
    @FXML
    private Button modifier_c0;
    @FXML
    private ListView<String> list2;
    
    CommandeInterface sp= new CommandeService();
    private Button quitter1;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the list view with custom cell factory
        /*list.setCellFactory(param -> new ListCell<Commande>() {
            @Override
            protected void updateItem(Commande item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
setText(item.getDate_commande()+ " - " + item.getClient()+ " - " + item.getProduit());
                }
            }
        });

        // Load the data for the list view
        CommandeService fs = new CommandeService();
        List<Commande> commandes = fs.fetchCommandes();
        list.getItems().addAll(commandes);  */
        
        // Set up the cell factory for the formation list view
list.setCellFactory(param -> new ListCell<Commande>() {
    
@Override
protected void updateItem(Commande item, boolean empty) {
super.updateItem(item, empty);

if (empty || item == null) {
setText(null);
} else {
setText(item.getNom_prenom());
}
}
});

// Load the data for the Commande list view
List<Commande> commandes = sp.fetchCommandes();
list.getItems().addAll(commandes);

// Set up the event handler for the formation list view
list.setOnMouseClicked((MouseEvent event) -> {
if (event.getClickCount??() == 2) {
Commande selectedCommande = list.getSelectionModel().ge??tSelectedItem();


// Create a list of strings to display the details of the selected formation
List<String> details = new ArrayList<>();
details.add("Date commande: " + selectedCommande.getDate_commande());
details.add("num: " + selectedCommande.getNum());


//The details list view
  list2.getItems().clear();
                list2.getItems().addAll(details);

// Hide the formation list view
//list.setVisible(false);
}
});
}
 
                

    @FXML
    private void rechercher_c(ActionEvent event) {
          // TODO
    // R??cup??rer l'ID de la COMMANDE ?? rechercher depuis le champ de texte
    int idCommande = Integer.parseInt(recherche_text.getText());

    // Appeler le service pour r??cup??rer la COMMANDE correspondant ?? l'ID
    Commande commandeRecherche = (Commande) sp.rechercherCommandebyid(idCommande);

    if (commandeRecherche != null) {
        // Afficher l formation trouv?? dans la ListView
        ObservableList<Commande> commandes = FXCollections.observableArrayList(commandeRecherche);
        list.setItems(commandes);
    } else {
        // Afficher un message d'erreur si la COMMANDE n'a pas ??t?? trouv??
        System.out.println("commande non trouv??e");
    }
        
    }


    @FXML
    private void supprimer_c(ActionEvent event) {
        // R??cup??rer le pack s??lectionn??
        Commande commandeSelectionne = list.getSelectionModel().getSelectedItem();

    if (commandeSelectionne != null) {
        // Appeler le service pour supprimer le pack
        sp.deleteCommande(commandeSelectionne.getId_commande());

        // Retirer le pack de la ListView
        list.getItems().remove(commandeSelectionne);
    }
    }


    @FXML
    private void quitter(ActionEvent event) {
        // R??cup??rer le stage actuel et le fermer
    Stage stage = (Stage) quitter.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void ajouter_c0(ActionEvent event) throws IOException {
        // Charger l'interface suivante ?? partir de son fichier FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("commande_ajouter.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }

    @FXML
    private void modifier_c0(ActionEvent event) throws IOException {
        // Cr??ation d'un nouveau pack avec les valeurs saisies   
          FXMLLoader loader = new FXMLLoader(getClass().getResource("commande_modifier.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
    Commande selectedCommande=list.getSelectionModel().getSelectedItem();
        Commande_modifierController Commande_modifierController=loader.getController();
        Commande_modifierController.getCommande(selectedCommande);
        Commande_modifierController.c=selectedCommande;
    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }

    
}
