<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ProduitRepository;
use App\Repository\ClientRepository;

use App\Repository\PanierRepository;
use Doctrine\Persistence\ManagerRegistry;
use App\Entity\Panier;
use App\Entity\Produit;
use App\Entity\LignePanier;
use App\Repository\LignePanierRepository;
use App\Form\LignePanierType;

class MarketplaceController extends AbstractController
{
    #[Route('/marketplace', name: 'app_marketplace')]
    public function index(): Response
    {
        return $this->render('marketplace/index.html.twig', [
            'controller_name' => 'MarketplaceController',
        ]);
    }

    
    //Fonction qui affiche tous les produits depuis la base
    #[Route('/marketplace', name: 'app_marketplace')]
    public function afficherProduits(ProduitRepository $rep,ClientRepository $ur,PanierRepository $panierRep): Response
    {
        $user = $ur->find(1);
       
        $panier = $panierRep->findOneBy(['user' => $user]);
        $produits=$rep->findAll();
         return $this->render('marketplace/marketplace.html.twig', [
            'panier' => $panier,
            'p'=>$produits,

        ]);
    }


         //Afficherdetail d'un produit et ajputer le produit au panier
           #[Route('/useraffichedetailproduit/{id}', name: 'affichedetail')]
           public function affichedetail(int $id, LignePanierRepository $lr, PanierRepository $pr, ClientRepository $ur, ProduitRepository $repproduit,ManagerRegistry $doctrine, Request $request): Response
           { 
            $produit=$repproduit->find($id);
            $Produits=$repproduit->findAll();
            $user = $ur->find(1);
            $panier = $pr->findOneBy(['user' => $user]);
            $em = $doctrine->getManager() ;
            $produits_par_panier=$lr->findBy(['panier' => $panier]);
            

            // Le produit n'est pas encore dans le panier, on crée une nouvelle ligne panier
            $lignePanier = new LignePanier();
                $form=$this->createForm(LignePanierType::class,$lignePanier);
                $form->handleRequest($request);
               
                  if ($form->isSubmitted() && $form->isValid()) {
                  $lignePanier->setProduit($produit);
                  $lignePanier->setPanier($panier);
                  $lignePanier->setNomProduit($produit->getNom());
                  $lignePanier->setDescription($produit->getDescription());
                  $lignePanier->setImage($produit->getImage());
                  $em->persist($lignePanier);
                  $em->flush();
                    // Rediriger vers la page d'affichage des produits 
                  return $this->redirectToRoute("app_marketplace");
                  
        
                 } 

                 return $this->render('marketplace/afficherDetailProduitUser.html.twig', [
                    'p'=>$produit  ,
                    's'=>$Produits  , 
                    'f' => $form->createView()
                    ]);






























   }

























}