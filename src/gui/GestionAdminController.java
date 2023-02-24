/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import models.admin;
import services.adminServices;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionAdminController implements Initializable {

   
    @FXML
    private ImageView background;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelCin;
    @FXML
    private TextField TfCin;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPwd;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPwd;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableColumn<admin, String> ColumnCin;
    @FXML
    private TableColumn<admin, String> ColumnNom;
    @FXML
    private TableColumn<admin, String> ColumnPrenom;
    @FXML
    private TableColumn<admin, String> ColumnEmail;
    @FXML
    private TableColumn<admin, String> ColumnPassword;
    @FXML
    private TableView<admin> TableViewAdmin;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnGestionAdmin;
    @FXML
    private Button btnGestionClients;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
         adminServices as = new adminServices();

        List<admin> la = as.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);

        ColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("pwd"));
      
        TableViewAdmin.setItems(AdminList);
    }    

   
 

    @FXML
    private void btnModifierAction(ActionEvent event) {
                    adminServices ss= new adminServices();
 
        if ( tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty() |tfPwd.getText().isEmpty() | tfEmail.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!tfEmail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (tfNom.getText().equals(tfPwd.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {
            admin a = new admin(TfCin.getText(), tfNom.getText(), tfPrenom.getText(), tfEmail.getText(), tfPwd.getText());
            ss.modifier(  TfCin.getText(),tfNom.getText(), tfPrenom.getText(),tfEmail.getText(),tfPwd.getText());

            JOptionPane.showMessageDialog(null,"User modified");
            List<admin> la = ss.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);

        ColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("pwd"));
      
        TableViewAdmin.setItems(AdminList);
            
        }
           

        
    }

    @FXML
    private void btnAjouterAction(ActionEvent event) {
        admin u = new admin();
        adminServices ss= new adminServices();
        u.setNom(tfNom.getText());
        u.setCin(TfCin.getText());
        u.setEmail(tfEmail.getText());
        u.setPrenom(tfPrenom.getText());
        u.setPwd(tfPwd.getText());
         if ( tfNom.getText().isEmpty() | tfPrenom.getText().isEmpty() |tfPwd.getText().isEmpty() | tfEmail.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!tfEmail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (tfNom.getText().equals(tfPwd.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {
        ss.ajouter(u);
        JOptionPane.showMessageDialog(null,"User added ! ");
         List<admin> la = ss.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);

        ColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("pwd"));
      
        TableViewAdmin.setItems(AdminList);
                }
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) {
          admin u = new admin();
        adminServices ss= new adminServices();
        
        u.setCin(TfCin.getText());
        ss.supprimer(u.getCin());
       
       Alert alert = new Alert (AlertType.INFORMATION);
       alert.setTitle("admin supprimé ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("admin supprimé avec succés !!");
       alert.show();
       
        List<admin> la = ss.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);

        ColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("pwd"));
      
        TableViewAdmin.setItems(AdminList);
    }

    @FXML
    private void btnGestionAdminAction(ActionEvent event) {
          try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/gui/GestionAdmin.fxml" ));

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

                Parent page1 = FXMLLoader.load(getClass().getResource("/gui/GestionClients.fxml" ));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
    }
    }
    

