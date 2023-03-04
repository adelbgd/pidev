/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.ByteArrayOutputStream;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.String.format;
import static java.lang.String.format;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.produit;
import services.produitservice;
import util.MyConnection;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class FXMLController implements Initializable  {
    
    produitservice ps = new produitservice();
    
    @FXML
    private TextField nom_prod;
    @FXML
    private TextField description_prod;
    @FXML
    private TextField statut_prod;
    @FXML
    private TextField valeur_prod;
    @FXML
    private DatePicker date_prod;
    @FXML
    private Button ajouter_produit;
    @FXML
    private Button retour;
    @FXML
    private Button select;
    //@FXML
    //private Button recommendations;
    @FXML
    private ImageView img;
    
   // @FXML
    // private Label label;
    
    String s;
    @FXML
    private TextField selec;
    @FXML
    private Label label;
    @FXML
    private Button recommendations;
    @FXML
    private Button valider;
    @FXML
    private ComboBox<String> combo;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<String> items = FXCollections.observableArrayList("Accesoires","Vetements");

        // Set the items to the ComboBox
        combo.setItems(items);  
    }    

    @FXML
    private void ajouter_produit(ActionEvent event) throws FileNotFoundException, IOException {
        produit p = new produit();
        if (nom_prod.getText().isEmpty() || statut_prod.getText().isEmpty() || valeur_prod.getText().isEmpty() || description_prod.getText().isEmpty() || date_prod.getValue() == null   ){
             Alert alert = new Alert (Alert.AlertType.ERROR);
       alert.setTitle("Erreur  ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        }else{
        p.setNom(nom_prod.getText());
        p.setDescription(description_prod.getText());
        p.setStatut(statut_prod.getText());
        p.setValeur(Float.parseFloat(valeur_prod.getText()));
        p.setImage(selec.getText());
        
        LocalDate date1= date_prod.getValue();
        LocalDate today = LocalDate.now();
        if (date1.isAfter(today)  ){
         Alert alert = new Alert (Alert.AlertType.ERROR);
       alert.setTitle("Erreur  ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez saisir une date valide !!");
       alert.show();
        
        
        }else{
        // p.setDate(date.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
        Date date = format.parse(date1.toString());
        
        p.setDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        }
        if(combo.getValue().equals("Accesoires")){
           p.setId_catego(1);
           
        
        }else{
        
         p.setId_catego(2);
        
        }
        
        
    
        
        
        
        ps.addproduit(p);
        
        
        //String code = Integer.toString(p.getId_prod()); // Code à barres du produit
        
        String code = Integer.toString(p.getId_prod())+"12345678910"; // Code à barres du produit
        
        if (code.length() != 12) {
            JOptionPane.showMessageDialog(null, "Le code à barres doit contenir exactement 12 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Barcode barcode = BarcodeFactory.createEAN13(code); // Créer un code à barres EAN-13
            barcode.setDrawingText(false); // Ne pas afficher le code à barres en-dessous
            ImageIcon icon = new ImageIcon(BarcodeImageHandler.getImage(barcode)); // Créer une image du code à barres
            JLabel label = new JLabel(icon); // Créer une étiquette pour afficher l'image
            JFrame frame = new JFrame(); // Créer une fenêtre
            frame.add(label); // Ajouter l'étiquette à la fenêtre
            frame.pack(); // Adapter la taille de la fenêtre à celle de l'image
            frame.setVisible(true); // Afficher la fenêtre
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        

        
        
        
       
        
       
        
        
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Produit ajouté ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Produit ajouté avec succés !!");
       alert.show();}
        
    }
    
   
    
    @FXML
    public void select(ActionEvent e) throws IOException{
        
         FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Sélectionner une photo");
         fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Fichiers d'image", "*.png", "*.jpg", "*.gif"));

         
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        String imagePath = selectedFile.toURI().toString();
        //String imagePath = selectedFile.getAbsolutePath();
        selec.setText(selectedFile.getName());
        //s=imagePath;
        Image image = new Image(imagePath);
        img.setImage(image);
        String S1="C:\\xampp2\\htdocs\\img\\"+selec.getText();
        String S2=imagePath.substring(6);
        File sourcefile = new File (S2);  //C:\\Users\\Walid\\OneDrive\\Bureau\\dia.png
        File destinationfile = new File (S1);
        Files.copy(sourcefile.toPath(), destinationfile.toPath(),StandardCopyOption.REPLACE_EXISTING);
        
        
        
        /**String S1="C:\\xampp1\\htdocs\\img";
        File root = new File (imagePath);
        File fileList[]= root.listFiles();
        int filecount=0;
        for (File fileobject : fileList){
          filecount++;
        }
        
        try{
            FileInputStream inputStream = new FileInputStream(imagePath);
            FileOutputStream outputStream = new FileOutputStream (S1);
            int data = inputStream.read();
            while(data != -1){
               outputStream.write(data);
               data= inputStream.read();
            
            }
            inputStream.close();
            outputStream.close();
        
        
        }catch(Exception exe){
            exe.printStackTrace();
        
        
        }**/
        
        
        
    

     }
    }
    
    
    
    
    
    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/home.fxml"));

    // Create a new stage for the new view
    Stage stage = new Stage();
    stage.setScene(new Scene(loader.load()));

    // Show the new stage and hide the current stage
    stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
        
        
        
    }

    @FXML
    private void recommendations(ActionEvent event) {
    
    Float val= Float.parseFloat(valeur_prod.getText());
    List<String> recommendations = new ArrayList<>();
    List<produit> allProducts = ps.fetchProduits(); // récupère tous les produits

    // Parcours de tous les produits
    for (produit p : allProducts) {
        if (p.getValeur() != val); {    // on ne recommande pas le même produit
            if (areSimilarProducts(p.getValeur(), Float.parseFloat(valeur_prod.getText()))) { // si les produits sont similaires
                recommendations.add(p.getNom()); // ajoute le produit à la liste des recommandations
            }
        }
    }
    label.setText(recommendations.toString());
    
}

private boolean areSimilarProducts(Float a, Float b) {
    // Implémentation de l'algorithme de comparaison de produits similaires
    // Ici, on peut comparer différents attributs des produits, comme leur catégorie, leur marque, etc.
    // Pour simplifier, nous supposons que deux produits sont similaires s'ils ont le même nom.
    int result = Float.compare(a, b);
    if (result < 0) {
        return false;
    } else if (result > 0) {
        return false;
    } else {
    return true;
}
    
    
}



}
