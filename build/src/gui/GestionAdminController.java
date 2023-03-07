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

   
    private TextField TfCin;
    private TextField tfNom;
    private TextField tfPrenom;
    private TextField tfEmail;
    private TextField tfPwd;
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
    private ImageView image;
    @FXML
    private Button retour;
    public static admin selectedAdmin = null;
     

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
    adminServices ss = new adminServices();
    selectedAdmin = TableViewAdmin.getSelectionModel().getSelectedItem();

    if (selectedAdmin == null) {
        // If no admin is selected, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Veuillez sélectionner un administrateur !");
        al.showAndWait();
    } 
    try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/ModifAdmin.fxml"));
                   

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
   /* else if (tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPwd.getText().isEmpty()) {
        // If any of the text fields are empty, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Veuillez remplir tous les champs !");
        al.showAndWait();
    } else if (!tfEmail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                    + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$")) {
        // If the email address is not valid, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Veuillez entrer une adresse e-mail valide !");
        al.showAndWait();
    } else if (tfNom.getText().equals(tfPwd.getText())) {
        // If the username and password are the same, display an error message
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText(null);
        al.setContentText("Le nom d'utilisateur et le mot de passe ne peuvent pas être identiques !");
        al.showAndWait();
    } else {
        // Modify the selected admin record
        selectedAdmin.setCin(TfCin.getText());
        selectedAdmin.setNom(tfNom.getText());
        selectedAdmin.setPrenom(tfPrenom.getText());
        selectedAdmin.setEmail(tfEmail.getText());
        selectedAdmin.setPwd(tfPwd.getText());
        ss.modifier(selectedAdmin.getCin(),selectedAdmin.getNom(),selectedAdmin.getPrenom(),selectedAdmin.getEmail(),selectedAdmin.getPwd());

        // Display a success message
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setHeaderText(null);
        al.setContentText("L'administrateur a été modifié avec succès !");
        al.showAndWait();

        // Refresh the TableView with the updated data
        List<admin> la = ss.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);

        ColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColumnPassword.setCellValueFactory(new PropertyValueFactory<>("pwd"));

        TableViewAdmin.setItems(AdminList);
    }*/
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
              /*      adminServices ss= new adminServices();
 
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
            
        }*/
           
   @FXML
    private void btnAjouterAction(ActionEvent event) {
          try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/gui/AjoutAdmin.fxml" ));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
        
    }

 
       /* admin u = new admin();
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
                }*/
        @FXML
    private void btnSupprimerAction(ActionEvent event) {
       /*  admin u = new admin();
          u.setCin(TfCin.getText());
        adminServices ss= new adminServices();
       
        TableViewAdmin.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue != null) {
        admin selectedAdmin = TableViewAdmin.getSelectionModel().getSelectedItem();
        ss.supprimer(selectedAdmin.getCin());
        Alert alert = new Alert (AlertType.INFORMATION);
        alert.setTitle("admin supprimé ");
        alert.setHeaderText("ATTENTION !!");
        alert.setContentText("admin supprimé avec succés !!");
        alert.show();
         List<admin> la = ss.getall();
        ObservableList<admin> AdminList = FXCollections.observableArrayList(la);
        AdminList.remove(selectedAdmin);
    }
        });*/
        // Get the selected admin from the TableView
    admin selectedAdmin = TableViewAdmin.getSelectionModel().getSelectedItem();

    if (selectedAdmin != null) {
        // Call the service method to delete the selected admin
        adminServices ss = new adminServices();
        ss.supprimer(selectedAdmin.getCin());

        // Display a message to the user
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Admin supprimé");
        alert.setHeaderText("Attention !");
        alert.setContentText("Admin supprimé avec succès !");
        alert.show();

        // Remove the selected admin from the TableView
        TableViewAdmin.getItems().remove(selectedAdmin);
    }
     

                }
    @FXML
    private void retour(ActionEvent event) {
    }
    }



        
        
        
        
        
        
        
        
        
        
        
        
        
        /*  admin u = new admin();
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
    }*/
                
                

 /*   private void btnGestionAdminAction(ActionEvent event) {
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
    }*/

   /* @FXML
    private void retour(ActionEvent event) {
    }*/
    
                
    

