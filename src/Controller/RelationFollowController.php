<?php

namespace App\Controller;

use App\Entity\RelationFollow;
use App\Form\RelationFollowType;
use App\Repository\RelationFollowRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/relation/follow')]
class RelationFollowController extends AbstractController
{
    #[Route('/', name: 'app_relation_follow_index', methods: ['GET'])]
    public function index(RelationFollowRepository $relationFollowRepository): Response
    {
        return $this->render('relation_follow/index.html.twig', [
            'relation_follows' => $relationFollowRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_relation_follow_new', methods: ['GET', 'POST'])]
    public function new(Request $request, RelationFollowRepository $relationFollowRepository): Response
    {
        $relationFollow = new RelationFollow();
        $form = $this->createForm(RelationFollowType::class, $relationFollow);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $relationFollowRepository->save($relationFollow, true);

            return $this->redirectToRoute('app_relation_follow_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('relation_follow/new.html.twig', [
            'relation_follow' => $relationFollow,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_relation_follow_show', methods: ['GET'])]
    public function show(RelationFollow $relationFollow): Response
    {
        return $this->render('relation_follow/show.html.twig', [
            'relation_follow' => $relationFollow,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_relation_follow_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, RelationFollow $relationFollow, RelationFollowRepository $relationFollowRepository): Response
    {
        $form = $this->createForm(RelationFollowType::class, $relationFollow);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $relationFollowRepository->save($relationFollow, true);

            return $this->redirectToRoute('app_relation_follow_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('relation_follow/edit.html.twig', [
            'relation_follow' => $relationFollow,
            'form' => $form,
        ]);
    }

   #[Route('/{id}', name: 'app_relation_follow_delete', methods: ['POST'])]
    public function delete(Request $request, RelationFollow $relationFollow, RelationFollowRepository $relationFollowRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$relationFollow->getId(), $request->request->get('_token'))) {
            $relationFollowRepository->remove($relationFollow, true);
        }

        return $this->redirectToRoute('app_relation_follow_index', [], Response::HTTP_SEE_OTHER);
    }
}