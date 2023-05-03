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
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


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
     
     #[Route ('/allCommandes', name: 'list')]
//* Dans cette fonction, nous utilisons les services Normlizeinterface et StudentRepository, //* avec la méthode d'injection de dépendances.
public function getCommandes (CommandeRepository $repo, SerializerInterface $serializer)
{
$commandes = $repo -> findAll();
//* Nous utilisons la fonction normalize qui transforme Le tableau d'objets //* students en tableau associatif simple.

//*$commandesNormalises = $normalizer-> normalize($commandes, 'json',['groups'=>"commandes"]);


//* Nous utilisons lg fonction json encode pour transformer un tableau associatif en format JSON
$json = $serializer -> serialize ($commandes, 'json',['groups'=>"commandes"]);
//* Nous renvoyons une réponse Http qui prend en paramètre un tableau en format JSON
return new Response ($json);

    
     } 

#[Route('/commande/{id}', name: "student")]
public function CommandeId($id, NormalizerInterface $normalizer, CommandeRepository $repo)
{
$commande = $repo->find($id);
$commandeNormalises = $normalizer -> normalize($commande,'json', ['groups' =>"commandes"]);
return new Response (json_encode ($commandeNormalises)) ;

    
}

#[Route ("deleteCommandeJSON/{id}", name: "deletecommandeJSON") ]
public function deleteCommandeJSON(Request $req, $id, NormalizerInterface $Normalizer)
{
$em = $this->getdoctrine()->getManager () ;
$commande = $em->getRepository (Commande::class)->find($id);
$em->remove ($commande);
$em->flush();
$jsonContent = $Normalizer->normalize($commande,'json', ['groups'=>'students']);
return new Response("Student deleted successfully" . json_encode ($jsonContent));

}



#[Route ("addCommandeJSON/new", name: "addCommandeJSON") ] 
public function addStudentJSON (Request $req,NormalizerInterface $Normalizer,int $idPanier)
{
$em = $this->getDoctrine()->getManager();
$panier = $em->getRepository(Panier::class)->find($idPanier);

$commande = new Commande();
$commande->setPanier($panier);
$commande->setPrenom($req->get ('prenom')) ;
$commande->setNom($req->get ('nom'));
$commande->setNum($req->get ('num'));
$commande->setMail($req->get ('mail'));
$commande->setRegion($req->get ('region'));
$commande->setVille($req->get ('ville'));
$commande->setRue($req->get ('rue'));
$commande->setCodePostal($req->get ('code_postal'));

$em->persist ($commande);
$em->flush();
$jsonContent=$Normalizer->normalize($commande, 'json', ['groups' => 'commandes']);
return new Response(json_encode ($jsonContent)) ;
//*http://127.0.0.1:8000/addCommandeJSON/new?prenom=grech&nom=safa&nom=safa&num=12345678&mail=safa@esprit.tn&region=benarous&ville=rades&rue=nahjbenzart&code_postal=1234
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

