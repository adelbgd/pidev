/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Date;
import java.util.List;
import model.Publication;

/**
 *
 * @author azizl
 */
public interface publicationinterface {
    
      //add
    public void addPublication(Publication p);
    
    //list : select
     
    public List<Publication> rechercherPublication(int id_user);
public List<Publication> rechercherPublication(String nom, String prenom);
public List<Publication> afficherPublication();

    
    //affectation
   public void ajouterPublication(Publication p );
 public void supprimerPublication (int  id_publication) ;
public void modifierTitrePublication(int id_publication, String titre_publication);
public void modifierDatePublication(int id_publication, Date date_publication);
}