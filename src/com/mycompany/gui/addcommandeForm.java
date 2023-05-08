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
public class addcommandeForm extends Form{
    public addcommandeForm(Form previous){
        setTitle("add commande ");
        setLayout(BoxLayout.y());
        TextField tfprenom = new TextField("", "prenom");
        TextField tfnom = new TextField("", "nom");
        TextField tfnum = new TextField("", "num");
        
        TextField tfmail = new TextField("", "mail");
        ComboBox<String> regionComboBox = new ComboBox<>("Region");
regionComboBox.addItem("Region 1");
regionComboBox.addItem("Region 2");
regionComboBox.addItem("Region 3");
        ComboBox<String> villeComboBox = new ComboBox<>("Ville");
  villeComboBox.addItem("Ville 1");
  villeComboBox.addItem("Ville 2");
  villeComboBox.addItem("Ville 3");
        TextField tfrue = new TextField("", "rue");
        TextField tfcodepostal = new TextField("", "code_postal");
        Button ajouterbtn = new Button("ajouter");
        
       ajouterbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((tfprenom.getText().length()==0||tfnom.getText().length()==0||tfnum.getText().length()==0||tfmail.getText().length()==0||regionComboBox.getSelectedItem().length()==0||villeComboBox.getSelectedItem().length()==0||tfrue.getText().length()==0||tfnum.getText().length()==0 ))
               Dialog.show("Alert", "fill all the fields", new Command("OK"));
           else
           {
                      commandeservices sc = new commandeservices();
      
                   Commande c = new Commande(tfprenom.getText(), tfnom.getText(), Integer.parseInt(tfnum.getText()), tfmail.getText(), regionComboBox.getSelectedItem(), villeComboBox.getSelectedItem(), tfrue.getText(),  Integer.parseInt(tfcodepostal.getText()));
                   
                           boolean test = sc.AddCommande(c);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "commande enregistré avec succes", new Command("OK"));
                   }
                   
            }
               
               
              
                  });
                    addAll(tfprenom,tfnom,tfnum,tfmail,regionComboBox,villeComboBox,tfrue,tfcodepostal,ajouterbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
}
    
}
