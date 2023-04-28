<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Entity\Categorie;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;


use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
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
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class IndexController extends AbstractController
{
    /**#[Route('/index', name: 'app_index')]
    public function index(): JsonResponse
    {
        return $this->json([
            'message' => 'Welcome to your new controller!',
            'path' => 'src/Controller/IndexController.php',
        ]);
    }**/

    #[Route('/', name: 'prod_list')]
    public function home()
  {
    //récupérer tous les articles de la table article de la BD
    // et les mettre dans le tableau $articles
    $produits= $this->getDoctrine()->getRepository(Produit::class)->findAll();
   
    //$articles=['produit1','produit2','produit3'];
    return  $this->render('produits/indexback.html.twig',['produits' => $produits]);  
  }


  #[Route('/index/save', name: 'app_save')]
  public function save() {
    $entityManager = $this->getDoctrine()->getManager();

    $produit = new Produit();
    $produit->setDescription('Produit 3');
    $produit->setStatut('non disponible');
    $produit->setNom('casquette');
    $produit->setValeur(887.9);
    $date = new \DateTime('2020-04-12');
    $produit->setDate($date);
    $produit->setImage('aa.jgp');
   
    $entityManager->persist($produit);
    $entityManager->flush();

    return new Response('produit enregisté avec id   '.$produit->getId());
  }


  #[Route('/index/form', name: 'app_formadd')]
  public function new(Request $request, SluggerInterface $slugger) {
    $produit = new Produit();
    $form = $this->createFormBuilder($produit)
    ->add('nom', TextType::class)
    ->add('valeur', NumberType::class)
    ->add('description', TextType::class)
    ->add('statut', TextType::class)
    ->add('Categorie', EntityType::class, ['class' => Categorie::class,
    'choice_label' => 'nom',
    'label' => 'Catégorie'
        ],
    )
    ->add('date', DateType::class, [
        'widget' => 'single_text',
        
    ])
    ->add('image', FileType::class, [
        'label' => 'Image (JPG, PNG, GIF)',
        'required' => false,
        'mapped' => false,
        'constraints' => [
            new File([
                'maxSize' => '1024k',
                'mimeTypes' => [
                    'image/jpeg',
                    'image/png',
                    'image/gif',
                ],
                'mimeTypesMessage' => 'Please upload a valid image file',
            ])
        ],
    ])
    ->add('save', SubmitType::class, array('label' => 'Ajouter')
    )->getForm();
    
    
    $form->handleRequest($request);
    
    if($form->isSubmitted() && $form->isValid()) {
    $nom = $form->get('nom')->getData();
    $valeur = $form->get('valeur')->getData();
    $description = $form->get('description')->getData();
    $statut = $form->get('statut')->getData();
    $date = $form->get('date')->getData();
    $brochureFile = $form->get('image')->getData();
    $Categories = $request->request->get('form')['Categorie'];

    // this condition is needed because the 'brochure' field is not required
    // so the PDF file must be processed only when a file is uploaded
    if ($brochureFile) {
        $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
        // this is needed to safely include the file name as part of the URL
        $safeFilename = $slugger->slug($originalFilename);
        $newFilename = $safeFilename.'-'.uniqid().'.'.$brochureFile->guessExtension();

        // Move the file to the directory where brochures are stored
        try {
            $brochureFile->move(
                $this->getParameter('produit_image'),
                $newFilename
            );
        } catch (FileException $e) {
            // ... handle exception if something happens during file upload
        }

        // updates the 'brochureFilename' property to store the PDF file name
        // instead of its contents
        $produit->setDescription($description);
        $produit->setStatut($statut);
        $produit->setNom($nom);
        $produit->setValeur($valeur);
        $produit->setDate($date);
        $produit->setImage($newFilename);
        $produit->setCategorie($Categories);
    }
    
    $entityManager = $this->getDoctrine()->getManager();
    $entityManager->persist($produit);
    $entityManager->flush();
    
    return $this->redirectToRoute('prod_list');
    }
    return $this->render('produits/new.html.twig',['form' => $form->createView()]);
    }


    #[Route('/index/formmodif/{id}', name: 'app_formmodif')]
    public function edit(Request $request, $id, SluggerInterface $slugger) {
        $produit = new Produit();
        $produit = $this->getDoctrine()->getRepository(Produit::class)->find($id);
  
        $form = $this->createFormBuilder($produit)
        ->add('nom', TextType::class)
        ->add('valeur', NumberType::class)
        ->add('description', TextType::class)
        ->add('statut', TextType::class)
        ->add('date', DateType::class, [
            'widget' => 'single_text',
            
        ])
        ->add('Categorie', EntityType::class, ['class' => Categorie::class,
    'choice_label' => 'nom',
    'label' => 'Catégorie'
        ],
    )
        ->add('image', FileType::class, [
            'label' => 'Image (JPG, PNG, GIF)',
            'required' => false,
            'mapped' => false,
            'constraints' => [
                new File([
                    'maxSize' => '1024k',
                    'mimeTypes' => [
                        'image/jpeg',
                        'image/png',
                        'image/gif',
                    ],
                    'mimeTypesMessage' => 'Please upload a valid image file',
                ])
            ],
        ])
        ->add('save', SubmitType::class, array('label' => 'Modifier')
        )->getForm();
  
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()) {
            $brochureFile = $form->get('image')->getData();

    // this condition is needed because the 'brochure' field is not required
    // so the PDF file must be processed only when a file is uploaded
    if ($brochureFile) {
        $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
        // this is needed to safely include the file name as part of the URL
        $safeFilename = $slugger->slug($originalFilename);
        $newFilename = $safeFilename.'-'.uniqid().'.'.$brochureFile->guessExtension();

        // Move the file to the directory where brochures are stored
        try {
            $brochureFile->move(
                $this->getParameter('produit_image'),
                $newFilename
            );
        } catch (FileException $e) {
            // ... handle exception if something happens during file upload
        }
        $produit->setImage($newFilename);
    }
  
          $entityManager = $this->getDoctrine()->getManager();
          $entityManager->flush();
  
          return $this->redirectToRoute('prod_list');
        }
  
        return $this->render('produits/edit.html.twig', ['form' => $form->createView()]);
      }

      #[Route('/index/formsupp/{id}', name: 'app_formsupp')]
      public function delete(Request $request, $id) {
        $produit = $this->getDoctrine()->getRepository(Produit::class)->find($id);
  
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($produit);
        $entityManager->flush();
  
        $response = new Response();
        $response->send();

        return $this->redirectToRoute('prod_list');
      }

      #[Route('/index/sh/{id}', name: 'prod_show')]
      public function show($id) {
        $produit = $this->getDoctrine()->getRepository(Produit::class)->find($id);
  
        return $this->render('produits/show.html.twig', array('produit' => $produit));
      }

      
   




}
