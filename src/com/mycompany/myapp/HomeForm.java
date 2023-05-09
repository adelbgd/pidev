/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Compte;
import service.CompteService;
public class HomeForm extends Form{
    Form current;
    public HomeForm(Resources res){
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnaddcompte = new Button ("Add Compte");
        Button btnmodifiercompte = new Button ("modifier un Compte");
        TextField id = new TextField("", "id du compte");
        
        Button btnmsupprimercompte = new Button ("supprimer un Compte");
        Button btnmaffichercompte = new Button (" affichage d'un compte ");
        Button btnmtaffichercomptes = new Button ("tout les comptes ");
        
        
        
       //  Button btnListClient = new Button ("List Client");
         btnaddcompte.addActionListener(e -> new AddCompteForm(res).show());
        //  btnListClient.addActionListener(e -> new ListClientForm(current).show());
          
          //  Button btnListClient = new Button ("List Client");
        // btnmodifiercompte.addActionListener(e -> new ModifierCompteForm(res).show());
        //  btnListClient.addActionListener(e -> new ListClientForm(current).show());
        
          //  Button btnListClient = new Button ("List Client");
          
       btnmsupprimercompte.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((id.getText().length()==0))
               Dialog.show("Alert", "donner un identiant ", new Command("OK"));
           else
           {
                      CompteService sc = new CompteService();
      
                  
                   int idValue = Integer.parseInt(id.getText());
                           boolean test = sc.deleteCompte(idValue);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "commande enregistré avec succes", new Command("OK"));
                   }
                   
            }
               
               
              
                  });
        //  btnListClient.addActionListener(e -> new ListClientForm(current).show());
        
          
          //  Button btnListClient = new Button ("List Client");
         btnmaffichercompte.addActionListener(e -> new AddCompteForm(res).show());
        //  btnListClient.addActionListener(e -> new ListClientForm(current).show());
        
          
          //  Button btnListClient = new Button ("List Client");
         btnmtaffichercomptes.addActionListener(e -> new FetchComptesForm(res).show());
        //  btnListClient.addActionListener(e -> new ListClientForm(current).show());
          addAll(btnaddcompte ,btnmodifiercompte,id , btnmsupprimercompte,btnmaffichercompte,btnmtaffichercomptes );
    }
    
}
