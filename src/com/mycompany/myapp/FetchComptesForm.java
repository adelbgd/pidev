package com.mycompany.myapp;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import entities.Compte;
import java.util.ArrayList;
import service.CompteService;

/**
 *
 * @author HP
 */public class FetchComptesForm extends SideMenuBaseForm {
    public FetchComptesForm(Resources res) {
        super(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Comptes");

        // Fetch comptes from service
        CompteService cs = new CompteService();
        ArrayList<Compte> comptes = cs.affichageComptes();

        // Add compte data to form
        for (Compte compte : comptes) {
            Container compteContainer = new Container(new BorderLayout());
            compteContainer.getStyle().setBorder(Border.createLineBorder(1, 0x999999));
            compteContainer.getStyle().setBgColor(0xEEEEEE);

            Label nomLabel = new Label("Nom: " + compte.getNom());
            Label followersLabel = new Label("Followers: " + compte.getNbr_Followers());
            Label followingsLabel = new Label("Followings: " + compte.getNbr_Followings());
            Label produitsLabel = new Label("Produits publiés: " + compte.getNbr_Produits_Publies());
            Label bioLabel = new Label("Bio: " + compte.getBio());

            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.addAll(nomLabel, followersLabel, followingsLabel, produitsLabel, bioLabel);

            compteContainer.add(BorderLayout.CENTER, labelsContainer);
            add(compteContainer);
        }

        // Add refresh button
        Button refreshButton = new Button("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                removeAll();
                revalidate();
                repaint();
                new FetchComptesForm(res).show();
            }
        });
        add(refreshButton);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new AddCompteForm(res).show();
    }
}



