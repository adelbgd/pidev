/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.client;


/**
 *
 * @author admin
 */
public interface clientInterfaces{
    
    
    public void addclient(client c);
    public void modifierclient(int id ,String nom, String prenom);

    /**
     *
     * @param nom
     */
    public void supprimerclient(String nom );
    public void afficherclient(client c);
    
    
    //list : select
    public List<client> fetchclient();
    
    //affectation

   
 
    
}
