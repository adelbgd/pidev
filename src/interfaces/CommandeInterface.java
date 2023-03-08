/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;
import java.util.List;
import models.Commande;
import models.Livraison;

/**
 *
 * @author adelb
 */
public interface CommandeInterface {
     //add
    public void addCommande(Commande c);
    
    //list : select
    public List<Commande> fetchCommandes();
    
    //affectation
    public void affecterCommande(Commande c, Livraison l);
    public void deleteCommande(int id_commande);
    public void updateCommande(Commande C);
    public  Commande rechercherCommandebyid(int id_commande);
    public void afficherCommande(Commande c);
    public Commande readbyid(int id);
    public Commande getcommande(Commande c);
    public Commande rechercherCommandebynom(String nom_prenom);
    
}
