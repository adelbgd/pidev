 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Publication;
import service.Publicationservice;

/**
 * FXML Controller class
 *
 * @author azizl
 */
public class ModifierPublicationController implements Initializable {

    @FXML
    private Button retour_p;
    @FXML
    private Button modifier_p;
    @FXML
    private TextField titre_pub;
    @FXML
    private TextArea contenu_pub;
    @FXML
    private ListView<Publication> list_pub;

    /**
     * Initializes the controller class.
     */
    Publicationservice ps = new Publicationservice();
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list_pub.setCellFactory(param -> new ListCell<Publication>() {
            @Override
            protected void updateItem(Publication item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitre_publication() + " - " + item.getContenu_publication() + " - " + item.getDate_publication());
                }
            }
        });

        List<Publication> N = null;
        N = ps.rechercherPublication(1);
        list_pub.getItems().addAll(N);
        // TODO
    }

    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userinterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    private void modifier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifierPublication.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("La publication a été modifié avec succès");
        alert.showAndWait();
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
    private void modifier_p(ActionEvent event) throws IOException, SQLException {

        Publication pub_Selectionne = list_pub.getSelectionModel().getSelectedItem();
          
        if (pub_Selectionne != null) {
            Publication p = new Publication();
            p.setTitre_publication(titre_pub.getText());
            p.setContenu_publication(contenu_pub.getText());
            p.setDate_publication(new Date(System.currentTimeMillis()));

            ps.modifierContenuPublication(p.getId_pub(), p.getContenu_publication());
            list_pub.getItems().remove(pub_Selectionne);
            list_pub.setCellFactory(param -> new ListCell<Publication>() {
                @Override
                protected void updateItem(Publication item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getTitre_publication()+ " - " + item.getContenu_publication()+ " - " );
                    }
                }
            });

            List<Publication> list_p = null;

            list_p = ps.afficherPublication();

            list_pub.getItems().addAll(p);
            
        }
}
}