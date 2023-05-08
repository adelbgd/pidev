package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commande;
import com.mycompany.statics.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 *
 * @author user
 */
public class commandeservices {

    public boolean resultOK;
    ConnectionRequest req;
    static commandeservices instance;
    ArrayList<Commande> result = new ArrayList<>();

    public commandeservices() {
        req = new ConnectionRequest();
    }

    public static commandeservices getInstance() {

        if (instance == null) {
            instance = new commandeservices();
        }

        return instance;
    }

    public boolean AddCommande(Commande c) {
         String url = Statics.BASE_URL + "/add_commande_json?prenom="+c.getPrenom()+"&nom="+c.getNom()+"&num="+c.getNum()+"&mail="+c.getMail()+"&region="+c.getRegion()+"&ville="+c.getVille()+"&rue="+c.getRue()+"&code_postal="+c.getCode_postal();
        System.out.println( url );
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
            resultOK = req.getResponseCode() == 200 ; 
            req.removeResponseCodeListener(this);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK; 
    }


public List<Commande> fetchCommandes() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/affichageJSON";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = (ArrayList<Commande>) parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    //Parse
    public List<Commande> parseTasks(String jsonText) {

        //var
        result = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {

                Commande c = new Commande();
              if (item.get("id")!= null) {
              c.setId((int) (double) item.get("id"));
                   }
                c.setPrenom((String) item.get("prenom"));
                c.setNom((String) item.get("nom"));
                if (item.get("num")!= null) {
              c.setNum((int) (double) item.get("num"));
                   }
                c.setMail((String) item.get("mail"));
                c.setRegion((String) item.get("region"));
                c.setVille((String) item.get("ville"));
                c.setRue((String) item.get("rue"));
                if (item.get("code_postal")!= null) {
                c.setCode_postal((int) (double) item.get("code_postal"));
                   }
               
                result.add(c);
            }

        } catch (IOException ex) {
        }

        //End
        return result;
    }
   
     public boolean UpdateCommande(Commande c) {
    String url = Statics.BASE_URL + "/update_commande_JSON?id_commande=" + c.getId()+"&prenom="+c.getPrenom()+"&nom="+c.getNom()+"&num="+c.getNum()+"&mail="+c.getMail()+"&region="+c.getRegion()+"&ville="+c.getVille()+"&rue="+c.getRue()+"&code_postal="+c.getCode_postal(); 
    req.setUrl(url);
     System.out.println(url);
    req.setPost(false);
    req.addArgument("id_commande", String.valueOf(c.getId()));
     req.addArgument("prenom", c.getPrenom());
     req.addArgument("nom", c.getNom());
   req.addArgument("num", String.valueOf(c.getNum()));
    req.addArgument("mail", c.getMail());
    req.addArgument("region", c.getRegion());
    req.addArgument("ville", c.getVille());
    req.addArgument("rue", c.getRue());
    req.addArgument("code_postal", String.valueOf(c.getCode_postal()));

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
    public boolean deletecommande(Commande c) {

        String url = Statics.BASE_URL + "/deletecommandeJSON?id=" + c.getId();

      System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
            resultOK = req.getResponseCode() == 200 ; 
            req.removeResponseCodeListener(this);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
