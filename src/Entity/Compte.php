<?php

namespace App\Entity;

use App\Repository\CompteRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: CompteRepository::class)]
class Compte
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?int $id_utilisateur = null;

    #[ORM\Column(length: 35)]
    private ?string $nom = null;

    #[ORM\Column(type: Types::DATE_MUTABLE )]
    private ?\DateTimeInterface $date_de_creation = null;

    #[ORM\Column]
    private ?int $nbr_followers = null;

    #[ORM\Column]
    private ?int $nbr_followings = null;

    #[ORM\Column]
    private ?int $nbr_produits_publies = null;

    #[ORM\Column]
    private ?int $banned = null;

    #[ORM\Column(length: 255)]
    private ?string $profile_image = null;
     
    #[ORM\OneToMany(mappedBy: "followed", targetEntity: RelationFollow::class)]
    private Collection $followers;
 
    #[ORM\OneToMany(mappedBy: "follower", targetEntity: RelationFollow::class)]
    private Collection $followings;

    #[ORM\Column(length: 255 , nullable:true)]
    private ?string $bio = null;

    public function getFollowers(): Collection
    {
        return $this->followers;
    }

    public function setFollowers(Collection $followers): self
    {
        $this->followers = $followers;
        return $this;
    }

    public function getFollowings(): Collection
    {
        return $this->followings;
    }

    public function setFollowings(Collection $followings): self
    {
        $this->followings = $followings;
        return $this;
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function setId(int $Id): self
    {
        $this->Id = $Id;

        return $this;
    }

    public function getIdUtilisateur(): ?int
    {
        return $this->id_utilisateur;
    }

    public function setIdUtilisateur(int $id_utilisateur): self
    {
        $this->id_utilisateur = $id_utilisateur;

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

    public function getDateDeCreation(): ?\DateTimeInterface
    {
        return $this->date_de_creation;
    }

    public function setDateDeCreation(\DateTimeInterface $date_de_creation): self
    {
        $this->date_de_creation = $date_de_creation;

        return $this;
    }

    public function getNbrFollowers(): ?int
    {
        return $this->nbr_followers;
    }

    public function setNbrFollowers(int $nbr_followers): self
    {
        $this->nbr_followers = $nbr_followers;

        return $this;
    }

    public function getNbrFollowings(): ?int
    {
        return $this->nbr_followings;
    }

    public function setNbrFollowings(int $nbr_followings): self
    {
        $this->nbr_followings = $nbr_followings;

        return $this;
    }

    public function getNbrProduitsPublies(): ?int
    {
        return $this->nbr_produits_publies;
    }

    public function setNbrProduitsPublies(int $nbr_produits_publies): self
    {
        $this->nbr_produits_publies = $nbr_produits_publies;

        return $this;
    }

    public function getBanned(): ?int
    {
        return $this->banned;
    }

    public function setBanned(int $banned): self
    {
        $this->banned = $banned;

        return $this;
    }

    public function getProfileImage(): ?string
    {
        return $this->profile_image;
    }

    public function setProfileImage(string $profile_image): self
    {
        $this->profile_image = $profile_image;

        return $this;
    }

    public function getBio(): ?string
    {
        return $this->bio;
    }

    public function setBio(string $bio): self
    {
        $this->bio = $bio;

        return $this;
    }

   

}