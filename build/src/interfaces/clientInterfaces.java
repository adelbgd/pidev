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
    public void modifierclient( int id,String nom, String prenom,long num_tel, String adresse,String sexe,String mail,String password);
    public void supprimerclient(int id);
    public client getOneById(int id);
    public List<client> getall();

   
 
    
}
