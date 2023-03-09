/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Publication;
import service.Publicationservice;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class PublicationController implements Initializable {

    @FXML
    private TextField idUtilisateurTextField;
    @FXML
    private Button afficherToutesLesPublications;
    @FXML
    private Button afficherPublicationUtilisateur;
    @FXML
    private Button rechercherparNomPrenom;
    @FXML
    private Button home;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn<Publication, Integer> colID_Publication;

    @FXML
    private TableColumn<Publication, Integer> colID_User;
    @FXML
    private TableColumn<Publication, String> colTitrePublication;

    @FXML
    private TableColumn<Publication, String> colContenuPublication;

    @FXML
    private TableColumn<Publication, Date> colDatePublication ;

    private ObservableList<Publication> publications;
    @FXML
    private ImageView image;

    @FXML
    public void afficherPublications(ActionEvent event) throws SQLException {
        Publicationservice dc = new Publicationservice();
        publications = FXCollections.observableArrayList(dc.afficherPublication());
        tableview.setItems(publications);
        tableview.setOpacity(1);
    }

   
    private ObservableList<Publication> publicationUser;

    @FXML
    public void afficherPublicationUtilisateur(ActionEvent event) {
        if ((idUtilisateurTextField.getText().isEmpty()) || ((Integer.parseInt(idUtilisateurTextField.getText())) == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le texte ne peut pas être vide ou nul");
            alert.showAndWait();
        } else {
            Publicationservice dc = new Publicationservice();
            publicationUser = FXCollections.observableArrayList(dc.rechercherPublication(Integer.parseInt(idUtilisateurTextField.getText())));
            if (publicationUser.isEmpty()) {
                tableview.setOpacity(0);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("cet utilisateur n'existe pas ou n'as pas de publications créés");
                alert.showAndWait();
            } else {
                tableview.setItems(publicationUser);
                tableview.setOpacity(1);
                idUtilisateurTextField.clear();
            }
        }
        
    }

    private ObservableList<Publication> publicationList;

    @FXML
    public void afficherPublicationUtilisateurNomPrenom(ActionEvent event) {

        Publicationservice dc = new Publicationservice();
        publicationList = FXCollections.observableArrayList(dc.rechercherPublication((nomTextField.getText()), prenomTextField.getText()));
        if (publicationList.isEmpty()) {
            tableview.setOpacity(0);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("cet utilisateur n'existe pas ou n'as pas de publications créés");
            alert.showAndWait();
        } else {
            tableview.setItems(publicationList);
            tableview.setOpacity(1);
            prenomTextField.clear();
            nomTextField.clear();
        }
    }

    @FXML
    public void home(ActionEvent event) throws IOException {
        Parent pageSuivanteParent = FXMLLoader.load(getClass().getResource("/view/HOME.fxml"));
        Scene pageSuivanteScene = new Scene(pageSuivanteParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(pageSuivanteScene);
        appStage.show();
    }

    /**
     * @FXML public void handleButtonAction(ActionEvent event) {
     *
     * Connection con = null; PreparedStatement ps = null; try { con =
     * DriverManager.getConnection("jdbc:mysql://localhost:3306/doulicha",
     * "root", ""); String query = "INSERT INTO discussion (ID_user,
     * titre_discussion, contenu_discussion, date_discussion) VALUES (?, ?, ?,
     * ?)"; ps = con.prepareStatement(query); ps.setInt(1,
     * Integer.parseInt(idUtilisateurTextField.getText())); ps.setString(2,
     * titreDiscussionTextField.getText()); ps.setString(3,
     * contenuDiscussionTextField.getText()); ps.setDate(4,
     * java.sql.Date.valueOf(java.time.LocalDate.now())); //date actuelle
     * ps.executeUpdate(); System.out.println("Nouvelle discussion ajoutée à la
     * base de données!"); } catch (SQLException ex) {
     * System.err.println(ex.getMessage()); } finally { try { if (ps != null) {
     * ps.close(); } if (con != null) { con.close(); } } catch (SQLException ex)
     * { System.err.println(ex.getMessage()); } } }
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colID_Publication.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        colID_User.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        colTitrePublication.setCellValueFactory(new PropertyValueFactory<>("titre_publication"));
        colContenuPublication.setCellValueFactory(new PropertyValueFactory<>("contenu_publication"));
        colDatePublication.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
       Image img = new Image("/view/Swappywhite.png");
        image.setImage(img);
        



    }
}