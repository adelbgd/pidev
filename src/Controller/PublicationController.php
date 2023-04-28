<?php

namespace App\Controller;

use App\Entity\Publication;
use App\Form\PublicationType;
use App\Repository\PublicationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;


#[Route('/publication')]
class PublicationController extends AbstractController
{
    #[Route('/', name: 'app_publication_index', methods: ['GET'])]
    public function index(PublicationRepository $publicationRepository, PaginatorInterface $paginator,Request $request ): Response
    {
        $publication = $publicationRepository->findAll();
        $publication = $paginator->paginate(
            $publication , /* query NOT result */
            $request->query->getInt(key:'page', default: 1)/*page number*/,
            limit:2/*limit per page*/
        );  

        return $this->render('publication/index.html.twig', [
           'publications' => $publication ,
            'pagination' => $publication,
        ]);
    }

    #[Route('/new', name: 'app_publication_new', methods: ['GET', 'POST'])]
    public function new(Request $request, PublicationRepository $publicationRepository): Response
    {
        $publication = new Publication();
        $form = $this->createForm(PublicationType::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $publication->setDatePub(new \DateTime('now'));
            $publicationRepository->save($publication, true);

            return $this->redirectToRoute('app_publication_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('publication/new.html.twig', [
            'publication' => $publication,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_publication_show', methods: ['GET'])]
    public function show(Publication $publication): Response
    { $comment=$publication->getCommentaires();
        
        return $this->render('publication/show.html.twig', [
            'publication' => $publication,
            'comment'=> $comment,
            
        ]);
    }

    #[Route('/{id}/edit', name: 'app_publication_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Publication $publication, PublicationRepository $publicationRepository): Response
    {
        $form = $this->createForm(PublicationType::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $publicationRepository->save($publication, true);

            return $this->redirectToRoute('app_publication_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('publication/edit.html.twig', [
            'publication' => $publication,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_publication_delete', methods: ['POST'])]
    public function delete(Request $request, Publication $publication, PublicationRepository $publicationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$publication->getId(), $request->request->get('_token'))) {
            $publicationRepository->remove($publication, true);
        }

        return $this->redirectToRoute('app_publication_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/publication/{id}/signal', name: 'app_publication_signal', methods: ['POST'])]
public function signalPublication(Request $request, publication $publication,PublicationRepository $publicationRepository): Response
{
    $entityManager = $this->getDoctrine()->getManager();

    $publication->setNbSignals($publication->getNbSignals() + 1);

    $entityManager->persist($publication);

    $entityManager->flush();
    if($publication->getNbSignals() >=4){
        $publicationRepository->remove($publication, true);
        return $this->redirectToRoute('app_publication_index');
    }

    return $this->redirectToRoute('app_publication_show', ['id' => $publication->getId()]);
}
}
