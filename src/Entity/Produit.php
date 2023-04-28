<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use App\Validator\NumberExceptZero;

#[ORM\Entity(repositoryClass: ProduitRepository::class)]
class Produit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    

    #[ORM\Column(length: 255)]
    #[Assert\Length(min : 2, max : 300, minMessage : "La description d'un produit doit comporter au moins {{ limit }} caractères", maxMessage : "La description d'un produit doit comporter au plus {{ limit }} caractères"    )]
    private ?string $description = null;

    #[ORM\Column(length: 255)]
    #[Assert\Length(min : 3, max : 50, minMessage : "La statut d'un produit doit comporter au moins {{ limit }} caractères", maxMessage : "La statut d'un produit doit comporter au plus {{ limit }} caractères"    )]
    private ?string $statut = null;

    #[ORM\Column(length: 255)]
    #[Assert\Length(min : 2, max : 50, minMessage : "Le nom d'un produit doit comporter au moins {{ limit }} caractères", maxMessage : "Le nom d'un produit doit comporter au plus {{ limit }} caractères"    )]
    private ?string $nom = null;

    #[ORM\Column]
    #[Assert\NotEqualTo(value : 0,  message : "La valeur d’un produit ne doit pas être égal à 0"   )]
    #[NumberExceptZero([
        'message' => 'La valeur doit être un entier sauf zéro.'
    ])]
    private ?float $valeur = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Assert\LessThanOrEqual([
        'value' => new \DateTime(),
        'message' => 'La date doit être antérieure ou égale à la date d\'aujourd\'hui.',
    ])]
    private ?\DateTimeInterface $date = null;

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $image = null;

    #[ORM\ManyToOne(inversedBy: 'produits')]
    private ?Categorie $categorie = null;

    #[ORM\Column]
    private ?int $rate = null;

   

    

    public function __construct()
    {
        $this->produits = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getStatut(): ?string
    {
        return $this->statut;
    }

    public function setStatut(string $statut): self
    {
        $this->statut = $statut;

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

    public function getValeur(): ?float
    {
        return $this->valeur;
    }

    public function setValeur(float $valeur): self
    {
        $this->valeur = $valeur;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    

    /**
     * @return Collection<int, self>
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    public function addProduit(self $produit): self
    {
        if (!$this->produits->contains($produit)) {
            $this->produits->add($produit);
            $produit->setCategories($this);
        }

        return $this;
    }

    public function removeProduit(self $produit): self
    {
        if ($this->produits->removeElement($produit)) {
            // set the owning side to null (unless already changed)
            if ($produit->getCategories() === $this) {
                $produit->setCategories(null);
            }
        }

        return $this;
    }

    public function getCategorie(): ?Categorie
    {
        return $this->categorie;
    }

    public function setCategorie(?Categorie $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    public function getRate(): ?int
    {
        return $this->rate;
    }

    public function setRate(int $rate): self
    {
        $this->rate = $rate;

        return $this;
    }

    
}
