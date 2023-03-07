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

/**
 * FXML Controller class
 *
 * @author user
 */
public class VerifcodeController implements Initializable {

    @FXML
    private TextField tfCode;
    @FXML
    private Button btnConfirmCode;
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
    private void bntConfirmCodeAction(ActionEvent event) {
        
        if (Integer.parseInt(tfCode.getText()) == MdpoubliéController.code )
        {
             try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/Resetpassword.fxml"));
                   

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
             Alert A = new Alert(Alert.AlertType.WARNING); 
            A.setContentText("code erroné !");
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
    

