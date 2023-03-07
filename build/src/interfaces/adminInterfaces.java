/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.admin;
import models.client;

/**
 *
 * @author user
 */
public interface adminInterfaces {

    public void ajouter(admin am);

    public void modifier(String cin, String nom, String prenom, String email, String pwd);

    public void supprimer(String cin);

    public List<admin> getall();

    public admin getOneById(String cin);

}
