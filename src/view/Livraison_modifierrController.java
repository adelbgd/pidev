/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Commande;
import models.Livraison;
import services.CommandeService;
import services.LivraisonService;


           
/**
 * FXML Controller class
 *
 * @author adelb
 */
public class Livraison_modifierrController implements Initializable {

    private TextField modif_lieu;
    private TextField modif_status;
    private TextField modif_mode;
    @FXML
    private TextField modif_frais;
    private DatePicker modif_date;
    @FXML
    private Button retour4;
    @FXML
    private Button modifier_l;

     Livraison l = new Livraison();
     LivraisonService sp = new LivraisonService();
    @FXML
    private ComboBox<String> combo;
    @FXML
    private ComboBox<String> combo2;
    
    
    private final ObservableList<String> regions = FXCollections.observableArrayList("Ariana","Béja", "Ben Arous", "Bizerte", "Gabès", "Gafsa",
             "Jendouba", "Kairouan", "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Médenine",
             "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
              
            private final ObservableList<String> ArianaVilles = FXCollections.observableArrayList("Ariana ville", "borj Louzir", "Borj Touil", "Chargia", "Chargia", "Cité El Ghazela", "Ennasr", "Ettadhamen", "Soukra", "Menzah","Raoued");
            private final ObservableList<String> BéjaVilles = FXCollections.observableArrayList("Amdoun", "Goubellat", "Mjez El Beb", "Nefza", "Tebourzok", "Testour");
            private final ObservableList<String> BenArousVilles = FXCollections.observableArrayList("Bir El Kassa", "Borj Cedria", "Boumhal", "Boukarnine", "Boukarnine", "El Mourouj", "Ezzahra", "Fouchena", "Hammam Lif", "Morneg", "Rades");
            private final ObservableList<String> BizerteVilles = FXCollections.observableArrayList("El Alia", "Ghar El Melh", "Mateur", "Menzel Borgiba", "Ras Jebal", "Sejnane", "Zarzouna");
            private final ObservableList<String> GabèsVilles = FXCollections.observableArrayList("EL Hamma", "El Metouia", "Mareth", "Matmata");
            private final ObservableList<String> GafsaVilles = FXCollections.observableArrayList("Belkhyr", "El Ksaar", "Metlaoui", "Redeyef");
            private final ObservableList<String> JendoubaVilles = FXCollections.observableArrayList("Ain Drahem", "Bou Salem", "Fernena", "Ghardimaouu", "Tbarka");
            private final ObservableList<String> KairouanVilles = FXCollections.observableArrayList("Bou Hajla", "Chebika", "Hafouz", "Ousletia");
            private final ObservableList<String> KasserineVilles = FXCollections.observableArrayList("Sbeitla", "Thala", "Foussana", "Hassi El Ferid", "Feriana");
            private final ObservableList<String> KébiliVilles = FXCollections.observableArrayList("Douz", "El Faouar", "Souk El Ahad");
            private final ObservableList<String> KefVilles = FXCollections.observableArrayList("Dahmani", "Le Sers", "Nebeur", "Tajerouine");
            private final ObservableList<String> MahdiaVilles = FXCollections.observableArrayList("Bou Merdes", "Chorbane", "El Jem", "Hbira", "Chebba", "Souassi", "Melloulech");
            private final ObservableList<String> ManoubaVilles = FXCollections.observableArrayList("Douar Hicher", "Oued Ellil", "Borj El Amri", "", "Chebba", "Mornaguia", "Den Den");
            private final ObservableList<String> MonastirVilles = FXCollections.observableArrayList("Sahline", "Ksar Hellal", "Jemmal", "Moknine", "Zéramdine", "Bembla", "Sayada");
            private final ObservableList<String> MédenineVilles = FXCollections.observableArrayList("Ben Gardane", "Zarzis", "Djerba Midoun", "Djerba Houmt Souk", "Tataouine", "Remada", "Djerba Ajim");
            private final ObservableList<String> NabeulVilles = FXCollections.observableArrayList("Hammamet", "Korba", "Kelibia", "Beni Khiar", "Grombalia", "Soliman", "Korbous","Haouaria");
            private final ObservableList<String> SfaxVilles = FXCollections.observableArrayList("Agareb", "Bir Ali Ben Khalifa", "El Aïn", "Gremda", "Mahres", "Menzel Chaker", "Kerkennah ");
            private final ObservableList<String> SidibouzidVilles = FXCollections.observableArrayList("Bir El Mater", "Mezzouna", "Meknassy", "Menzel Bouzaiane", "Jelma", "Ouled Haffouz", "Sbeitla", "Souk Jedid");
            private final ObservableList<String> SilianaVilles = FXCollections.observableArrayList("Makthar", "Gaâfour", "Bargou", "Bargou", "Beni Mtir", "Boulhem", "Kesra");
            private final ObservableList<String> SousseVilles = FXCollections.observableArrayList("Bouficha", "Enfidha", "Hammam Sousse", "Hergla", "Kantaoui-Sousse", "Msaken", "Port El-Kantaoui");
            private final ObservableList<String> TataouineVilles = FXCollections.observableArrayList("Bir Lahmar", "Douiret", "Dehiba", "Ksar El Ferch", "Smâr", "Remada");  
            private final ObservableList<String> TozeurVilles = FXCollections.observableArrayList("Degache", "Hamet Jerid", "Metlaoui", "Nefta", "Tamerza");
            private final ObservableList<String> tunisVilles = FXCollections.observableArrayList("Ben Arous", "Carthage", "El Aouina", "La Marsa", "La Goulette", "La Soukra", "Sidi Bou Said");
            private final ObservableList<String> ZaghouenVilles = FXCollections.observableArrayList("Bir Mcherga", "Bir Salah", "Bir Halima", "El Fahs", "Djebel Oust", "Nadhour", "Zriba");
    @FXML
    private Label label10;
    @FXML
    private ComboBox<String> combo3;
    
     private final ObservableList<String> company = FXCollections.observableArrayList("First Delivery","Fast Coursier", "Allo Coursier", "Tunisia Express");
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo.setItems(regions);
        
        combo.setOnAction(event -> {
            String selectedRegion = combo.getSelectionModel().getSelectedItem();
            if (selectedRegion != null) {
                switch (selectedRegion) {
                    case "Ariana":
                        combo2.setItems(ArianaVilles);
                        break;
                    case "Béja":
                        combo2.setItems(BéjaVilles);
                        break;
                    case "Ben Arous":
                        combo2.setItems(BenArousVilles);
                        break;
                    case "Bizerte":
                        combo2.setItems(BizerteVilles);
                        break;
                    case "Gabès":
                        combo2.setItems(GabèsVilles);
                        break;
                    case "Gafsa":
                        combo2.setItems(GafsaVilles);
                        break;
                        case "Jendouba":
                        combo2.setItems(JendoubaVilles);
                        break;
                        case "Kairouan":
                        combo2.setItems(KairouanVilles);
                        break;
                        case "Kasserine":
                        combo2.setItems(KasserineVilles);
                        break;
                        case "Kébili":
                        combo2.setItems(KébiliVilles);
                        break;
                        case "Kef":
                        combo2.setItems(KefVilles);
                        break;
                        case "Mahdia":
                        combo2.setItems(MahdiaVilles);
                        break;
                        case "Manouba":
                        combo2.setItems(ManoubaVilles);
                        break;
                        case "Médenine":
                        combo2.setItems(MédenineVilles);
                        break;
                        case "Monastir":
                        combo2.setItems(MonastirVilles);
                        break;
                        case "Nabeul":
                        combo2.setItems(NabeulVilles);
                        break;
                        case "Sfax":
                        combo2.setItems(SfaxVilles);
                        break;
                        case "Sidi Bouzid":
                        combo2.setItems(SidibouzidVilles);
                        break;
                        case "Siliana":
                        combo2.setItems(SilianaVilles);
                        break;
                        case "Sousse":
                        combo2.setItems(SousseVilles);
                        break;
                        case "Tataouine":
                        combo2.setItems(TataouineVilles);
                        break;
                        case "Tozeur":
                        combo2.setItems(TozeurVilles);
                        break;
                        case "Tunis":
                        combo2.setItems(tunisVilles);
                        break;
                        case "Zaghouan":
                        combo2.setItems(ZaghouenVilles);
                        break;
                        
                    default:
                        combo2.setItems(FXCollections.emptyObservableList());
                        break;
                }
            } else {
                combo2.setItems(FXCollections.emptyObservableList());
            }
        });
        combo3.setItems(company);

    }    
    void getLivraison(Livraison l){
 
    modif_frais.setText(Integer.toString(l.getFrais_livraison())); 
    label10.setText(l.getStatus_livraison());
    combo.setValue(l.getLieu_livraison());
    combo2.setValue(l.getVille());
    combo3.setValue(l.getComp());
}

    @FXML
    private void retour4(ActionEvent event) throws IOException {
                // Charger l'interface suivante à partir de son fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("livraison_acceuil.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
    }
    
    

    @FXML
    private void modifier_l(ActionEvent event) {
          //LocalDate k = modif_date.getValue(); 
       
         String region = combo.getValue();
         String ville = combo2.getValue();
         String com = combo3.getValue();
         String status = label10.getText();
         String frais = modif_frais.getText();
         
         // Vérifier que les champs ne sont pas vides
    if (region.isEmpty() || ville.isEmpty() || status.isEmpty() ||  frais.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }
    
    // Vérifier que le nombre d etapes est un nombre valide
    int Frais;
        try {
Frais = Integer.parseInt(frais);
        if (Frais <= 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le frais doit être supérieur à zéro.");
            alert.showAndWait();
            return;
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le frais doit être un nombre");
        alert.showAndWait();
        return;
    }
       
        //LocalDate d = modif_date.getValue(); 
        LocalDate d1 = LocalDate.now();
        l.setDate_livraison(java.sql.Date.valueOf(d1));
        l.setLieu_livraison(region);
        l.setVille(ville);
        l.setComp(com);
        l.setStatus_livraison(status);
        
        l.setFrais_livraison(Frais);
        
           sp.updateLivraison(l);
           
           // Afficher un message de confirmation
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("La livraison a été modifié avec succès");
    alert.showAndWait();
    }
    
}
