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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizl el boss
 */
public class UserinterfaceController implements Initializable {

    @FXML
    private Button ajouter_p;
    @FXML
    private Button affiche_p;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/view/Swappyblack.png");
        image.setImage(img);
        // TODO
    }    

    @FXML
    private void affiche_p(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConsulterPublicationUser.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    private void ajouter_p(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MesPublications.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }
    
}
