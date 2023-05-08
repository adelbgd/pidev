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
         
         
        public UpdatCommandeForm(Form previous){
        setTitle("Mettre a jour");
        setLayout(BoxLayout.y());
        TextField tfId= new TextField("", "id");
        TextField tfprenom = new TextField("", "prenom");
        TextField tfnom = new TextField("", "nom");
        TextField tftel = new TextField("", "num");
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
        TextField tfcode = new TextField("", "code_postal");
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
   
    



