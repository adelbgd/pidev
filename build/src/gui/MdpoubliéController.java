/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.EmailSender;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MdpoubliéController implements Initializable {

    @FXML
    private TextField tfmail;
    @FXML
    private Button btncode;
    
    public static int code ; 
    public static String emailReset ; 
    @FXML
    private ImageView image;
    @FXML
    private Button retour;
    
    
        private int genererCode()
        {
            Random  random  = new Random();
            return 100000 + random.nextInt(900000);
        }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnenvoyer(ActionEvent event) {
        
        
        if ( !tfmail.getText().isEmpty()  && tfmail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            emailReset = tfmail.getText();
            code = genererCode();
        EmailSender.sendEmail("chadine.benaissa@esprit.tn", "201JFT5596", tfmail.getText(), "Reset password", "Votre code de reinitialisation : " + code  );
         try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/verifcode.fxml"));
                   

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        }
        else {
            Alert A = new Alert(Alert.AlertType.WARNING); 
            A.setContentText("veuillez saisir un email valide !");
            A.show();
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }
    
    
    
}
