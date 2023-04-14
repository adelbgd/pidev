<?php

namespace App\Controller;

use App\Entity\Client;
use App\Form\ClientType;
use App\Repository\ClientRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ClientController extends AbstractController
{
    #[Route('/client', name: 'app_client')]
    public function index(): Response
    {
        return $this->render('user/index.html.twig', [
            'controller_name' => 'ClientController',
        ]);
    }
    #[Route('/getAllUsers', name: 'getAllUsers')]
    public function getAllUsers(ClientRepository $userRep): Response
    {
        $allUsers=$userRep->findAll();
        return $this->render('user/getAll.html.twig', [
            'allUsers' => $allUsers,
        ]);
    }
}
