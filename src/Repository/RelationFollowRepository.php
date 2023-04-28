<?php

namespace App\Repository;

use App\Entity\RelationFollow;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<RelationFollow>
 *
 * @method RelationFollow|null find($id, $lockMode = null, $lockVersion = null)
 * @method RelationFollow|null findOneBy(array $criteria, array $orderBy = null)
 * @method RelationFollow[]    findAll()
 * @method RelationFollow[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RelationFollowRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, RelationFollow::class);
    }

    public function save(RelationFollow $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(RelationFollow $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function checkFollow($compte1,$compte2){
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("SELECT r FROM APP\Entity\RelationFollow r WHERE r.follower = :compte1 AND r.followed = :compte2 ")
            ->setParameter('compte1',$compte1)
            ->setParameter('compte2',$compte2)
        ;
        return $query->getResult();
    }

    public function rechercheid($compte1,$compte2){
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("SELECT id FROM APP\Entity\RelationFollow r WHERE r.follower = :compte1 AND r.followed = :compte2 ")
            ->setParameter('compte1',$compte1)
            ->setParameter('compte2',$compte2)
        ;
        return $query->getResult();
    }
    
    public function delete($compte1,$compte2): void
    {
        $entityManager = $this->getEntityManager();
        $queryBuilder = $entityManager->createQueryBuilder();
    
        $queryBuilder->delete(RelationFollow::class, 'r')
            ->where('r.follower = :follower')
            ->andWhere('r.followed = :followed')
            ->setParameter('follower', $compte1)
            ->setParameter('followed', $compte2);
    
        $queryBuilder->getQuery()->execute();
    }
    
    
//    /**
//     * @return RelationFollow[] Returns an array of RelationFollow objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('r.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?RelationFollow
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
