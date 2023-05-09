/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

/**
 *
 * @author adelb
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.statics.statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceCommande {
    public static ServiceCommande instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceCommande getInstance() {
        if(instance == null )
            instance = new ServiceCommande();
        return instance ;
    }
    
    
    
    public ServiceCommande() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutCommande(Commande commande) {
        
        String url =statics.BASE_URL+"/addCommandeJSON/new?prenom="+commande.getPrenom()+"&nom="+commande.getNom()+"&num="+commande.getNum()+"&mail="+commande.getMail()+"&region="+commande.getRegion()+"&ville="+commande.getVille()+"&rue="+commande.getRue()+"&code_postal="+commande.getCode_postal(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Commande>affichageCommandes() {
        ArrayList<Commande> result = new ArrayList<>();
        
        String url = statics.BASE_URL+"/allCommandes";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCommandes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCommandes.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Commande re = new Commande();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String prenom = obj.get("prenom").toString();
                        
                        String nom = obj.get("nom").toString();
                        String mail = obj.get("mail").toString();
                        String num = obj.get("num").toString();
                        int numInt = Integer.parseInt(num);
                        String region = obj.get("region").toString();
                        String ville = obj.get("ville").toString();
                        String rue = obj.get("rue").toString();
                        float code = Float.parseFloat(obj.get("code_postal").toString());
                        
                        re.setId((int)id);
                        re.setPrenom(prenom);
                        re.setNom(nom);
                        re.setMail(mail);
                        re.setNum((int)num);
                        re.setRegion(region);
                        re.setVille(ville);
                        re.setRue(rue);
                        re.setCode_postal((int)code);
                        

                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
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
    public boolean deleteReclamation(int id ) {
        String url = statics.BASE_URL +"/deleteCommandeJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
   
    
}
