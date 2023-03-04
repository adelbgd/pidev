/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.produit;
import services.produitservice;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class ModifierprodController implements Initializable {

    produitservice ps = new produitservice();
    produit p = new produit();
    
    @FXML
    private TextField nom_p;
    @FXML
    private TextField description_p;
    @FXML
    private TextField statut_p;
    @FXML
    private TextField valeur_p;
    @FXML
    private DatePicker date_p;
    @FXML
    private Button select;
    
    @FXML
    private Button modifierproduit;
    @FXML
    private Button retour;
    @FXML
    private TextField selec;
    @FXML
    private ImageView img;
    @FXML
    private ComboBox<String> combo;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> items = FXCollections.observableArrayList("Accesoires","Vetements");

        // Set the items to the ComboBox
        combo.setItems(items); 
    } 
    
    void getProduit(produit p){
        LocalDate d = date_p.getValue(); 
// c.set(java.sql.Date.valueOf(d));
    nom_p.setText(p.getNom()); 
    description_p.setText(p.getDescription());
    statut_p.setText(p.getStatut());
    valeur_p.setText(Float.toString(p.getValeur()));
    selec.setText(p.getImage());
    
    }
    
      /**@FXML
    private void modifierproduit(ActionEvent event) {
        
        
        String nom = nom_prod.getText();
        String description = description_prod.getText();
        String statut= statut_prod.getText();
        Float valeur = Float.parseFloat(valeur_prod.getText());
        Integer id = Integer.parseInt(id_prod.getText());
        LocalDate date1= date_prod.getValue();
        // p.setDate(date.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
        Date date = format.parse(date1.toString());
        
        ps.updateproduit(id, nom, description, statut, valeur, date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
    }**/
    @FXML
    private void modifierproduit(ActionEvent event) {
        
        if (nom_p.getText().isEmpty() || statut_p.getText().isEmpty() || valeur_p.getText().isEmpty() ||  date_p.getValue() == null  || description_p.getText().isEmpty() ) {
             Alert alert = new Alert (Alert.AlertType.ERROR);
       alert.setTitle("Erreur  ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("veuillez remplir toutes les cases !!");
       alert.show();
        } 
        
        else{
        
        p.setNom(nom_p.getText());
        p.setDescription(description_p.getText());
        p.setStatut(statut_p.getText());
        p.setValeur(Float.parseFloat(valeur_p.getText()));
        
        LocalDate date1= date_p.getValue();
        p.setDate(java.sql.Date.valueOf(date1));
        p.setImage(selec.getText());
        // p.setDate(date.toString());
       // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       /** try {
        Date date = format.parse(date1.toString());
        
        p.setDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }**/
       
       if(combo.getValue().equals("Accesoires")){
           p.setId_catego(1);
           
        
        }else{
        
         p.setId_catego(2);
        
        }
        ps.updateproduit(p);
        
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Produit modifié ");
       alert.setHeaderText("ATTENTION !!");
       alert.setContentText("Produit modifié avec succés !!");
       alert.show();}
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
    }
    }
    
}
