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
import models.SessionManager;
import models.client;
import services.clientServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SignInController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_telephone;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tf_sexe;
    @FXML
    private TextField tfpwd;
    @FXML
    private TextField tf_nom;
    @FXML
    private Button client_create;
    private Button cancelbtn;
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
    private void Create_client(ActionEvent event) throws IOException {
         //clientServices cs = new clientServices();
    
        clientServices ps = new clientServices();
        Alert A = new Alert(Alert.AlertType.INFORMATION);
         String email = tfemail.getText();
        String password = tfpwd.getText();
         String encryptedPassword = encrypt(password);
        int id_client = -1;

        client c = new client();
        c.setMail(tfemail.getText());
        c.setNom(tf_nom.getText());
        c.setPrenom(tf_prenom.getText());
        c.setSexe(tf_sexe.getText());
        c.setAdresse(tfadresse.getText());
        c.setNum_tel(Integer.parseInt(tf_telephone.getText()));
        c.setPassword(encryptedPassword);
        ps.addclient(c);
       id_client = ps.authentification(email, encryptedPassword);
       SessionManager.getInstance().setCurrentClient(ps.getOneById(id_client));


            if (id_client != -1) {
                A.setContentText("Client connecté ! ");
                A.show();
                 try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/gui/ApplicationFrontTest.fxml" ));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
       /* try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/GestionAdmin.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }*/

    }
    }

    private void btncancel(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root= loader.load();
        cancelbtn.getScene().setRoot(root);
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

