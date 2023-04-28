<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Form\PanierType;
use App\Repository\PanierRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\LignePanierRepository;
use App\Entity\LignePanier;
use App\Entity\Commande;
use App\Repository\CommandeRepository;
use App\Repository\ProduitRepository;
use App\Repository\ClientRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ManagerRegistry;
use App\Form\CommandeType;
use Dompdf\Dompdf;
use Dompdf\Options;


class PanierController extends AbstractController
{
    #[Route('/panier', name: 'app_panier')]
    public function index(): Response
    {
        return $this->render('panier/index.html.twig', [
            'controller_name' => 'PanierController',
        ]);
    }
   
        //Fonction qui retourne les produits du panier d'un user ainsi que le montant total de son panier
    #[Route('/affichepanier', name: 'app_Affichepanier')]
    public function ProduitsParPanier(PanierRepository $panierRep,ClientRepository $ur,LignePanierRepository $rep):Response
    {
        $user = $ur->find(1);
        $panier = $panierRep->findOneBy(['user' => $user]);
        $idPanier=$panier->getId();
        $produits = $rep->findBy(['panier' => $idPanier]);

        
        
    return $this->render('panier/afficherpanier.html.twig', [
        'produits' => $produits,
        'panier' => $panier
    ]);
    }


           //Fonction qui supprime un produit du panier 
   #[Route('SupprimerProduit/{id}', name: 'supprimer_ligne_panier')]
   public function deleteLignePanier(int $id,ManagerRegistry $doctrine,LignePanierRepository $rep): Response
{
    $entityManager = $doctrine->getManager();
    $lignePanier = $rep->find($id);
       // Récupérer l'ID du panier
   $idPanier = $lignePanier->getPanier()->getId();
    //Supprimer la ligne panier
    $entityManager->remove($lignePanier);
    $entityManager->flush();
   // Rediriger vers la page d'affichage du panier en passant l'ID du panier
   return $this->redirectToRoute("app_Affichepanier", ['idPanier' => $idPanier]);
}
   

  //Fonction qui vide le panier d'un user 
  #[Route('/ViderPanier/{id}', name: 'vider_panier')]
  public function ViderPanier(int $id,ManagerRegistry $doctrine,LignePanierRepository $rep): Response
{
    $entityManager = $doctrine->getManager();
    $lignesPanier = $rep->findBy(['panier' => $id]);
        

    foreach ($lignesPanier as $lignePanier) {
        $entityManager->remove($lignePanier);
        // Récupérer l'ID du panier
   $idPanier = $lignePanier->getPanier()->getId();
    }

    $entityManager->flush();
   // Rediriger vers la page d'affichage du panier en passant l'ID du panier
   return $this->redirectToRoute("app_Affichepanier", ['idPanier' => $idPanier]);
}

#[Route('/passercommande/{idPanier}', name: 'passer_commande')]
public function passerCommande(int $idPanier,ManagerRegistry $doctrine,Request $request): Response
{
    // Récupérer le panier
    $entityManager = $doctrine->getManager();
    $panier = $entityManager->getRepository(Panier::class)->find($idPanier);
    
    $commande = new Commande();
    $form=$this->createForm(CommandeType::class,$commande);
    $form->handleRequest($request);
    


    if ($form->isSubmitted() && $form->isValid()) {
        $commande->setPanier($panier);
        $entityManager = $doctrine->getManager() ;
        $entityManager->persist($commande);
        $entityManager->flush();
        $idCommande =$commande->getId();

        return $this->redirectToRoute("app_facture", ['idCommande' => $idCommande]);
    }

    return $this->renderForm("commande/passercommande.html.twig",
        array("f"=>$form));
}

#[Route('/imprimerfacture/{idCommande}', name: 'app_facture')]
public function imprimerFacture(int $idCommande,LignePanierRepository $lr,PanierRepository $pr,ClientRepository $ur,CommandeRepository $cr): Response
{
    $user = $ur->find(1);
    $panier = $pr->findOneBy(['user' => $user]);
    $id=$panier->getId();
    $produits_par_panier=$lr->findBy(['panier' => $panier]);
    $commande = $cr->find($idCommande);
    $publicPath = $this->getParameter('kernel.project_dir') . '/public';
    $pdfOptions = new Options();
    $pdfOptions->set('defaultFont', 'Arial');
    $pdfOptions->set('isRemoteEnabled', true);
    $pdf = new Dompdf($pdfOptions);
//Contenu du Pdf
    $html = '<html><body>';
    //$html .= '<h1>Facture</h1>';
    // $html .= '<img src="' . $publicPath . '/en_tete.png" />';
    $html .= '<img src="public/images/en_tete.png" />';
    //$html .= '<img src="data:image/png;base64,'.base64_encode(file_get_contents($publicPath.'/en_tete.png')).'" />';
    $html .= '<p>Vendeur : Swappy</p>';
    $html .= '<p>________________________________________________</p>';
    $html .= '<p>Client : '.$commande->getNom().' '.$commande->getPrenom().'</p>';
    $html .= '<p>Commande n°'.$commande->getId().'</p>';
    $html .= '<p>________________________________________________</p>';
    $html .= '<h3>Détails de la commande</h3>';
    $html .= '<table border="1" cellspacing="0">';
    $html .= '<tr><th>Image</th><th>Produit</th><th>Description</th></tr>';
    foreach ($produits_par_panier as $p) {
        $html .= '<tr><td>'.$p->getProduit()->getImage().'</td><td>'.$p->getProduit()->getNom().'</td><td>'.$p->getProduit()->getDescription().'</td></tr>';
    }
    $html .= '</table>';
   // $html .= '<img src="data:image/png;base64,'.base64_encode(file_get_contents($publicPath.'/bas_page.png')).'" />';
    //$html .= '<img src="' . $publicPath . '/bas_page.png" />';
    
    $html .= '</body></html>';
    $pdf->loadHtml($html);
    $pdf->setPaper('A4', 'portrait');
     // Render the PDF as a string
     $pdf->render();

     // Retourner le pdf
     $output = $pdf->output();
     file_put_contents('Téléchargements/facture.pdf', $output);

     return $this->redirectToRoute("app_Affichepanier", ['id' => $id]);
}




























    /*#[Route('/', name: 'app_panier_index', methods: ['GET'])]
    public function index(PanierRepository $panierRepository): Response
    {
        return $this->render('panier/index.html.twig', [
            'paniers' => $panierRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_panier_new', methods: ['GET', 'POST'])]
    public function new(Request $request, PanierRepository $panierRepository): Response
    {
        $panier = new Panier();
        $form = $this->createForm(PanierType::class, $panier);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $panierRepository->save($panier, true);

            return $this->redirectToRoute('app_panier_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('panier/new.html.twig', [
            'panier' => $panier,
            'form' => $form,
        ]);
    }

    #[Route('/{idPanier}', name: 'app_panier_show', methods: ['GET'])]
    public function show(Panier $panier): Response
    {
        return $this->render('panier/show.html.twig', [
            'panier' => $panier,
        ]);
    }

    #[Route('/{idPanier}/edit', name: 'app_panier_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Panier $panier, PanierRepository $panierRepository): Response
    {
        $form = $this->createForm(PanierType::class, $panier);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $panierRepository->save($panier, true);

            return $this->redirectToRoute('app_panier_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('panier/edit.html.twig', [
            'panier' => $panier,
            'form' => $form,
        ]);
    }

    #[Route('/{idPanier}', name: 'app_panier_delete', methods: ['POST'])]
    public function delete(Request $request, Panier $panier, PanierRepository $panierRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$panier->getIdPanier(), $request->request->get('_token'))) {
            $panierRepository->remove($panier, true);
        }

        return $this->redirectToRoute('app_panier_index', [], Response::HTTP_SEE_OTHER);
    }*/
}
