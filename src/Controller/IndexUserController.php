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
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Sbyaute\StarRatingBundle\Form\StarRatingType;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\HttpFoundation\JsonResponse;

class IndexUserController extends AbstractController
{

    #[Route('/u', name: 'home')]
    public function home(): Response
    {

        $produits= $this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('produits/indexuser.html.twig',['produits' => $produits]);
    }


    #[Route('/indexuser/form', name: 'app_formadduser')]
  public function new(Request $request, SluggerInterface $slugger, MailerInterface $mailer) {
    $produit = new Produit();
    $form = $this->createFormBuilder($produit)
    ->add('nom', TextType::class)
    ->add('valeur', TextType::class)
    ->add('description', TextType::class)
    ->add('statut', TextType::class)
    ->add('rate', StarRatingType::class, [
    	//...
    	'stars' => 5,
    	//...
    ])
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
                'maxSize' => '5000k',
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

        
        
    }
    
    $entityManager = $this->getDoctrine()->getManager();
    $entityManager->persist($produit);
    $entityManager->flush();


    $email = (new Email())
            ->from('sswappy288@gmail.com')
            ->to('saadaoui.walid@esprit.tn')
            //->cc('cc@example.com')
            //->bcc('bcc@example.com')
            //->replyTo('fabien@example.com')
            //->priority(Email::PRIORITY_HIGH)
            ->subject('Swappy')
            ->text('Votre produit '.$nom.' a été ajouté avec succés');
            //->html('<p>PRODUIT AJOUTEE AVEC SUCCEES!</p>');
            

        $mailer->send($email);
    
    return $this->redirectToRoute('home');
    }
    return $this->render('produits/new.html.twig',['form' => $form->createView()]);
    }


    #[Route('/indexuser/formmodif/{id}', name: 'app_formmodifuser')]
    public function edit(Request $request, $id, SluggerInterface $slugger, MailerInterface $mailer) {
        $produit = new Produit();
        $produit = $this->getDoctrine()->getRepository(Produit::class)->find($id);
  
        $form = $this->createFormBuilder($produit)
        ->add('nom', TextType::class)
        ->add('valeur', TextType::class)
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
                    'maxSize' => '5000K',
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


          $email = (new Email())
            ->from('sswappy288@gmail.com')
            ->to('saadaoui.walid@esprit.tn')
            //->cc('cc@example.com')
            //->bcc('bcc@example.com')
            //->replyTo('fabien@example.com')
            //->priority(Email::PRIORITY_HIGH)
            ->subject('Swappy')
            ->text('Votre produit a été modifié avec succés');
            //->html('<p>PRODUIT AJOUTEE AVEC SUCCEES!</p>');
            

          $mailer->send($email);
  
          return $this->redirectToRoute('home');
        }
  
        return $this->render('produits/edit.html.twig', ['form' => $form->createView()]);
      }


      #[Route('/indexuser/formsupp/{id}', name: 'app_formsuppuser')]
      public function delete(Request $request, $id) {
        $produit = $this->getDoctrine()->getRepository(Produit::class)->find($id);
  
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($produit);
        $entityManager->flush();
  
        $response = new Response();
        $response->send();

        return $this->redirectToRoute('home');
      }

      #[Route('/produit/dynamic-search', name: 'product_dynamic_search', methods: ['GET'])]
public function findByTitle(Request $request): Response
{
    $query = $request->query->get('q');
    $entityManager = $this->getDoctrine()->getManager();
    $produitRepository = $entityManager->getRepository(Produit::class);
    $results = $produitRepository->findByTitle($query);

    // Récupérer les résultats de recherche sous forme de tableau
    $data = [];
    foreach ($results as $result) {
        $data[] = [
            'image' => $result->getImage(),
            'nom' => $result->getNom(),
            'description' => $result->getDescription(),
        ];
    }

    // Retourner les résultats de recherche sous forme de JSON
    return new JsonResponse($data);
}

}