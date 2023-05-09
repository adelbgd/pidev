/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Compte;
import service.CompteService;

/**
 *
 * @author HP
 */
public class ModifierCompteForm extends SideMenuBaseForm{
    
         public  ModifierCompteForm(Resources res){
             
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        CompteService sc = new CompteService();
        Compte c1 = new Compte() ; 
        Compte c2 = sc.Detailcompte(1, c1) ;
             System.out.println(c2);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
         Container nbr_followers = BoxLayout.encloseY(
                        new Label("12", "CenterTitle"),
                        new Label("nbr followers", "CenterSubTitle")
                );
        nbr_followers.setUIID("nbr followers");
        Container nbr_followings = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label("nbr followings", "CenterSubTitle")
        );
        nbr_followings.setUIID("nbr followings");
        Container nbr_produits = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label("nbr produits", "CenterSubTitle")
        );
        nbr_produits.setUIID("nbr produits");
  Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Jennifer Wilson", "Title"),
                                    new Label("UI/UX Designer", "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(3, nbr_followers, nbr_followings , nbr_produits)
                );
     
        tb.setTitleComponent(titleCmp);
                        
       
      // khedemti
        TextField nom = new TextField("", "nom");
        TextField profile_image = new TextField("", "profile_image");
        TextField bio = new TextField("", "bio");
       
        
        //Button modifierbtn = new Button("modifier");
        FloatingActionButton modifierbtn = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD , "modifier");
       modifierbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
          
               
      
                   Compte c = new Compte(nom.getText(), profile_image.getText(), bio.getText());
                   
                           boolean test = sc.modifierCompte(c);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "commande enregistré avec succes", new Command("OK"));
                   }
                   
             
               
              
                  });
                    addAll(nom,profile_image,bio,modifierbtn);
       ///////// toufa lena           
            setupSideMenu(res);           
}    
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show(); //To change body of generated methods, choose Tools | Templates.
    }

   
}
