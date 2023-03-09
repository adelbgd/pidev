/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Publication;
import model.commentaire;
import service.Publicationservice;
import service.commentaireservice;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import com.textrazor.annotations.Response;
import com.textrazor.annotations.Word;
import com.textrazor.annotations.Response;
import com.textrazor.annotations.Word;
import javax.swing.JOptionPane;
import com.textrazor.TextRazor;
import com.textrazor.AnalysisException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import javax.swing.JOptionPane;
import model.commentaire ;
/**
 * FXML Controller class
 *
 * @author azizl
 */
public class ConsulterPublicationUserController implements Initializable {

    @FXML
    private ListView<Publication> list_pub;
    @FXML
    private Button retour;
    Publicationservice ps = new Publicationservice();
    commentaireservice ps2 = new commentaireservice();

    @FXML
    private TextField contenu_c;

    @FXML
    private Button commenter;
    @FXML
    private ImageView image;
    @FXML
    private DatePicker dateDebutPicker;
    @FXML
    private DatePicker dateFinPicker;
    @FXML
    private Button filtrer_b;
    @FXML
    private Button signaler;

    private TextRazor client;
    private static final String TEXTRAZOR_API_KEY = "526ed98512e535f0ebf9ce11ef025ed9abc9692ee87059095fea496d";

    List<String> badWords = new ArrayList<>();

    // You can now use the badWords list in your program
    // For example, you could iterate over the list and print each word
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list_pub.setCellFactory(param -> new ListCell<Publication>() {
            @Override
            protected void updateItem(Publication item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitre_publication() + " \n " + item.getContenu_publication() + " \n " + item.getDate_publication() + "\n" + "Commentaires:"  );
                }
            }
        });

        List<Publication> N = null;
        N = ps.afficherPublication();
        list_pub.getItems().addAll(N);
        Image img = new Image("/view/Swappyblack.png");
        image.setImage(img);

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userinterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Swappy ");
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    private void commenter(ActionEvent event) throws NetworkException, AnalysisException {
        Publication p_Selectionne = list_pub.getSelectionModel().getSelectedItem();
        String commentaire = contenu_c.getText();

        List<String> badWords = new ArrayList<>();
        badWords.add("shit");
        badWords.add("merde");
        badWords.add("fuck");
        badWords.add("putain");
        badWords.add("dick");
        badWords.add("pussy");
        badWords.add("motherfucker");
        badWords.add("Fils de pute");
        badWords.add("Connard");
        badWords.add("Enculé");
        badWords.add("Salope");
        badWords.add("Bite");
        badWords.add("Chatte");
        badWords.add("Nègre");
        badWords.add("Tapette");
        badWords.add("Son of a bitch");
        badWords.add("Asshole");
        badWords.add("nigga");

        // Check if the comment contains any of the bad words
        boolean containsBadWord = false;
        for (String word : badWords) {
            if (commentaire.contains(word)) {
                containsBadWord = true;
                break;
            }
        }

        // Produce an alert message based on whether the comment contains any bad words
        if (containsBadWord) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("BADWORDS");
            alert.setHeaderText(null);
            alert.setContentText("Le commentaire contient des mots désagréables");
            alert.showAndWait();
        } else {
            commentaire c = new commentaire();
            c.setId_pub(p_Selectionne.getId_pub());
            c.setId_user(1);
            c.setUsername("monta");
            c.setSuj_com(contenu_c.getText());
            System.out.println(c);
            ps2.addCommentaire(c);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Le commentaire a été ajouté avec succès");
            alert.showAndWait();
        }
    }

    @FXML
    private void filtrer_p(ActionEvent event
    ) {
        list_pub.getItems().clear();
        LocalDate dateDebut = dateDebutPicker.getValue();
        LocalDate dateFin = dateFinPicker.getValue();

        List<Publication> publications = ps.afficherPublication();

        List<Publication> publicationsFiltrees = publications.stream()
                .filter(p -> {
                    Date datePublication = p.getDate_publication();
                    return datePublication.after(Date.from(dateDebut.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                            && datePublication.before(Date.from(dateFin.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                })
                .collect(Collectors.toList());

        if (publicationsFiltrees.isEmpty()) {
            // Display an alert if no publications were found for the selected date range
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aucune publication trouvée");
            alert.setHeaderText(null);
            alert.setContentText("Aucune publication n'a été trouvée pour la plage de dates sélectionnée.");
            alert.showAndWait();
        } else {
            // Display the filtered publications in the ListView
            list_pub.getItems().addAll(publicationsFiltrees);
        }
    }

    @FXML
    private void ListViewClick(MouseEvent event
    ) {
    }

    @FXML
    private void signaler(ActionEvent event
    ) {

        Publication p_Selectionne = list_pub.getSelectionModel().getSelectedItem();

        // récupérer la publication sélectionnée
        if (p_Selectionne != null) {
            System.out.println(p_Selectionne.getNb_signals() + "," + p_Selectionne.getId_pub());
            int nbSignalements = ps.rechercherPublicationParId(p_Selectionne.getId_pub()).getNb_signals();
            System.out.println(nbSignalements);
            p_Selectionne.setNb_signals(nbSignalements + 1);

            ps.modifierTitrePublication(nbSignalements + 1, p_Selectionne.getId_pub());
            if (nbSignalements + 1 >= 10) {
                try {
                    ps.supprimerPublication(p_Selectionne.getId_pub());
                    // supprimer la publication si elle a été signalée 10 fois ou plus
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConsulterPublicationUser.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(loader.load()));
                    stage.setTitle("Swappy ");
                    stage.show();
                    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    currentStage.hide();
                } catch (IOException ex) {
                    Logger.getLogger(ConsulterPublicationUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

}
