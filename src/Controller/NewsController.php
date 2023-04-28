<?php

namespace App\Controller;

use App\Entity\News;
use App\Form\NewsType;
use App\Repository\NewsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Validator\Constraints\DateTime ; 

#[Route('/news')]
class NewsController extends AbstractController
{
    #[Route('/', name: 'app_news_index', methods: ['GET'])]
    public function index(NewsRepository $newsRepository): Response
    {
        return $this->render('news/index.html.twig', [
            'news' => $newsRepository->findAll(),
        ]);
    }
    #[Route('/admin', name: 'app_news_index_admin', methods: ['GET'])]
    public function indexadmin(NewsRepository $newsRepository): Response
    {
        return $this->render('news/indexadmin.html.twig', [
            'news' => $newsRepository->findAll(),
        ]);
    }
    
    
    #[Route('/admin/new', name: 'app_news_new', methods: ['GET', 'POST'])]
    public function new(Request $request, NewsRepository $newsRepository): Response
    {
        $news = new News();
        $form = $this->createForm(NewsType::class, $news);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $news->setDateDeCreation(new \DateTime('now'));
            $imageFile = $form->get('image')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/img';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                   
                   $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }
                
                $news->setImage($filenameWithoutSpaces);
               }
          // $date = new DateTime('now');

          //  $news->setDateDeCreation($date);
            $newsRepository->save($news, true);

            return $this->redirectToRoute('app_news_index_admin', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('news/new.html.twig', [
            'news' => $news,
            'form' => $form,
        ]);
    }

    #[Route('admin/{id}', name: 'app_news_show_admin', methods: ['GET'])]
    public function shows(News $news): Response
    {
        return $this->render('news/show_admin.html.twig', [
            'news' => $news,
        ]);
    }

    #[Route('/{id}', name: 'app_news_show', methods: ['GET'])]
    public function show(News $news): Response
    {
        return $this->render('news/show.html.twig', [
            'news' => $news,
        ]);
    }

    #[Route('/admin/{id}/edit', name: 'app_news_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, News $news, NewsRepository $newsRepository): Response
    {
        $form = $this->createForm(NewsType::class, $news);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $newsRepository->save($news, true);
            $imageFile = $form->get('image')->getData();
        
                    if ($imageFile) {
                        
                        $imagesDirectory = 'C:/xampp/htdocs/img';
                        $originalFilename = $imageFile->getClientOriginalName();
                        $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);
        
                        
                          
                           $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                      
                        
                        $news->setImage($filenameWithoutSpaces);
                        }
                        $newsRepository->save($news, true);

            return $this->redirectToRoute('app_news_index_admin', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('news/edit.html.twig', [
            'news' => $news,
            'form' => $form,
        ]);
    }

    #[Route('/admin/{id}', name: 'app_news_delete', methods: ['POST'])]
    public function delete(Request $request, News $news, NewsRepository $newsRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$news->getId(), $request->request->get('_token'))) {
            $newsRepository->remove($news, true);
        }

        return $this->redirectToRoute('app_news_index_admin', [], Response::HTTP_SEE_OTHER);
    }
}
