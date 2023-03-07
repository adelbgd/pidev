/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.SQLException;
import java.util.List;
import models.Reclamation;

/**
 *
 * @author user
 */
public interface ReclamationInterfaces {

    public void ajouter(Reclamation r) throws SQLException;

    public void modifier(Reclamation r) throws SQLException;

    public void supprimer(Reclamation r) throws SQLException;

    public List<Reclamation> recuperer() throws SQLException;

    public Reclamation recupererSingel(int id_reclamation) throws SQLException;

}
