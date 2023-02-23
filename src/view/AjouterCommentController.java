/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author azizl
 */
public class AjouterCommentController implements Initializable {

    @FXML
    private TextField username_c;
    @FXML
    private TextField name_notifb21;
    @FXML
    private TextField sujet_c;
    @FXML
    private Button ajouter_c;
    @FXML
    private Button retour1;
    @FXML
    private DatePicker text_date_c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_c(ActionEvent event) {
    }

    @FXML
    private void retour1(ActionEvent event) {
    }
    
}
