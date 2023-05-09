/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Commande;
import com.mycompany.services.commandeservices;




     public class UpdatCommandeForm extends Form{
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
         
        public UpdatCommandeForm(Form previous){
        setTitle("Mettre a jour");
        setLayout(BoxLayout.y());
        TextField tfId= new TextField("", "Id");
        TextField tfprenom = new TextField("", "Prenom");
        TextField tfnom = new TextField("", "Nom");
        TextField tftel = new TextField("", "Numéro télephone");
        TextField tfmail = new TextField("", "Email");
       ComboBox<String> villeComboBox = new ComboBox<>();
        villeComboBox.addItem("Chargia");
        villeComboBox.addItem("Ennasr");
        villeComboBox.addItem("Soukra");
        ComboBox<String> regionComboBox = new ComboBox<>();
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
        TextField tfcode = new TextField("", "Code postal");
        Button Modifierbtn = new Button("Modifier");
        
        Modifierbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((tfId.getText().length()==0||tfprenom.getText().length()==0||tfnom.getText().length()==0||tftel.getText().length()==0||tfmail.getText().length()==0||regionComboBox.getSelectedItem().length()==0||villeComboBox.getSelectedItem().length()==0||tfrue.getText().length()==0 ||tfcode.getText().length()==0 ))
               Dialog.show("Alert", "fill all the fields", new Command("OK"));
           else
           {
                      commandeservices sc = new commandeservices();
                       Commande c = new Commande(Integer.parseInt(tfId.getText()),tfprenom.getText(), tfnom.getText(),Integer.parseInt(tftel.getText()), tfmail.getText(),regionComboBox.getSelectedItem(), villeComboBox.getSelectedItem(), tfrue.getText(),  Integer.parseInt(tfcode.getText()));

                   
                           boolean test = sc.UpdateCommande(c);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "Commande modifié avec succes", new Command("OK"));
                   }
                   
            }
               
               
              
                  });
                    addAll(tfId,tfprenom,tfnom,tftel,tfmail,regionComboBox,villeComboBox,tfrue,tfcode,Modifierbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
}
        
        
        
        
}
   
    



