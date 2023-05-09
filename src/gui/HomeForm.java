/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class HomeForm extends Form {

    Form current;

    public HomeForm(Resources res) {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnAddPub = new Button("Add Pub");
        Button btnListPub = new Button("List Pub");
        Button btnModifPub = new Button("Modifier Pub");
        Button btnSuppPub = new Button("Supprimer Pub");
        btnAddPub.addActionListener(e -> new addpubForm(res).show());
        btnListPub.addActionListener(e -> new ListPubForm(res).show());
        btnModifPub.addActionListener(e -> new updatePubForm(res).show());
        btnSuppPub.addActionListener(e -> new deleteForm(res).show());
        addAll(btnAddPub, btnListPub, btnModifPub, btnSuppPub);
    }

}
