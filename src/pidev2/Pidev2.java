/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2;

import interfaces.clientInterfaces;
import models.admin;
import models.client;
import services.adminServices;
import services.clientServices;
import util.Myconnexion;

public class Pidev2 {

    public static void main(String[] args) 
    {
       clientServices sp2 = new clientServices();
    /*  client   =new client("jjj","hhhj","hjhj","jkjk","jkkj" ); */
               client c1 =new client(); 
               c1.setName("walid");
               c1.setprenom("abidi");
               c1.setNumber(789456);
               c1.setmail("test@test.tn");
               c1.setadresse("tunis");
               c1.setsexe("homme");
               /*client c2 =new client("12345","00","hj888hj","j44kjk","jk66kj" );*/

    adminServices sp =new adminServices() ; 
    sp.modifier("15j", "chadine","ben aissa");
    /* sp2.addclient(c1);
     System.out.println("client added avec succes");*/
    /*  sp2.supprimerclient("walid");
      System.out.println("client deleted avec succes");*/
     /*admin am= new admin("15","chadine","ben aissa ","chadinebenaissa@esprit.tn","jk66kj") ; 
      /*admin am1= new admin("jjj","hhhj","hjhj","jkjk","jkkj" ) ; */
    /* sp.ajouter(am);*/
      /*sp.ajouter(am1);*/
    /* System.out.println(sp.getall()) ;
      System.out.println(sp.getOneById("15")) ;*/
    /* sp.supprimer("15") ; */
     
    /*sp.ajouter(am) ; 
      sp.ajouter(am2) ;
        sp.ajouter(c) ; 
        sp.supprimer("1234");*/
       
     /*sp2.modifierclient('1',"ben aissa ","chadine");*/
     
   /* System.out.println(sp.getall()) ;
    System.out.println(sp.getOneById("1234")) ;*/
     
    }
    
}
