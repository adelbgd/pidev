/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
public class HomeForm extends Form{
    Form current;
    public HomeForm(){
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnAddCommande = new Button ("Add Commande");
        Button btnListCommande = new Button ("List Commande");
        Button btnModifier = new Button ("Update Commande");
        Button btndelete = new Button ("Delete Commande");
        Button btnmap = new Button ("Map");
          btnAddCommande.addActionListener(e -> new addcommandeForm(current).show());
          btnListCommande.addActionListener(e -> new ListcommandeForm(current).show());
          btnModifier.addActionListener(e -> new UpdatCommandeForm(current).show());
          btndelete.addActionListener(e -> new suppcommandeForm(current).show());
          btnmap.addActionListener(e -> new MapForm());
          addAll(btnAddCommande,btnListCommande,btnModifier,btndelete,btnmap);
    }
    
}
