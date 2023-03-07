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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static models.PasswordEncryption.encrypt;
import services.clientServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ResetpasswordController implements Initializable {

    @FXML
    private TextField tfnewpass;
    @FXML
    private Button btnresetpass;
    @FXML
    private TextField tfnewpassConfirm;
    @FXML
    private ImageView image;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnreset(ActionEvent event) {
        clientServices cs = new clientServices();
        String password  = tfnewpass.getText();
       String encryptedPassword = encrypt(password);
       
        if (!tfnewpass.getText().isEmpty() && tfnewpass.getText().equals(tfnewpassConfirm.getText()))
        {
        cs.ResetPassword(MdpoubliéController.emailReset, encryptedPassword);
            Alert A = new Alert(Alert.AlertType.INFORMATION); 
            A.setContentText("Mot de passe modifié avec succes!");
            A.show();
           
            
            
                  try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
                   

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        
        }
        else 
        {
            Alert A = new Alert(Alert.AlertType.ERROR); 
            A.setContentText("saisir deux mot de passe conforme ! ");
            A.show(); 
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mdpoublié.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }
    
}
