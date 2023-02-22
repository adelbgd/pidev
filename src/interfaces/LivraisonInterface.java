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
public interface LivraisonInterface {
     //add
    public void addLivraison(Livraison l);
    
    //list : select
    public List<Livraison> fetchLivraisons();
    
    //affectation
    public void affecterLivraison(Livraison l,Commande c);
    public void deleteLivraison(int id_livraiason);
    public void updateLivraison(Livraison c);
    public Livraison rechercherLivraisonbyid(int id_livraison);
    public void afficherLivraison(Livraison l);
    
}
