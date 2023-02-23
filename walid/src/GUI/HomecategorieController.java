/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.categorieinterface;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Categorie;
import models.produit;
import services.categorieservice;
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class HomecategorieController implements Initializable {
    
    categorieinterface sp = new categorieservice();
    @FXML
    private Button ajout;
    @FXML
    private Button modifie;
    @FXML
    private Button supprime;
    @FXML
    private ListView<Categorie> list;
    @FXML
    private TextField recherche_text;
    @FXML
    private Button rechercher_c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.setCellFactory(param -> new ListCell<Categorie>() {
            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
setText(item.getNom_catego());
                }
            }
        });

        // Load the data for the list view
        categorieservice fs = new categorieservice();
        List<Categorie> categories = fs.fetchcategories();
        list.getItems().addAll(categories);  
    }
     

    
@FXML
    private void rechercher_c(ActionEvent event) {
          // TODO
    // Récupérer l'ID de la COMMANDE à rechercher depuis le champ de texte
    int idcategorie = Integer.parseInt(recherche_text.getText());

    // Appeler le service pour récupérer la COMMANDE correspondant à l'ID
    Categorie categorieRecherche = (Categorie) sp.rechercherbyIdcategorie(idcategorie);

    if (categorieRecherche != null   ) {
        // Afficher l formation trouvé dans la ListView
        ObservableList<Categorie> categories = FXCollections.observableArrayList(categorieRecherche);
        list.setItems(categories);
    } else {
        // Afficher un message d'erreur si la COMMANDE n'a pas été trouvé
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Categorie ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Categorie non trouvé !!");
       alert.show();
    }
    }
    
    @FXML
    private void ajout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ajoutcategorie.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
        
        
        
    }
    
    @FXML
    private void modifie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/modifiercategorie.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
        
        
        
    }
    
     @FXML
    private void supprime(ActionEvent event) {
        // Récupérer le pack sélectionné
        Categorie categorieSelectionne = list.getSelectionModel().getSelectedItem();

    if (categorieSelectionne != null) {
        // Appeler le service pour supprimer le pack
        sp.deletebyIdcategorie(categorieSelectionne.getId_catego());

        // Retirer le pack de la ListView
        list.getItems().remove(categorieSelectionne);
    }
    }
    
    
}
        
    

