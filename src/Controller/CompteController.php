<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\RelationFollow;
use App\Form\CompteType;
use App\Repository\CompteRepository;
use App\Repository\RelationFollowRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface ;
use Symfony\Component\HttpFoundation\Request as HttpFoundationRequest;
use GuzzleHttp\Client;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\DomCrawler\Crawler;

#[Route('/compte')]
class CompteController extends AbstractController
{
    #[Route('/comptes', name: 'app_compte_index', methods: ['GET'])]
    public function index(CompteRepository $compteRepository , PaginatorInterface $paginator, Request $request
    ): Response
    {     $comptes = $paginator->paginate($compteRepository->findAll(),
        $request->query->getInt('page',1),
        4) ;
        $pageCount = $comptes->getPageCount();
        return $this->render('compte/index.html.twig', [
            'comptes' => $comptes,
            'pageCount' => $pageCount,

        ]);
    }
    #[Route('/search-comptes', name: 'search_comptes')]
    public function searchComptes(Request $request, CompteRepository $compteRepository, SerializerInterface $serializer,PaginatorInterface $paginator)
    {

        if ($request->isXmlHttpRequest()) {
    
            $searchTerm = $request->request->get('search_field');
    
            // Vérifier si le champ de recherche est vide
            if (empty($searchTerm)) {
                 
        $comptes = $compteRepository->findAll();
        // Utilisez la méthode paginate pour paginer les résultats
        $comptes = $paginator->paginate(
           $comptes,
            $request->query->getInt('page', 1), // Numéro de page
            4 // Nombre de résultats par page
        );
        $pageCount = $comptes->getPageCount();
    
     
                $html = $this->renderView('compte/index.html.twig', [
                    'comptes' => $comptes,
            'pageCount' => $pageCount,
                     
                ]);
    
                $crawler = new Crawler($html);
                $compteList = $crawler->filter('#comptes-list')->html();
                        // retourner les résultats au format JSON
                        return new JsonResponse($compteList);
            }
            $comptes = $paginator->paginate(
                $compteRepository->findbyname($searchTerm),$request->query->getInt('page', 1), // Numéro de page
                4 // Nombre de résultats par page
            );
            
            // transformer les résultats en HTML
            $pageCount = $comptes->getPageCount();
    
     
                $html = $this->renderView('compte/index.html.twig', [
                    'comptes' => $comptes,
                    'pageCount' => $pageCount,
                             
                ]);
    
            $crawler = new Crawler($html);
    $compteList = $crawler->filter('#comptes-list')->html();
            // retourner les résultats au format JSON
            return new JsonResponse($compteList);
        } else {
            // si la requête n'est pas une requête AJAX, afficher la page des discussions
            $comptes = $paginator->paginate(
                $compteRepository->findAll(),
                $request->query->getInt('page', 1), // Numéro de page
                4 // Nombre de résultats par page
            );
            $pageCount = $comptes->getPageCount();
    
     
                $html = $this->renderView('compte/index.html.twig', [
                    'comptes' => $comptes,
                    'pageCount' => $pageCount,
                             
                ]);
        }
    }
    #[Route('/admin', name: 'app_compte_index_admin', methods: ['GET'])]
    public function indexadmin(CompteRepository $compteRepository): Response
    {
        return $this->render('compte/index_admin.html.twig', [
            'comptes' => $compteRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_compte_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CompteRepository $compteRepository): Response
    {
        $compte = new Compte();
        $form = $this->createForm(CompteType::class, $compte);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $compte->setDateDeCreation(new \DateTime('now'));
            $compte->setNbrFollowers(0);
            $compte->setNbrFollowings(0);
            $compte->setNbrProduitsPublies(0);
            $compte->setBanned(0);
            $imageFile = $form->get('profile_image')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/img';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                   
                   $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }
                
                $compte->setProfileImage($filenameWithoutSpaces);
               }

            $compteRepository->save($compte, true);

            return $this->redirectToRoute('app_compte_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('compte/new.html.twig', [
            'compte' => $compte,
            'form' => $form,
        ]);
    }
    #[Route('/admin/{id}', name: 'app_compte_show_admin', methods: ['GET'])]
    public function shows(Compte $compte ,CompteRepository $rep1 , RelationFollowRepository $rep2 ): Response
    {   
        $comptelogin = $rep1->find(1);
        $tablerelation = $rep2->checkFollow($comptelogin,$compte);
        $followings = $compte->getFollowings(); 
        $followerss = [];
        $followingss = [];
        $followers = $compte->getFollowers(); 
        foreach ($followers as $f) {
           
            $followerss[] = $f->getFollower() ;
        }
        foreach ($followings as $f) {
           
            $followingss[] = $f->getFollowed() ;
        }
        return $this->render('compte/show_admin.html.twig', [
            'compte' => $compte, 
            'tablerelation' => $tablerelation,
            'followings' => $followingss ,
            'followers' => $followerss ,
         
        ]);
    }
    #[Route('/{id}', name: 'app_compte_show', methods: ['GET'])]
    public function show(Compte $compte ,CompteRepository $rep1 , RelationFollowRepository $rep2 ): Response
    {   
        $comptelogin = $rep1->find(1);
        $tablerelation = $rep2->checkFollow($comptelogin,$compte);
        $followings = $compte->getFollowings(); 
        $followerss = [];
        $followingss = [];
        $followers = $compte->getFollowers(); 
        foreach ($followers as $f) {
           
            $followerss[] = $f->getFollower() ;
        }
        foreach ($followings as $f) {
           
            $followingss[] = $f->getFollowed() ;
        }
        return $this->render('compte/show.html.twig', [
            'compte' => $compte, 
            'tablerelation' => $tablerelation,
            'followings' => $followingss ,
            'followers' => $followerss ,
         
        ]);
    }

    
   
    #[Route('/{id}/edit', name: 'app_compte_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Compte $compte, CompteRepository $compteRepository): Response
    {
        $form = $this->createForm(CompteType::class, $compte);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('profile_image')->getData();
        
                    if ($imageFile) {
                        
                        $imagesDirectory = 'C:/xampp/htdocs/img';
                        $originalFilename = $imageFile->getClientOriginalName();
                        $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);
        
                        
                           
                           $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                      
                        
                        $compte->setProfileImage($filenameWithoutSpaces);
                        }

            $compteRepository->save($compte, true);


            return $this->redirectToRoute('app_compte_show', ['id' => $compte->getId()], Response::HTTP_SEE_OTHER);

        }

        return $this->renderForm('compte/edit.html.twig', [
            'compte' => $compte,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_compte_delete', methods: ['POST'])]
    public function delete(Request $request, Compte $compte, CompteRepository $compteRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$compte->getId(), $request->request->get('_token'))) {
            $compteRepository->remove($compte, true);
        }

        return $this->redirectToRoute('app_compte_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/', name: 'app_compte_home', methods: ['GET'])]
    public function Home(CompteRepository $compteRepository): Response
    {
        return $this->render('compte/home.html.twig', [
            'comptes' => $compteRepository->findAll(),
        ]);
    }

    public function unfollow(Request $request, Compte $compte , CompteRepository $rep1 , RelationFollowRepository $rep2): Response
    {
        
        
        // Find the logged-in user's compte.
        //$user = $this->getUser()->getCompte();
        $comptelogin = $rep1->find(1);
        
        // Find the RelationFollow entity between the two comptes.
        $relation = $rep2->findOneBy([
                'follower' => $comptelogin,
                'followed' => $compte
            ]);
        
            
        if ($relation) {
            // Remove the relation from the database.
            $rep2->remove($relation,true);
           
            
            $this->addFlash('success', 'You have unfollowed '.$compte->getNom());
        } else {
            $this->addFlash('warning', 'You are not following '.$compte->getNom());
        }
        $comptelogin->setNbrFollowings($comptelogin->getNbrFollowings()-1);
        $compte->setNbrFollowers($compte->getNbrFollowers()-1);
        $rep1->save($comptelogin, true);
        $rep1->save($compte, true);
        // Redirect back to the compte page.
        return $this->redirectToRoute('app_compte_show', ['id' => $compte->getId()]);
    }
    
    public function follow(Request $request, Compte $compte , CompteRepository $rep1 , RelationFollowRepository $rep2): Response
    {
        
        $comptelogin = $rep1->find(1);

        $relationfollow = new RelationFollow();
        $relationfollow->setFollower($comptelogin);
        $relationfollow->setFollowed($compte);
        $comptelogin->setNbrFollowings($comptelogin->getNbrFollowings()+1);
        $compte->setNbrFollowers($compte->getNbrFollowers()+1);
        $rep1->save($comptelogin, true);
        $rep1->save($compte, true);
        $rep2->save($relationfollow, true);
        
        return $this->redirectToRoute('app_compte_show', ['id' => $compte->getId()]);
    }

    public function banned(Request $request, Compte $compte , CompteRepository $rep1 ):response
    {

$compte->setBanned(1) ;
$rep1->save($compte, true);
return $this->redirectToRoute('app_compte_index_admin');

    }
    
    public function unbanned(Request $request, Compte $compte , CompteRepository $rep1 ):response
    {

$compte->setBanned(0) ;
$rep1->save($compte, true);
return $this->redirectToRoute('app_compte_index_admin');

    }
}
