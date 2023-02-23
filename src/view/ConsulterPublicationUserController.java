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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Publication;
import model.commentaire;
import service.Publicationservice;
import service.commentaireservice;

/**
 * FXML Controller class
 *
 * @author azizl
 */
public class ConsulterPublicationUserController implements Initializable {

    @FXML
    private ListView<Publication> list_pub;
    @FXML
    private Button retour;
    Publicationservice ps = new Publicationservice();
    commentaireservice ps2 = new commentaireservice();

    @FXML
    private TextField contenu_c;

    @FXML
    private Button commenter;
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
                    setText(item.getTitre_publication() + " - " + item.getContenu_publication() + " - " + item.getDate_publication());
                }
            }
        });

        List<Publication> N = null;
        N = ps.afficherPublication();
        list_pub.getItems().addAll(N);
         Image img = new Image("/view/Swappyblack.png");
        image.setImage(img);

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
    private void commenter(ActionEvent event) {
        Publication p_Selectionne = list_pub.getSelectionModel().getSelectedItem();

        if (p_Selectionne != null) {
            commentaire c = new commentaire();
            c.setId_pub(p_Selectionne.getId_pub());
            c.setId_user(1);
            c.setUsername("monta");
            c.setSuj_com(contenu_c.getText());
            System.out.println(c);
            ps2.addCommentaire(c);
        }
    }

}
