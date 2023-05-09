/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;
import entities.Publication;

import service.PublicationService;
import java.util.ArrayList;
/**
 *
 * @author azizl
 */
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import java.util.ArrayList;


 public class ListPubForm extends com.mycompany.myapp.SideMenuBaseForm {

    public ListPubForm(Resources res) {
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
                    new Label("Publications", "Title")
                )
            )
        );
        tb.setTitleComponent(titleCmp);

        PublicationService sc = new PublicationService();
        SpanLabel PublicationListSP = new SpanLabel();
        PublicationListSP.setText(sc.fetchPublications().toString());
        ArrayList<Publication> result = (ArrayList<Publication>) sc.fetchPublications();
        if (result != null) {
            System.out.println(sc.fetchPublications());
            this.add(PublicationListSP);
        } else {
            System.out.println("list empty");
        } 
        
        Button addPublicationButton = new Button("Ajouter publication");
        addPublicationButton.addActionListener(e -> {
            new gui.addpubForm(res).show();
        });
        
        Button modifyButton = new Button("Modifier");
        modifyButton.addActionListener(e -> {
            new gui.updatePubForm(res).show();
        });
        
        Button deleteButton = new Button("Supprimer");
        deleteButton.addActionListener(e -> {
            new deleteForm(res).show();
        });
        
        this.addAll(addPublicationButton, modifyButton, deleteButton);
        
        setupSideMenu(res);
    }   

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}

