/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import entities.Publication;
import service.PublicationService;

public class addpubForm extends  com.mycompany.myapp.SideMenuBaseForm {
    
    private static String[] badwords = {"shit", "merde", "putin", "fuck"};

    public addpubForm(Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Ajouter une publication", "Title")
                        )
                )
        );
            setupSideMenu(res);           

        tb.setTitleComponent(titleCmp);
        // setTitle("Add Pub");
        setLayout(BoxLayout.y());
        TextField tfTitre = new TextField("", "titre_pub");
        TextField tfcontenu_pub = new TextField("", "contenu_pub");
        TextField tfimage = new TextField("", "image");

        Button Ajouterbtn = new Button("Ajouter");

        Ajouterbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length() == 0 || tfcontenu_pub.getText().length() == 0)) {
                    Dialog.show("Alert", "fill all the fields", new Command("OK"));
                } else {
                    PublicationService sc = new PublicationService();
                    Publication p = new Publication(tfTitre.getText(), tfcontenu_pub.getText(), tfimage.getText());

                    boolean containsBadWords = false;
                    for (String badword : badwords) {
                        if (p.getContenu_pub().contains(badword) || p.getTitre_pub().contains(badword)) {
                            containsBadWords = true;
                            Dialog.show("Alerte", "Le contenu de la publication contient un mot inapproprié : " + badword, "OK", null);
                            break;
                        }
                    }

                    if (!containsBadWords) {
                        boolean test = sc.AjoutPublication(p);
                        if (!test) {
                            Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                        } else {
                            Dialog.show("success", "publication enregistrée avec succès", new Command("OK"));
                        }
                    }
                }
            }
        });

        addAll(tfTitre, tfcontenu_pub, tfimage, Ajouterbtn);
           getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->new gui.ListPubForm(res).show());   }

          //addpubForm(Resources res) {
        //   throw new addpubForm("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

  //  @Override
   // protected void showOtherForm(Resources res) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }

