/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Commande;
import com.mycompany.services.commandeservices;
import java.util.ArrayList;

/**
 *
 * @author adelb
 */
public class ListcommandeForm extends Form {

    public ListcommandeForm(Form previous) {
        commandeservices sc = new commandeservices();
        setTitle("List Commande");
        SpanLabel CommandeListSP = new SpanLabel();
        CommandeListSP.setText(sc.fetchCommandes().toString());
       ArrayList<Commande> commandes = (ArrayList<Commande>) sc.fetchCommandes();
       if (commandes != null) {
     System.out.println(sc.fetchCommandes());
        this.add(CommandeListSP);
    } else {
            System.out.println("list empty");
        } 
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    }
