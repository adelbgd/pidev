/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
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

/**
 *
 * @author azizl
 */
public class suppcommandeForm extends Form{
     public suppcommandeForm(Form previous){
         
        setTitle("Delete Pub");
        setLayout(BoxLayout.y());
        TextField tfId = new TextField("", "id");
        
       
        Button suppPubbtn = new Button("Supprimer");
        
   suppPubbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((tfId.getText().length()==0 ))
               Dialog.show("Alert", "fill all the fields", new Command("OK"));
           else
           {
                     commandeservices sc = new commandeservices();
                   Commande c =new Commande (Integer.parseInt(tfId.getText()));
                   
                           boolean test = sc.deletecommande(c);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "Commande supprimé avec succes", new Command("OK"));
                   }
                   
            }
               
               
              
                  });
                    addAll(tfId,suppPubbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
}
    
    
}