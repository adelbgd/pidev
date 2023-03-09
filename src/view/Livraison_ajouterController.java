/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
public class Livraison_ajouterController implements Initializable {

   Livraison l = new Livraison();

    private TextField status_l;
    
     @FXML
    private TextField label;
     
    @FXML
    private Button retour3;
    
    @FXML
    private Button ajouter_l;
    
    private DatePicker text_date_l;

    @FXML
    private Button calculerFraisLivraison;
    
    @FXML
    private Label label10;
      
      
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
    private ComboBox<String> combo3;
    
    private final ObservableList<String> company = FXCollections.observableArrayList("First Delivery","Fast Coursier", "Allo Coursier", "Tunisia Express");
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

       
    
    

    @FXML
    private void retour3(ActionEvent event) throws IOException {
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
    private void ajouter_l(ActionEvent event) {
       
       String selectedOption = (String) combo.getValue();
        if (selectedOption.isEmpty()     ){
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Livraison ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        }
        else{String selectedOptionn = (String) combo3.getValue();
        if (selectedOption.isEmpty()     ){
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Livraison ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();}
     
        
        else{
       
        
        LocalDate d1 = LocalDate.now();
        LocalDate d2= d1.plusDays(3);
        l.setDate_livraison(java.sql.Date.valueOf(d2));
        
        l.setLieu_livraison(selectedOption);
        l.setComp(selectedOptionn); 
        l.setVille(combo2.getValue());
        if (Statut(d1)){
        l.setStatus_livraison("livrée");
        
        }else{
        l.setStatus_livraison("en cours");
        }
        l.setStatus_livraison(label10.getText());
       
           LivraisonService fs= new LivraisonService();
           fs.addLivraison(l);
    }
}
        }
    
    @FXML
    private void calculerFraisLivraison() {
        int frais1 = 7 ;
        int frais2 = 10 ;
        int frais3 = 15 ;
        
        if (combo.getValue().equals("Nord")) {
            label.setText(Integer.toString(frais1));
            l.setFrais_livraison(frais1);
        } else if (combo.getValue().equals("Milieu")) {
            label.setText(Integer.toString(frais2));
            l.setFrais_livraison(frais2);
        } else if (combo.getValue().equals("Sud")) {
            label.setText(Integer.toString(frais3));
            l.setFrais_livraison(frais3);
        } 
        
        
    }
    
    public boolean Statut(LocalDate d) {
        LocalDate today1 = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(d, today1);
        if (daysBetween >= 1) {
            label10.setText("livrée") ;
            return true;
        }
        else{
        return false;
        }
        
        
    }
}






   

    