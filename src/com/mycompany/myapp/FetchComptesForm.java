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
import entities.Compte;
import java.util.ArrayList;
import service.CompteService;

/**
 *
 * @author HP
 */
public class FetchComptesForm extends Form  {
      public  FetchComptesForm(Form previous){
           CompteService sc = new CompteService();
          ArrayList<Compte> array = sc.affichageComptes();
      }
}
