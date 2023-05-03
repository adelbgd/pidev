<?php

namespace App\Entity;

use App\Repository\RelationFollowRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: RelationFollowRepository::class)]
class RelationFollow
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?int $id_follower = null;

    #[ORM\Column]
    private ?int $id_followed = null;

    #[ORM\ManyToOne(targetEntity: Compte::class)]
    #[ORM\JoinColumn(name: "id_follower", referencedColumnName: "id")]
    private ?Compte $follower = null;

    #[ORM\ManyToOne(targetEntity: Compte::class)]
    #[ORM\JoinColumn(name: "id_followed", referencedColumnName: "id")]
    private ?Compte $followed = null;

    
    public function getFollower(): ?Compte
    {
        return $this->follower;
    }

    public function setFollower(?Compte $follower): self
    {
        $this->follower = $follower;

        return $this;
    }

    public function getFollowed(): ?Compte
    {
        return $this->followed;
    }

    public function setFollowed(?Compte $followed): self
    {
        $this->followed = $followed;

        return $this;
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdFollower(): ?int
    {
        return $this->id_follower;
    }

    public function setIdFollower(int $id_follower): self
    {
        $this->id_follower = $id_follower;

        return $this;
    }

    public function getIdFollowed(): ?int
    {
        return $this->id_followed;
    }

    public function setIdFollowed(int $id_followed): self
    {
        $this->id_followed = $id_followed;

        return $this;
    }
}