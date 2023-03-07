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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import services.ReclamationService;
import services.clientServices;

/**
 * FXML Controller class
 *
 * @author Ghass
 */
public class AddRecController implements Initializable {

    @FXML
    private TextArea tfobj;
    @FXML
    private ComboBox<String> combotype;
    SessionManager sessionManager = SessionManager.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        ObservableList<String> values = FXCollections.observableArrayList("Problème technique", "Interface utilisateur", "Problèmes de paiement", "Demandes de fonctionnalités", "Service client", "Problèmes de sécurité");
        combotype.setItems(values);
        combotype.setValue("Problème technique");
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {

        clientServices sa = new clientServices();
        ReclamationService sd = new ReclamationService();
        Reclamation p = new Reclamation();
        p.setMessage(tfobj.getText());
        p.setSujet(combotype.getValue());
        p.setEtat(0);
        p.setC(sessionManager.getCurrentClient());
        p.setEmail(sessionManager.getCurrentClient().getMail());
        sd.ajouter(p);
    }

}
