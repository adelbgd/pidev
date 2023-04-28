<?php

namespace App\Controller;

use App\Entity\Publication;
use App\Form\Publication1Type;
use App\Repository\PublicationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/publication1')]
class Publication1Controller extends AbstractController
{
    #[Route('/', name: 'app_publication1_index', methods: ['GET'])]
    public function index(PublicationRepository $publicationRepository): Response
    {
        return $this->render('publication1/index.html.twig', [
            'publications' => $publicationRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_publication1_new', methods: ['GET', 'POST'])]
    public function new(Request $request, PublicationRepository $publicationRepository): Response
    {
        $publication = new Publication();
        $form = $this->createForm(Publication1Type::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $publicationRepository->save($publication, true);

            return $this->redirectToRoute('app_publication1_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('publication1/new.html.twig', [
            'publication' => $publication,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_publication1_show', methods: ['GET'])]
    public function show(Publication $publication): Response
    {$comment=$publication->getCommentaires();
        return $this->render('publication1/show.html.twig', [
            'publication' => $publication,
            'comment'=> $comment,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_publication1_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Publication $publication, PublicationRepository $publicationRepository): Response
    {
        $form = $this->createForm(Publication1Type::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $publicationRepository->save($publication, true);

            return $this->redirectToRoute('app_publication1_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('publication1/edit.html.twig', [
            'publication' => $publication,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_publication1_delete', methods: ['POST'])]
    public function delete(Request $request, Publication $publication, PublicationRepository $publicationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$publication->getId(), $request->request->get('_token'))) {
            $publicationRepository->remove($publication, true);
        }

        return $this->redirectToRoute('app_publication1_index', [], Response::HTTP_SEE_OTHER);
    }
}
