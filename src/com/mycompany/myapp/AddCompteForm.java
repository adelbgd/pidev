package com.mycompany.myapp;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Compte;
import service.CompteService;
/**
 *
 * @author adelb
 */
public class AddCompteForm extends SideMenuBaseForm{
    
    public  AddCompteForm(Resources res){
        
        setTitle("add compte ");
        setLayout(BoxLayout.y());
        TextField iduser = new TextField("", "id user");
        TextField nom = new TextField("", "nom");
        TextField profile_image = new TextField("", "profile_image");
        TextField bio = new TextField("", "bio");
           Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Button ajouterbtn = new Button("ajouter");
        
       ajouterbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((nom.getText().length()==0||profile_image.getText().length()==0||bio.getText().length()==0))
               Dialog.show("Alert", "fill all the fields", new Command("OK"));
           else
           {
                      CompteService sc = new CompteService();
      
                   Compte c = new Compte(Integer.parseInt(iduser.getText()) , nom.getText(), profile_image.getText(), bio.getText());
                   
                           boolean test = sc.ajoutCompte(c);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "commande enregistré avec succes", new Command("OK"));
                   }
                   
            }
               
               
              
                  });
                    addAll(nom,profile_image,bio,ajouterbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e-> new ProfileForm(res).show() );
                       
}

    @Override
    protected void showOtherForm(Resources res) {
       new ProfileForm(res).show();//To change body of generated methods, choose Tools | Templates.
    }
    
}