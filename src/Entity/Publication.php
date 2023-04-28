<?php

namespace App\Entity;

use App\Repository\PublicationRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: PublicationRepository::class)]
class Publication
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?int $id_user = null;

    #[ORM\Column(length: 255)]
    #[Assert\Regex(
        pattern: '/^[A-Za-z]+$/',
        message: 'The title must contain only letters.',
    )]
    private ?string $titre_pub = null;

    #[ORM\Column(length: 255)]
    private ?string $contenu_pub = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    private ?\DateTimeInterface $date_pub = null;

    #[ORM\Column]
    private ?int $nb_signals = null;

    #[ORM\OneToMany(mappedBy: 'id_pub', targetEntity: Commentaire::class)]
    private Collection $commentaires;

    public function __construct()
    {
        $this->commentaires = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdUser(): ?int
    {
        return $this->id_user;
    }

    public function setIdUser(int $id_user): self
    {
        $this->id_user = $id_user;

        return $this;
    }

    public function getTitrePub(): ?string
    {
        return $this->titre_pub;
    }

    public function setTitrePub(string $titre_pub): self
    {
        $this->titre_pub = $titre_pub;

        return $this;
    }

    public function getContenuPub(): ?string
    {
        return $this->contenu_pub;
    }

    public function setContenuPub(string $contenu_pub): self
    {
        $this->contenu_pub = $contenu_pub;

        return $this;
    }

    public function getDatePub(): ?\DateTimeInterface
    {
        return $this->date_pub;
    }

    public function setDatePub(\DateTimeInterface $date_pub): self
    {
        $this->date_pub = $date_pub;

        return $this;
    }

    public function getNbSignals(): ?int
    {
        return $this->nb_signals;
    }

    public function setNbSignals(int $nb_signals): self
    {
        $this->nb_signals = $nb_signals;

        return $this;
    }
    public function getNbSignalsCount(): int
    {
        return $this->getNbSignals()->count();
    }
    /**
     * @return Collection<int, Commentaire>
     */
    public function getCommentaires(): Collection
    {
        
        return $this->commentaires;
    }
    public function getCommentairesCount(): int
    {
        return $this->getCommentaires()->count();
    }

    public function addCommentaire(Commentaire $commentaire): self
    {
        if (!$this->commentaires->contains($commentaire)) {
            $this->commentaires->add($commentaire);
            $commentaire->setIdPub($this);
        }

        return $this;
    }

    public function removeCommentaire(Commentaire $commentaire): self
    {
        if ($this->commentaires->removeElement($commentaire)) {
            // set the owning side to null (unless already changed)
            if ($commentaire->getIdPub() === $this) {
                $commentaire->setIdPub(null);
            }
        }

        return $this;
    }
    public function __toString()
    {

        return $this->titre_pub;
    }
}
