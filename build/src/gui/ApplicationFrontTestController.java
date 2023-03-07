/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.SessionManager;
import models.client;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ApplicationFrontTestController implements Initializable {

    @FXML
    private Label LabelId;
    @FXML
    private Label labelName;
    @FXML
    private Label LabelPrenom;
    @FXML
    private Pane Pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         client c = SessionManager.getInstance().getCurrentClient();
        LabelId.setText("" + c.getId_client());
        labelName.setText("Bonjour si " + c.getNom());
        LabelPrenom.setText(c.getPrenom());
        
        
       
    }    

    @FXML
    private void HyperRecAction(ActionEvent event) {
        Pane.getChildren().clear();
        FXMLLoader loadOffre = new FXMLLoader(getClass().getResource("/GUI/AffReclamationFXML.fxml"));
        try {
            Pane.getChildren().add(loadOffre.load());
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
}
