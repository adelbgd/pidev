/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.codename1.push.PushContent.setTitle;
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

/**
 *
 * @author azizl
 */
public class updatePubForm extends com.mycompany.myapp.SideMenuBaseForm {

    public updatePubForm(Resources res) {
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
                                new Label("Modifier votre publication", "Title")
                        )
                )
        );
        setupSideMenu(res);

        //  setTitle("Add Pub");
        setLayout(BoxLayout.y());
        TextField tfId = new TextField("", "id");
        TextField tfTitre = new TextField("", "titre_pub");
        TextField tfcontenu_pub = new TextField("", "contenu_pub");
        TextField tfimage = new TextField("", "image");

        Button Modifierbtn = new Button("Modifier");

        Modifierbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((tfId.getText().length() == 0 || tfTitre.getText().length() == 0 || tfcontenu_pub.getText().length() == 0)) {
                Dialog.show("Alert", "fill all the fields", new Command("OK"));
            } else {
                PublicationService sc = new PublicationService();
                Publication p = new Publication(Integer.parseInt(tfId.getText()), tfTitre.getText(), tfcontenu_pub.getText(), tfimage.getText());

                boolean test = sc.updatePub(p);
                if (!test) {
                    Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                } else {
                    Dialog.show("success", "publication modifié avec succes", new Command("OK"));
                }

            }

        });
        addAll(tfId, tfTitre, tfcontenu_pub, tfimage, Modifierbtn);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new gui.ListPubForm(res).show());
    }

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
