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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Publication;
import service.Publicationservice;

/**
 * FXML Controller class
 *
 * @author azizl
 */
public class AjouterPublicationController implements Initializable {

    @FXML
    private Button retour_p;
    @FXML
    private Button ajouter_p;
    @FXML
    private TextField titre_pub;
    @FXML
    private TextArea contenu_pub;

    /**
     * Initializes the controller class.
     */
    Publicationservice ps = new Publicationservice();
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image img = new Image("/view/Swappyblack.png");
        image.setImage(img);
        // TODO
    }    
    
     @FXML
    private void retour_p(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MesPublications.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    private void ajouter_p(ActionEvent event) {
        Publication p = new Publication();
        p.setId_user(1);
        p.setTitre_publication(titre_pub.getText());
        p.setContenu_publication(contenu_pub.getText());
        ps.ajouterPublication(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("La publication a été ajouté avec succès");
        alert.showAndWait();
        
    }
}
