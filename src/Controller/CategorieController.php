<?php

namespace App\Controller;

use App\Entity\Categorie;


use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;

class CategorieController extends AbstractController
{
    /*  #[Route('/produit', name: 'app_produit')]
    public function index(): JsonResponse
    {
        return $this->json([
            'message' => 'Welcome to your new controller!',
            'path' => 'src/Controller/ProduitController.php',
        ]);
    }*/

    #[Route('/catego', name: 'catego_list')]
    public function home()
  {
    //récupérer tous les articles de la table article de la BD
    // et les mettre dans le tableau $articles
    $categories= $this->getDoctrine()->getRepository(Categorie::class)->findAll();
   
    //$articles=['produit1','produit2','produit3'];
    return  $this->render('categories/index.html.twig',['categories' => $categories]);  
  }


  #[Route('/indexc/form', name: 'app_formaddc')]
  public function new(Request $request) {
    $categorie = new Categorie();
    $form = $this->createFormBuilder($categorie)
    ->add('nom', TextType::class)
    
    
    ->add('save', SubmitType::class, array('label' => 'Ajouter')
    )->getForm();
    
    
    $form->handleRequest($request);
    
    if($form->isSubmitted() && $form->isValid()) {
    $nom = $form->get('nom')->getData();
    

   

        // updates the 'brochureFilename' property to store the PDF file name
        // instead of its contents
        
    $categorie->setNom($nom);
        
    }
    
    $entityManager = $this->getDoctrine()->getManager();
    $entityManager->persist($categorie);
    $entityManager->flush();
    
    return $this->redirectToRoute('catego_list');
    
    return $this->render('categories/new.html.twig',['form' => $form->createView()]);
    }

}