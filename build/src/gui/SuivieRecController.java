/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import models.Reclamation;
import models.SessionManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.*;

/**
 * FXML Controller class
 *
 * @author Ghass
 */
public class SuivieRecController implements Initializable {

    @FXML
    private ScrollPane scrollpane;
    private ReclamationService sr = new ReclamationService();
    SessionManager sessionManager = SessionManager.getInstance();

    /**
     * Initializes the controller class.
     */
    public void table() throws SQLException {
        List<Reclamation> offres = sr.recupererParClient(sessionManager.getCurrentClient().getId_client());
        VBox vBox = new VBox();

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(30);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);//

        int count = 0;
        for (Reclamation offre : offres) {
            VBox box = createOffreBox(offre);

            hBox.getChildren().add(box);
            count++;

            if (count == 1) {
                vBox.getChildren().add(hBox);
                hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(100);
                count = 0;
            }
        }

        if (count > 0) {
            vBox.getChildren().add(hBox);
        }

        scrollpane.setContent(vBox);
        scrollpane.setFitToWidth(true);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
        try { 
            table();
        } catch (SQLException ex) {
            Logger.getLogger(SuivieRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private VBox createOffreBox(Reclamation offre) {
        VBox box = new VBox();

        box.setAlignment(Pos.CENTER);
        box.setSpacing(30);
        box.setUserData(offre.getId_reclamation()); // set the ID as the user data for the VBox

        Label titre = new Label("Type :  " + offre.getSujet());

        Button bb = new Button();
        bb.setText("Supprimer");

        Label user = new Label(offre.getC().getNom());
        Label etat = new Label();
        if (offre.getEtat() == 0) {
            etat = new Label("Etat :               Non Traitée");
            
        } else {
            etat = new Label("Etat :                      Traitée");
        }

        Label voir = new Label("Contenu      :" + offre.getMessage());
        Label sep = new Label("____________________________________________________________________________________________________________________");

        user.setStyle("-fx-text-fill : Blue;");
        voir.setStyle("-fx-text-fill : Black;");
        etat.setStyle("-fx-text-fill : Red;");

        voir.setFont(Font.font("Serif", FontWeight.LIGHT, 23));
        titre.setFont(Font.font("Arial", FontWeight.BOLD, 29));

        user.setWrapText(true);
        box.getChildren().addAll(titre, voir, etat, bb, sep);

        bb.setOnMouseClicked(event -> {

            try {
                sr.supprimer(offre);
            } catch (SQLException ex) {
                Logger.getLogger(SuivieRecController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                table();
            } catch (SQLException ex) {
                Logger.getLogger(SuivieRecController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

 
        return box;
    }
}
