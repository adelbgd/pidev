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
import models.Livraison;
import interfaces.LivraisonInterface;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.LivraisonService;
/**
 * FXML Controller class
 *
 * @author adelb
 */
public class Livraison_acceuilController implements Initializable {

    @FXML
    private TextField rechercher_text1;
    @FXML
    private Button rechercher_l;
    @FXML
    private Button ajouter_l0;
    @FXML
    private Button modifier_l0;
    @FXML
    private Button supprimer_l0;
    @FXML
    private Button quitter1;
    @FXML
    private ListView<Livraison> list1;
    
    LivraisonInterface sp= new LivraisonService();
    @FXML
    private ListView<String> list4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the list view with custom cell factory
        /**list1.setCellFactory(param -> new ListCell<Livraison>() {
            @Override
            protected void updateItem(Livraison item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
setText(item.getDate_livraison()+ " - " + item.getLieu_livraison()+ " - " + item.getStatus_livraison()+ " - "  + item.getFrais_livraison());
                }
            }
        });

        // Load the data for the list view
        LivraisonService fs = new LivraisonService();
        List<Livraison> livraisons = fs.fetchLivraisons();
        list1.getItems().addAll(livraisons);**/
        
        list1.setCellFactory(param -> new ListCell<Livraison>() {
    
@Override
protected void updateItem(Livraison item, boolean empty) {
super.updateItem(item, empty);

if (empty || item == null) {
setText(null);
} else {
setText(item.getDate_livraison().toString());
}
}
});

// Load the data for the Commande list view
List<Livraison> livraisons = sp.fetchLivraisons();
list1.getItems().addAll(livraisons);

// Set up the event handler for the formation list view
list1.setOnMouseClicked((MouseEvent event) -> {
if (event.getClickCount­() == 2) {
Livraison selectedLivraison = list1.getSelectionModel().ge­tSelectedItem();


// Create a list of strings to display the details of the selected formation
List<String> details = new ArrayList<>();
details.add("Lieu livraison: " + selectedLivraison.getLieu_livraison());
details.add("Status livraison: " + selectedLivraison.getStatus_livraison());
details.add("Frais livraison: " + selectedLivraison.getFrais_livraison());

//The details list view
  list4.getItems().clear();
                list4.getItems().addAll(details);

// Hide the formation list view
//list1.setVisible(false);
}
}); 
        
    }  
                

    @FXML
    private void rechercher_l(ActionEvent event) {
                  // TODO
    // Récupérer l'ID de la COMMANDE à rechercher depuis le champ de texte
    int idLivraison = Integer.parseInt(rechercher_text1.getText());

    // Appeler le service pour récupérer la COMMANDE correspondant à l'ID
    Livraison livraisonRecherche = (Livraison) sp.rechercherLivraisonbyid(idLivraison);

    if (livraisonRecherche != null) {
        // Afficher l formation trouvé dans la ListView
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList(livraisonRecherche);
        list1.setItems(livraisons);
    } else {
        // Afficher un message d'erreur si la COMMANDE n'a pas été trouvé
        System.out.println("livraison non trouvée");
    }
        
    }

    

    @FXML
    private void ajouter_l0(ActionEvent event) throws IOException {
                // Charger l'interface suivante à partir de son fichier FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("livraison_ajouter.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    
    }

    @FXML
    private void modifier_l0(ActionEvent event) throws IOException {
                // Création d'un nouveau pack avec les valeurs saisies   
          FXMLLoader loader = new FXMLLoader(getClass().getResource("livraison_modifierr.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));
    Livraison selectedLivraison=list1.getSelectionModel().getSelectedItem();
        Livraison_modifierrController Livraison_modifierController=loader.getController();
        Livraison_modifierController.getLivraison(selectedLivraison);
        Livraison_modifierController.l=selectedLivraison;
    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    

    }

    @FXML
    private void supprimer_l0(ActionEvent event) {
         // Récupérer le pack sélectionné
        Livraison livraisonSelectionne = list1.getSelectionModel().getSelectedItem();

    if (livraisonSelectionne != null) {
        // Appeler le service pour supprimer le pack
        sp.deleteLivraison(livraisonSelectionne.getId_livraison());

        // Retirer le pack de la ListView
        list1.getItems().remove(livraisonSelectionne);
    }
    }
    


    @FXML
    private void quitter1(ActionEvent event) {
            // Récupérer le stage actuel et le fermer
    Stage stage = (Stage) quitter1.getScene().getWindow();
    stage.close();
    }

    
    
}
