package view;


import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import model.commentaire;
import service.commentaireservice;

public class CommentaireController implements Initializable {

    @FXML
    private TextField idUtilisateurTextField;
    @FXML
    private TextField idPublicationTextField;
    @FXML
    private Button afficherCommentPub;
    @FXML
    private Button afficherCommentPubUtilisateur;
    @FXML
    private Button home;
    @FXML
    private TableView tableCommentaire;
    @FXML
    private TableColumn<commentaire, Integer> colID_Com;
    @FXML
    private TableColumn<commentaire, Integer> colID_Pub;
    @FXML
    private TableColumn<commentaire, Integer> colID_User;
    @FXML
    private TableColumn<commentaire, String> colUsername;
    @FXML
    private TableColumn<commentaire, String> colsujet_com;
    @FXML
    private TableColumn<commentaire, Date> coldate_Com;
    @FXML 
    private TableColumn<commentaire, Integer> colnb_reaction;
            
   
    private ObservableList<commentaire> CommentaireUser;
    @FXML
    private Button rechercheCom;
    @FXML
    private ImageView image;

    @FXML
    public void afficherCommentairesUtilisateur(ActionEvent event) throws SQLException {
        if ((idUtilisateurTextField.getText().isEmpty()) || ((Integer.parseInt(idUtilisateurTextField.getText())) == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le texte ne peut pas être vide ou nul");
            alert.showAndWait();
        } else {
            commentaireservice dc = new commentaireservice();
            CommentaireUser = FXCollections.observableArrayList(dc.fetchCommentaireUser(parseInt(idUtilisateurTextField.getText())));
            if (CommentaireUser.isEmpty()) {
                tableCommentaire.setOpacity(0);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("cet utilisateur n'existe pas ou n'as pas de discussions créés");
                alert.showAndWait();
            } else {
                tableCommentaire.setItems(CommentaireUser);
                tableCommentaire.setOpacity(1);
                idUtilisateurTextField.clear();
            }
        }
    }
    private ObservableList<commentaire> commentairePublication;

    @FXML
    public void afficherCommentairespublication(ActionEvent event) throws SQLException {
        if ((idPublicationTextField.getText().isEmpty()) || ((Integer.parseInt(idPublicationTextField.getText())) == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le texte ne peut pas être vide ou nul");
            alert.showAndWait();
        }
        commentaireservice dc = new commentaireservice();
        commentairePublication = FXCollections.observableArrayList(dc.fetchCommentairePublication(parseInt(idPublicationTextField.getText())));
        if (commentairePublication.isEmpty()) {
            tableCommentaire.setOpacity(0);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("cet publication n'existe pas ou n'as pas de commentaires");
            alert.showAndWait();
        } else {
            tableCommentaire.setItems(commentairePublication);
            tableCommentaire.setOpacity(1);
            idPublicationTextField.clear();
        }
    }
    private ObservableList<commentaire> commentaireUserPub;

    @FXML
    public void afficherCommentairePublicationUtilisateur(ActionEvent event) {
        if (((idPublicationTextField.getText().isEmpty()) || ((Integer.parseInt(idPublicationTextField.getText())) == 0)) || ((idUtilisateurTextField.getText().isEmpty()) || ((Integer.parseInt(idUtilisateurTextField.getText())) == 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le texte ne peut pas être vide ou nul");
            alert.showAndWait();
        } else {
            commentaireservice dc = new commentaireservice();
            commentaireUserPub = FXCollections.observableArrayList(dc.fetchCommentairePublicationUser(parseInt(idPublicationTextField.getText()), parseInt(idUtilisateurTextField.getText())));
            if (commentaireUserPub.isEmpty()) {
                tableCommentaire.setOpacity(0);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("cet utilisateur n'existe pas ou n'as pas de commentaires créés sur cette discussion");
                alert.showAndWait();
            } else {
                tableCommentaire.setItems(commentaireUserPub);
                tableCommentaire.setOpacity(1);
                idPublicationTextField.clear();
                idUtilisateurTextField.clear();
            }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID_Com.setCellValueFactory(new PropertyValueFactory<>("id_com"));
        colID_Pub.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        colID_User.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colsujet_com.setCellValueFactory(new PropertyValueFactory<>("suj_com"));
        coldate_Com.setCellValueFactory(new PropertyValueFactory<>("date_com"));
        colnb_reaction.setCellValueFactory(new PropertyValueFactory<>("nb_reaction"));
        Image img = new Image("/view/Swappywhite.png");
        image.setImage(img);

    }

}