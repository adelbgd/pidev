/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Compte;
import statics.statics ; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author HP
 */
public class CompteService {
    public boolean r ; 
    //singleton 
    public static CompteService instance = null ;
    


    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static CompteService getInstance() {
        if(instance == null )
            instance = new CompteService();
        return instance ;
    }
    
    
    
    public CompteService() {
        req = new ConnectionRequest();
        
        
    }
     //ajout 
    public boolean ajoutCompte(Compte c) {
        
        String url =statics.BASE_URL+"/add_compte_json?nom="+c.getNom()+"&profile_image="+c.getProfile_Image()+"&bio="+c.getBio(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
               r = req.getResponseCode() == 200; 
            req.removeResponseCodeListener(this);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return r; 
        
    }
    
        public ArrayList<Compte>affichageComptes() {
        ArrayList<Compte> result = new ArrayList<>();
        
        String url = statics.BASE_URL+"/allcomptes";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Compte c = new Compte();
                        
                        //dima id fi codename one float 5outhouha
                        float id= Float.parseFloat(obj.get("id").toString());
                        float id_utilisateur = Float.parseFloat(obj.get("id_utilisateur").toString());
                        
                        String nom = obj.get("nom").toString();
                        float nb_followers = Float.parseFloat(obj.get("nbr_followers").toString());
                        float nb_followings = Float.parseFloat(obj.get("nbr_followings").toString());
                        float nb_pp = Float.parseFloat(obj.get("nbr_produits_publies").toString());
                        float banned = Float.parseFloat(obj.get("banned").toString());
                        String bio = obj.get("bio").toString();
                        String p_i = obj.get("profile_image").toString();
                        System.out.println(nb_followers);
                        System.out.println(nb_followings);
                        System.out.println(banned);
                        System.out.println(bio);
                        System.out.println(p_i);
                        c.setId((int)id);
                        c.setId_utilisateur((int)id_utilisateur);
                        c.setNom(nom);
                        c.setNbr_Followers((int)nb_followers);
                        c.setNbr_Followings((int)nb_followings);
                        c.setNbr_Produits_Publies((int)nb_pp);
                        c.setBanned((int)banned);
                        c.setBio(bio);
                       
                        
                        //Date 
                      //  String DateConverter =  obj.get("date_de_creation").toString().substring(obj.get("date_de_creation").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        //System.out.println(DateConverter);
                        System.out.println(nom);
                      //  Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                       // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                       //String dateString = formatter.format(currentTime);
                       // c.setDate_de_creation(currentTime); 
                        
                        //insert data into ArrayList result
                        result.add(c);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
       //Delete 
    public boolean deleteCompte(int id ) {
        String url = statics.BASE_URL +"/deletecompteJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                      r = req.getResponseCode() == 200; 
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  r;
    }
        //Update 
    public boolean modifierCompte(Compte c) {
        
        String url = statics.BASE_URL +"/updatecompteJSON/"+1+"?nom="+c.getNom()+"&profile_image="+c.getProfile_Image()+"&bio="+c.getBio();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                r = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return r;
        
    }
     public Compte Detailcompte( int id , Compte c) {
        
          // Create the URL for the REST API endpoint
    String url = statics.BASE_URL + "/CompteJSONN/" + id;
    // Create a new request object with the URL
    req.setUrl(url);

    // Add a response listener to the request
    req.addResponseListener((evt) -> {
        // Get the response data as a string
        String str = new String(req.getResponseData());

        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                 c.setNom(obj.get("nom").toString());
                c.setNbr_Followers(Integer.parseInt(obj.get("nbr_followers").toString()));
                c.setNbr_Followings(Integer.parseInt(obj.get("nbr_followings").toString()));
                c.setNbr_Produits_Publies(Integer.parseInt(obj.get("nbr_produits_publies").toString()));
                c.setBio(obj.get("bio").toString());
                
            }catch(IOException ex) {
                System.out.println("error related to sql 🙁 "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        });
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return c;
        
        
    }
    
    
     

    
    
    
    
    
    
}
