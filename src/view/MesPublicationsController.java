/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Publication;
import service.Publicationservice;

/**
 * FXML Controller class
 *
 * @author azizl
 */
public class MesPublicationsController implements Initializable {

    @FXML
    private Button ajout_c;
    @FXML
    private Button modifier_c;
    @FXML
    private Button supprimer_c;
    @FXML
    private Button retour;
    @FXML
    private ListView<Publication> list_pub;
    
    Publicationservice ps = new Publicationservice();
    @FXML
    private ImageView image;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list_pub.setCellFactory(param -> new ListCell<Publication>() {
            @Override
            protected void updateItem(Publication item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                   setText(item.getTitre_publication()+ " - " + item.getContenu_publication()+ " - " + item.getDate_publication());
                }
            }
        });

        List<Publication> N = null; 
        N = ps.rechercherPublication(1);
        list_pub.getItems().addAll(N);
       
        Image img = new Image("/view/Swappywhite.png");
        image.setImage(img);

    }  
        // TODO
    

    @FXML
    private void ajout_c(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AjouterPublication.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    private void modifier_c(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifierPublication.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userinterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    private void supprimer_c(ActionEvent event) {
         Publication pubSelectionne = list_pub.getSelectionModel().getSelectedItem();

    if (pubSelectionne != null) {
        ps.supprimerPublication(pubSelectionne.getId_pub());
        list_pub.getItems().remove(pubSelectionne);
    }

    }

    
    
}
