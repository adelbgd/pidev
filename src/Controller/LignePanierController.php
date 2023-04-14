<?php

namespace App\Controller;

use App\Entity\LignePanier;
use App\Form\LignePanierType;
use App\Repository\LignePanierRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ligne/panier')]
class LignePanierController extends AbstractController
{
    #[Route('/', name: 'app_ligne_panier_index', methods: ['GET'])]
    public function index(LignePanierRepository $lignePanierRepository): Response
    {
        return $this->render('ligne_panier/index.html.twig', [
            'ligne_paniers' => $lignePanierRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_ligne_panier_new', methods: ['GET', 'POST'])]
    public function new(Request $request, LignePanierRepository $lignePanierRepository): Response
    {
        $lignePanier = new LignePanier();
        $form = $this->createForm(LignePanierType::class, $lignePanier);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $lignePanierRepository->save($lignePanier, true);

            return $this->redirectToRoute('app_ligne_panier_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('ligne_panier/new.html.twig', [
            'ligne_panier' => $lignePanier,
            'form' => $form,
        ]);
    }

    #[Route('/{idLigne}', name: 'app_ligne_panier_show', methods: ['GET'])]
    public function show(LignePanier $lignePanier): Response
    {
        return $this->render('ligne_panier/show.html.twig', [
            'ligne_panier' => $lignePanier,
        ]);
    }

    #[Route('/{idLigne}/edit', name: 'app_ligne_panier_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, LignePanier $lignePanier, LignePanierRepository $lignePanierRepository): Response
    {
        $form = $this->createForm(LignePanierType::class, $lignePanier);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $lignePanierRepository->save($lignePanier, true);

            return $this->redirectToRoute('app_ligne_panier_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('ligne_panier/edit.html.twig', [
            'ligne_panier' => $lignePanier,
            'form' => $form,
        ]);
    }

    #[Route('/{idLigne}', name: 'app_ligne_panier_delete', methods: ['POST'])]
    public function delete(Request $request, LignePanier $lignePanier, LignePanierRepository $lignePanierRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$lignePanier->getIdLigne(), $request->request->get('_token'))) {
            $lignePanierRepository->remove($lignePanier, true);
        }

        return $this->redirectToRoute('app_ligne_panier_index', [], Response::HTTP_SEE_OTHER);
    }
}
