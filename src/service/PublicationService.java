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
import entities.Publication;
import statics.statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizl
 */
public class PublicationService {

    public boolean resultOK;
    ConnectionRequest req;
    //singleton 
    static PublicationService instance;
    ArrayList<Publication> result = new ArrayList<>();

    //initilisation connection request 
    public static PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;
    }

    public PublicationService() {
        req = new ConnectionRequest();

        //ajout 
    }

    public boolean AjoutPublication(Publication p) {

        String url = statics.BASE_URL + "/add_pub_json?titre_pub=" + p.getTitre_pub() + "&contenu_pub=" + p.getContenu_pub() + "&image=" + p.getImage(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation

        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    //affichage
    /*
    public ArrayList<Publication>affichagePublication() {
        ArrayList<Publication> result = new ArrayList<>();
        
        String url = statics.BASE_URL+"/allPub";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapPublication = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapPublication.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Publication p = new Publication();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String titre_pub = obj.get("titre_pub").toString();
                        String contenu_pub = obj.get("contenu_pub").toString();
                        String image = obj.get("image").toString();

                        
                        p.setId((int)id);
                        p.setTitre_pub(titre_pub);
                        p.setContenu_pub(contenu_pub);
                        p.setImage(image);
                        
                              //Date 
                        String DateConverter =  obj.get("date_pub").toString().substring(obj.get("date_pub").toString().indexOf("timestamp") + 10 , obj.get("date_pub").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                      //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    //    String dateString = formatter.format(currentTime);
                        p.setDate_pub(currentTime);
                        
                        //insert data into ArrayList result
                        result.add(p);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
     */
    //Delete 
    /* public boolean deletePublication(int id ) {
        String url = statics.BASE_URL +"/deletePublication?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }*/
    //Update 
    /*  public boolean modifierPublication(Publication publication) {
        String url = statics.BASE_URL +"/updatePublication?id="+publication.getId()+"&titre_pub="+publication.getTitre_pub()+"&contenu_pub="+publication.getContenu_pub()+"&image="+publication.getImage();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }*/
    //Detail Publication bensba l detail n5alihoa lel5r ba3d delete+update
    public Publication DetailPublication(int id, Publication publication) {

        String url = statics.BASE_URL + "/detailPublication?" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                publication.setTitre_pub(obj.get("titre_pub").toString());
                publication.setContenu_pub(obj.get("contenu_pub").toString());
                publication.setImage(obj.get("Image").toString());

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return publication;

    }

    //FETCH
    public ArrayList<Publication> fetchPublications() {

        req = new ConnectionRequest();

        //1
        String fetchURL = statics.BASE_URL + "/allPub";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = (ArrayList<Publication>) parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    //Parse
    public List<Publication> parseTasks(String jsonText) {

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

                Publication p = new Publication();

                p.setTitre_pub((String) item.get("titre_pub"));
                p.setContenu_pub((String) item.get("contenu_pub"));
                p.setImage((String) item.get("image"));

                result.add(p);
            }

        } catch (IOException ex) {
        }

        //End
        return result;
    }

    public boolean updatePub(Publication p) {
        String url = statics.BASE_URL + "/updatepubJSON?id=" + p.getId()
                + "&titre_pub=" + p.getTitre_pub()
                + "&contenu_pub=" + p.getContenu_pub()
                + "&image=" + p.getImage();

        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deletePub(Publication p) {

        String url = statics.BASE_URL + "/deletepubJSON?id=" + p.getId();

        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
