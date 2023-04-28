<?php

namespace App\Controller;




use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Commande;
use App\Entity\Panier;
use App\Repository\CommandeRepository;
use App\Repository\PanierRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ManagerRegistry;
use App\Service\PdfService;
class CommandeController extends AbstractController
{
    #[Route('/commande', name: 'app_commande')]
    public function index(): Response
    {
        return $this->render('commande/index.html.twig', [
            'controller_name' => 'CommandeController',
        ]);
    }


    #[Route('/commandesAffiche/{idPanier}', name: 'afficher_commandes')]
    public function afficherCommandesParPanier($idPanier)
    {
    return $this->redirectToRoute("interface_affichage_commandes", ['idPanier' => $idPanier]);
    }



    #[Route('/commandes/{idPanier}', name: 'interface_affichage_commandes')]
    public function afficherCommandes(int $idPanier): Response
    {
    $commandes = $this->getDoctrine()->getRepository(Commande::class)->findBy(['panier' => $idPanier]);

    return $this->render('commande/affichercommandes.html.twig', [
        'commandes' => $commandes,
    ]);
    }

    #[Route('/suppCommande/{id}', name: 'suppC')]
    public function suppClassroom($id,CommandeRepository $com,
    ManagerRegistry $doctrine): Response
    { 
    
    //récupérer la commande à supprimer
    $commande=$com->find($id);
    $idPanier = $commande->getPanier()->getId();    
    //Action suppression
     $em =$doctrine->getManager();
     $em->remove($commande);
     $em->flush();
    return $this->redirectToRoute('interface_affichage_commandes', ['idPanier' => $idPanier]);
     } 
     


    
     } 



































    /*#[Route('/new', name: 'app_commande_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CommandeRepository $commandeRepository): Response
    {
        $commande = new Commande();
        $form = $this->createForm(CommandeType::class, $commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $commandeRepository->save($commande, true);
            
            return $this->redirectToRoute('app_commande_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('commande/new.html.twig', [
            'commande' => $commande,
            'form' => $form,
        ]);
    }

    #[Route('/{idCommande}', name: 'app_commande_show', methods: ['GET'])]
    public function show(Commande $commande): Response
    {
        return $this->render('commande/show.html.twig', [
            'commande' => $commande,
        ]);
    }

    #[Route('/{idCommande}/edit', name: 'app_commande_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Commande $commande, CommandeRepository $commandeRepository): Response
    {
        $form = $this->createForm(CommandeType::class, $commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $commandeRepository->save($commande, true);

            return $this->redirectToRoute('app_commande_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('commande/edit.html.twig', [
            'commande' => $commande,
            'form' => $form,
        ]);
    }

    #[Route('/{idCommande}', name: 'app_commande_delete', methods: ['POST'])]
    public function delete(Request $request, Commande $commande, CommandeRepository $commandeRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$commande->getIdCommande(), $request->request->get('_token'))) {
            $commandeRepository->remove($commande, true);
        }

        return $this->redirectToRoute('app_commande_index', [], Response::HTTP_SEE_OTHER);
    }*/

