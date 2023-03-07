/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.client;
import services.clientServices;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.admin;
import services.adminServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionClientsController implements Initializable {

    clientServices ps = new clientServices();
    @FXML
    private Button btnGestionAdmin;
    @FXML
    private Button btnGestionClients;
    @FXML
    private TableView<client> TableViewClients;
    @FXML
    private TableColumn<client, String> ColumnNom;
    @FXML
    private TableColumn<client, String> ColumnPrenom;
    @FXML
    private TableColumn<client, Long> ColumnNumero;
    @FXML
    private TableColumn<client, String> ColumnAdresse;
    @FXML
    private TableColumn<client, String> ColumnSexe;
    @FXML
    private TableColumn<client, String> ColumnMail;
    @FXML
    private TableColumn<client, String> ColumnPassword;
    @FXML
    private TextField TF_password;
    @FXML
    private Button btnModifier;
    @FXML
    private Button bntSupprimer;
    @FXML
    private TableColumn<client, Integer> ColumnId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tf_num;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_sexe;
    @FXML
    private TextField tfMail;
    @FXML
    private Label tfid;
    @FXML
    private TextField TF_ID;

    /**
     * Initializes the controller class.
     */
    public void afficherTableview(){
         List<client> lc = ps.getall();
        System.out.println(lc);
        ObservableList<client> ClientList = FXCollections.observableArrayList(lc);
        System.out.println(ClientList);
       
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("Id_client"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnNumero.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        ColumnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ColumnSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        ColumnMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        

        TableViewClients.setItems(ClientList);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        afficherTableview();
    }

    @FXML
    private void Ajouter(ActionEvent event) {

        client c = new client();
        c.setMail(tfMail.getText());
        c.setNom(tfNom.getText());
        c.setPrenom(tfPrenom.getText());
        c.setSexe(tf_sexe.getText());
        c.setAdresse(tf_adresse.getText());
        c.setNum_tel(Integer.parseInt(tf_num.getText()));
        c.setPassword(TF_password.getText());
        ps.addclient(c);
        afficherTableview();

    }

    @FXML
    private void btnGestionAdminAction(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/GestionAdmin.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void btnGestionClientsAction(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/GestionClients.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void btnModifierAction(ActionEvent event) {
                 clientServices ps= new clientServices();
 
        if ( tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty() |TF_password.getText().isEmpty() | tfMail.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!tfMail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (tfNom.getText().equals(TF_password.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {
            client c = new client(TF_ID.getPrefColumnCount(), tfNom.getText(), tfPrenom.getText(), tf_sexe.getText(), tf_adresse.getText(), tf_num.getLength(),tfMail.getText(), TF_password.getText());
           ps.modifierclient(TF_ID.getPrefColumnCount(), tfNom.getText(), tfPrenom.getText(),tf_num.getLength(),tf_adresse.getText(),  tf_sexe.getText(), tfMail.getText(),  TF_password.getText());

            JOptionPane.showMessageDialog(null,"client modified");
           List<client> lc = ps.getall();
        ObservableList<client> ClientList = FXCollections.observableArrayList(lc);

        ColumnId.setCellValueFactory(new PropertyValueFactory<>("Id_client"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnNumero.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        ColumnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ColumnSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        ColumnMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
            
        }
        
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) {
        
        client c =  TableViewClients.getSelectionModel().getSelectedItem();
        
        ps.supprimerclient(c.getId_client());
        
        afficherTableview();
        
    }

}
