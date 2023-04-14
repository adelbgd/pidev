<?php

namespace App\Entity;
use App\Repository\CommandeRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: CommandeRepository::class)]
class Commande
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name:"id_commande")]
    private ?int $id = null;
    

    #[ORM\Column(length: 150)]
    #[Assert\NotBlank(message:"")]
    private ?string $prenom = null;

    #[ORM\Column(length: 150)]
    #[Assert\NotBlank(message:"")]
    private ?string $nom = null;


    
    #[ORM\Column]
    #[Assert\NotBlank(message:"")]
    #[Assert\Length(exactly: 8, exactMessage : "Le code postal doit etre un nombre de huit chiffres")]
    private ?int $num = null;

    #[ORM\Column(length: 150)]
    #[Assert\NotBlank(message:"")]
    private ?string $mail = null;

    #[ORM\Column(length: 150)]
    #[Assert\NotBlank(message:"")]
    private ?string $region = null;

    #[ORM\Column(length: 150)]
    #[Assert\NotBlank(message:"")]
    private ?string $ville = null;

    #[ORM\Column(length: 150)]
    #[Assert\NotBlank(message:"")]
    private ?string $rue = null;

    #[ORM\Column]
    #[Assert\NotBlank(message:"")]
    #[Assert\Positive(message:"Le code postal doit etre un nombre positif !")]
    #[Assert\Length(exactly: 4, exactMessage : "Le code postal doit etre un nombre de quatre chiffres")]
    private ?int $codePostal = null;
    
    #[ORM\ManyToOne]
    #[ORM\JoinColumn(nullable: false,name:"id_panier",referencedColumnName:"id_panier")]
    private ?Panier $panier = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getNum(): ?int
    {
        return $this->num;
    }

    public function setNum(int $num): self
    {
        $this->num = $num;

        return $this;
    }

    public function getMail(): ?string
    {
        return $this->mail;
    }

    public function setMail(string $mail): self
    {
        $this->mail = $mail;

        return $this;
    }

    public function getRegion(): ?string
    {
        return $this->region;
    }

    public function setRegion(string $region): self
    {
        $this->region = $region;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getRue(): ?string
    {
        return $this->rue;
    }

    public function setRue(string $rue): self
    {
        $this->rue = $rue;

        return $this;
    }

    public function getCodePostal(): ?int
    {
        return $this->codePostal;
    }

    public function setCodePostal(int $codePostal): self
    {
        $this->codePostal = $codePostal;

        return $this;
    }

    public function getPanier(): ?Panier
    {
        return $this->panier;
    }

    public function setPanier(?Panier $panier): self
    {
        $this->panier = $panier;

        return $this;
    }


}
