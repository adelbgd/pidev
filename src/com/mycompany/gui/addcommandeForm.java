/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.NetworkEvent;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Commande;
import com.mycompany.services.commandeservices;

/**
 *
 * @author adelb
 */
public class addcommandeForm extends Form {
    
     private double calculateShippingCost(String selectedRegion) {
    double shippingCost = 0.150;
     double distariana = 29;           
     double distbeja = 119;
     double distbenarous = 6.3; 
     double distbizerte = 84; 
     double distgabes = 406; 
     double distgafsa = 336; 
     double distjendouba = 163; 
     double distkairouan = 153; 
     double distkassrine = 290; 
     double distkebeli = 501; 
     double distkef = 175; 
     double distmahdia = 201; 
     double distmanouba = 21; 
     double distmednine = 479; 
     double distmonastire = 161; 
     double distnabeul = 60; 
     double distsfax = 261; 
     double distsidibou = 260; 
     double distsiliana = 122; 
     double distsousse = 139; 
     double disttata = 529; 
     double disttozer = 428; 
     double disttunis = 17; 
     double distzagh = 47;
    
     switch (selectedRegion) {
        case "Ariana":
            shippingCost = distariana*shippingCost;
            break;
        case "Béja":
            shippingCost = distbeja*shippingCost;
            break;
        case "Ben Arous":
            shippingCost = distbenarous*shippingCost;
            break;
        case "Bizerte":
            shippingCost = distbizerte*shippingCost;
            break;
            case "Gabès":
            shippingCost = distgabes*shippingCost;
            break;
            case "Gafsa":
            shippingCost = distgafsa*shippingCost;
            break;
            case "Jendouba":
            shippingCost = distjendouba*shippingCost;
            break;
            case "Kairouan":
            shippingCost = distkairouan*shippingCost;
            break;
            case "Kasserine":
            shippingCost = distkassrine*shippingCost;
            break;
            case "Kébili":
            shippingCost = distkebeli*shippingCost;
            break;
            case "Kef":
            shippingCost = distkef*shippingCost;
            break;
            case "Mahdia":
            shippingCost = distmahdia*shippingCost;
            break;
            case "Manouba":
            shippingCost = distmanouba*shippingCost;
            break;
            case "Médenine":
            shippingCost = distmednine*shippingCost;
            break;
            case "Monastir":
            shippingCost = distmonastire*shippingCost;
            break;
            case "Nabeul":
            shippingCost = distnabeul*shippingCost;
            break;
            case "Sidi Bouzid":
            shippingCost = distsidibou*shippingCost;
            break;
            case "Siliana":
            shippingCost = distsiliana*shippingCost;
            break;
            case "Sousse":
            shippingCost = distsousse*shippingCost;
            break;
            case "Tataouine":
            shippingCost = disttata*shippingCost;
            break;
            case "Tozeur":
            shippingCost = disttozer*shippingCost;
            break;
            case "Tunis":
            shippingCost = disttunis*shippingCost;
            break;
            case "Zaghouan":
            shippingCost = distzagh*shippingCost;
            break;
            
        default:
            break;
    }

    return shippingCost;
}

    public addcommandeForm(Form previous) {
        setTitle("add commande ");
        setLayout(BoxLayout.y());
        TextField tfprenom = new TextField("", "Prenom");
       
        
        
    TextField tfnom = new TextField("", "Nom");
    
        TextField tfnum = new TextField("", "Numéro télephone");
       
        TextField tffrais = new TextField("", "frais");
        TextField tfmail = new TextField("", "Email");
       
        ComboBox<String> villeComboBox = new ComboBox<>("", "Ville");
        villeComboBox.addItem("Chargia");
        villeComboBox.addItem("Ennasr");
        villeComboBox.addItem("Soukra");
        ComboBox<String> regionComboBox = new ComboBox<>("", "Région");
        regionComboBox.addItem("Ariana");
        regionComboBox.addItem("Béja");
        regionComboBox.addItem("Ben Arous");
        regionComboBox.addItem("Bizerte");
        regionComboBox.addItem("Gabès");
        regionComboBox.addItem("Gafsa");
        regionComboBox.addItem("Jendouba");
        regionComboBox.addItem("Kairouan");
        regionComboBox.addItem("Kasserine");
        regionComboBox.addItem("Kébili");
        regionComboBox.addItem("Kef");
        regionComboBox.addItem("Mahdia");
        regionComboBox.addItem("Manouba");
        regionComboBox.addItem("Médenine");
        regionComboBox.addItem("Monastir");
        regionComboBox.addItem("Nabeul");
        regionComboBox.addItem("Sidi Bouzid");
        regionComboBox.addItem("Siliana");
        regionComboBox.addItem("Sousse");
        regionComboBox.addItem("Tataouine");
        regionComboBox.addItem("Tozeur");
        regionComboBox.addItem("Tunis");
        regionComboBox.addItem("Zaghouan");
        
        
        
      regionComboBox.addActionListener(evt -> {
    // Get the selected option from the first ComboBox
    String selectedOption = regionComboBox.getSelectedItem();
    double shippingCost = calculateShippingCost(selectedOption);
    tffrais.setText(Double.toString(shippingCost));

    // Dynamically set the options of the second ComboBox
    if (selectedOption.equals("Ariana")) {
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.addItem("Chargia");
               villeComboBox.addItem("Ennasr");
               villeComboBox.addItem("Soukra");
    }
            if (selectedOption.equals("Béja")) {

                villeComboBox.getModel().removeItem(0);
                villeComboBox.getModel().removeItem(0);
                villeComboBox.getModel().removeItem(0);
                
                villeComboBox.addItem("Amdoun");
                villeComboBox.addItem("Goubellat");
                villeComboBox.addItem("Nefza");

            }
            if (selectedOption.equals("Ben Arous")) {
                villeComboBox.getModel().removeItem(0);
                villeComboBox.getModel().removeItem(0);
                villeComboBox.getModel().removeItem(0);
                
                villeComboBox.addItem("Borj Cedria");
                villeComboBox.addItem("Boumhal");
                villeComboBox.addItem("Boukarnine");

            }
            if (selectedOption.equals("Bizerte")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Ghar El Melh");
                villeComboBox.addItem("Mateur");
                villeComboBox.addItem("Menzel Borgiba");

            }
            if (selectedOption.equals("Gabès")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("EL Hamma");
                villeComboBox.addItem("El Metouia");
                villeComboBox.addItem("Matmata");

            }
            if (selectedOption.equals("Gafsa")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Belkhyr");
                villeComboBox.addItem("Metlaoui");
                villeComboBox.addItem("Redeyef");

            }
            if (selectedOption.equals("Jendouba")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Ain Drahem");
                villeComboBox.addItem("Bou Salem");
                villeComboBox.addItem("Fernena");

            }
            if (selectedOption.equals("Kairouan")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Bou Hajla");
                villeComboBox.addItem("Chebika");
                villeComboBox.addItem("Hafouz");

            }
            if (selectedOption.equals("Kasserine")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Sbeitla");
                villeComboBox.addItem("Thala");
                villeComboBox.addItem("Feriana");

            }
            if (selectedOption.equals("Kébili")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Douz");
                villeComboBox.addItem("El Faouar");
                villeComboBox.addItem("Souk El Ahad");

            }
            if (selectedOption.equals("Kef")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Dahmani");
                villeComboBox.addItem("Nebeur");
                villeComboBox.addItem("Tajerouine");

            }
            if (selectedOption.equals("Mahdia")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Bou Merdes");
                villeComboBox.addItem("Chorbane");
                villeComboBox.addItem("Chebba");

            }
            if (selectedOption.equals("Manouba")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Douar Hicher");
                villeComboBox.addItem("Mornaguia");
                villeComboBox.addItem("Borj El Amri");

            }
            if (selectedOption.equals("Médenine")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Zarzis");
                villeComboBox.addItem("Remada");
                villeComboBox.addItem("Djerba Midoun");

            }
            if (selectedOption.equals("Monastir")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Hammamet");
                villeComboBox.addItem("Kelibia");
                villeComboBox.addItem("Haouaria");

            }
            if (selectedOption.equals("Sfax")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Agareb");
                villeComboBox.addItem("Gremda");
                villeComboBox.addItem("Kerkennah");

            }
            if (selectedOption.equals("Sidi Bouzid")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Mezzouna");
                villeComboBox.addItem("Meknassy");
                villeComboBox.addItem("Jelma");

            }
            if (selectedOption.equals("Siliana")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);

                villeComboBox.addItem("Makthar");
                villeComboBox.addItem("Gaâfour");
                villeComboBox.addItem("Boulhem");

            }
            if (selectedOption.equals("Sousse")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0); 

                villeComboBox.addItem("Bouficha");
                villeComboBox.addItem("Enfidha");
                villeComboBox.addItem("Hammam Sousse");

            }
            if (selectedOption.equals("Tataouine")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0); 

                villeComboBox.addItem("Douiret");
                villeComboBox.addItem("Dehiba");
                villeComboBox.addItem("Remada");

            }
            if (selectedOption.equals("Tozeur")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0); 

                villeComboBox.addItem("Degache");
                villeComboBox.addItem("Metlaoui");
                villeComboBox.addItem("Nefta");

            }
            if (selectedOption.equals("Tunis")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0); 

                villeComboBox.addItem("Carthage");
                villeComboBox.addItem("El Aouina");
                villeComboBox.addItem("La Marsa");

            }
            if (selectedOption.equals("Zaghouan")) {

                villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0);
               villeComboBox.getModel().removeItem(0); 

                villeComboBox.addItem("Bir Salah");
                villeComboBox.addItem("Nadhour");
                villeComboBox.addItem("Zriba");

            }
        });
        TextField tfrue = new TextField("", "Rue");
        TextField tfcodepostal = new TextField("", "Code postal");
        Button ajouterbtn = new Button("ajouter");
        
        ajouterbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
    String prenom = tfprenom.getText().trim();
    String nom = tfnom.getText().trim();
    String rue = tfrue.getText().trim();
    String num = tfnum.getText().trim();

    // validate prenom and nom fields
    if (prenom.isEmpty() || nom.isEmpty()) {
        Dialog.show("Alert", "Please enter a valid name", new Command("OK"));
        return;
    }

    // validate rue field
    if (rue.isEmpty()) {
        Dialog.show("Alert", "Please enter a valid street", new Command("OK"));
        return;
    }
    // validate rue field
    if (tfmail.getText().isEmpty()) {
        Dialog.show("Alert", "Please enter a valid mail", new Command("OK"));
        return;
    }
    // validate rue field
    if (tfcodepostal.getText().isEmpty()) {
        Dialog.show("Alert", "Please enter a valid Code postal", new Command("OK"));
        return;
    }

   

    // validate other fields
    if ( regionComboBox.getSelectedItem() == null || villeComboBox.getSelectedItem() == null) {
        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        return;
    }

    // create Commande object and add it to database
    commandeservices sc = new commandeservices();
    Commande c = new Commande(prenom, nom, Integer.parseInt(num), tfmail.getText(), regionComboBox.getSelectedItem(), villeComboBox.getSelectedItem(), rue, Integer.parseInt(tfcodepostal.getText()));
    boolean test = sc.AddCommande(c);
    if (!test) {
        Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
    } else {
        Dialog.show("success", "commande enregistré avec succes", new Command("OK"));
    }
});

addAll(tfprenom, tfnom, tfnum, tfmail, regionComboBox, villeComboBox, tffrais, tfrue, tfcodepostal, ajouterbtn);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }


   
}
