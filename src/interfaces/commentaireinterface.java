/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;
import java.util.List;
import model.commentaire;

/**
 *
 * @author adelb
 */
public interface commentaireinterface {
     //add
    public void addCommentaire(commentaire c);
    
    //list : select
    public List<commentaire> fetchCommentaire();
    
    //affectation
    public void deleteCommentaire(int id_com);
    public void updateCommentaire(commentaire c);
    public void rechercheCommentaire(int id_com);
    public void afficherCommentaire(int id_com);
    
}