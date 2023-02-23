/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.produitinterface;
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
import models.produit;
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class HomeController implements Initializable {
    produitinterface sp = new produitservice();
    @FXML
    private Button ajout;
    @FXML
    private Button modifie;
    @FXML
    private Button supprime;
    @FXML
    private ListView<produit> list;
    @FXML
    private TextField recherche_text;
    @FXML
    private Button rechercher_p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.setCellFactory(param -> new ListCell<produit>() {
            @Override
            protected void updateItem(produit item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
setText(item.getNom()+ " - " + item.getDescription()+ " - " + item.getStatut()+" - "+item.getValeur()+" - "+item.getDate());
                }
            }
        });

        // Load the data for the list view
        produitservice fs = new produitservice();
        List<produit> produits = fs.fetchProduits();
        list.getItems().addAll(produits);  
    }
    
    @FXML
    private void rechercher_p(ActionEvent event) {
          // TODO
    // Récupérer l'ID de la COMMANDE à rechercher depuis le champ de texte
    int idproduit = Integer.parseInt(recherche_text.getText());

    // Appeler le service pour récupérer la COMMANDE correspondant à l'ID
    produit produitRecherche = (produit) sp.rechercherbyIdproduit(idproduit);

    if (produitRecherche != null && produitRecherche.getValeur()!=0  ) {
        // Afficher l formation trouvé dans la ListView
        ObservableList<produit> produits = FXCollections.observableArrayList(produitRecherche);
        list.setItems(produits);
    } else {
        // Afficher un message d'erreur si la COMMANDE n'a pas été trouvé
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Produits ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Produit non trouvé !!");
       alert.show();
    }
        
    }

    
    @FXML
    private void ajout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ajoutproduit.fxml"));

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/modifierprod.fxml"));

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
        produit produitSelectionne = list.getSelectionModel().getSelectedItem();

    if (produitSelectionne != null) {
        // Appeler le service pour supprimer le pack
        sp.deletebyIdproduit(produitSelectionne.getId_prod());

        // Retirer le pack de la ListView
        list.getItems().remove(produitSelectionne);
    }
    }
    
    
}
