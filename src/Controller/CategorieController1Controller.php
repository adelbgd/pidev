<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Form\CategorieType;
use App\Repository\CategorieRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/categorie/controller1')]
class CategorieController1Controller extends AbstractController
{
    #[Route('/', name: 'app_categorie_controller1_index', methods: ['GET'])]
    public function index(CategorieRepository $categorieRepository): Response
    {
        return $this->render('categorie_controller1/index.html.twig', [
            'categories' => $categorieRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_categorie_controller1_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CategorieRepository $categorieRepository): Response
    {
        $categorie = new Categorie();
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieRepository->save($categorie, true);

            return $this->redirectToRoute('app_categorie_controller1_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorie_controller1/new.html.twig', [
            'categorie' => $categorie,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_categorie_controller1_show', methods: ['GET'])]
    public function show(Categorie $categorie): Response
    {
        return $this->render('categorie_controller1/show.html.twig', [
            'categorie' => $categorie,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_categorie_controller1_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Categorie $categorie, CategorieRepository $categorieRepository): Response
    {
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieRepository->save($categorie, true);

            return $this->redirectToRoute('app_categorie_controller1_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorie_controller1/edit.html.twig', [
            'categorie' => $categorie,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_categorie_controller1_delete', methods: ['POST'])]
    public function delete(Request $request, Categorie $categorie, CategorieRepository $categorieRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorie->getId(), $request->request->get('_token'))) {
            $categorieRepository->remove($categorie, true);
        }

        return $this->redirectToRoute('app_categorie_controller1_index', [], Response::HTTP_SEE_OTHER);
    }
}
