<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230503181518 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE compte (id INT AUTO_INCREMENT NOT NULL, id_utilisateur INT NOT NULL, nom VARCHAR(35) NOT NULL, date_de_creation DATE NOT NULL, nbr_followers INT NOT NULL, nbr_followings INT NOT NULL, nbr_produits_publies INT NOT NULL, banned INT NOT NULL, profile_image VARCHAR(255) NOT NULL, bio VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE relation_follow (id INT AUTO_INCREMENT NOT NULL, id_follower INT NOT NULL, id_followed INT NOT NULL, INDEX IDX_7F1044E6E90DE5D3 (id_follower), INDEX IDX_7F1044E61DD95082 (id_followed), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE relation_follow ADD CONSTRAINT FK_7F1044E6E90DE5D3 FOREIGN KEY (id_follower) REFERENCES compte (id)');
        $this->addSql('ALTER TABLE relation_follow ADD CONSTRAINT FK_7F1044E61DD95082 FOREIGN KEY (id_followed) REFERENCES compte (id)');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY fk_n');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D2FBB81F FOREIGN KEY (id_panier) REFERENCES panier (id_panier)');
        $this->addSql('ALTER TABLE commande RENAME INDEX fk_n TO IDX_6EEAA67D2FBB81F');
        $this->addSql('ALTER TABLE ligne_panier DROP FOREIGN KEY fk_u');
        $this->addSql('ALTER TABLE ligne_panier DROP FOREIGN KEY fk_y');
        $this->addSql('ALTER TABLE ligne_panier CHANGE nom_produit nom_produit VARCHAR(150) NOT NULL, CHANGE description description VARCHAR(150) NOT NULL, CHANGE image image VARCHAR(150) NOT NULL');
        $this->addSql('ALTER TABLE ligne_panier ADD CONSTRAINT FK_21691B42FBB81F FOREIGN KEY (id_panier) REFERENCES panier (id_panier)');
        $this->addSql('ALTER TABLE ligne_panier ADD CONSTRAINT FK_21691B4F7384557 FOREIGN KEY (id_produit) REFERENCES produit (id_prod)');
        $this->addSql('ALTER TABLE ligne_panier RENAME INDEX fk_u TO IDX_21691B42FBB81F');
        $this->addSql('ALTER TABLE ligne_panier RENAME INDEX fk_y TO IDX_21691B4F7384557');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY fk_t');
        $this->addSql('ALTER TABLE panier CHANGE id_panier id_panier INT NOT NULL');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF26B3CA4B FOREIGN KEY (id_user) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE panier RENAME INDEX fk_t TO IDX_24CC0DF26B3CA4B');
        $this->addSql('ALTER TABLE produit CHANGE nom nom VARCHAR(150) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE relation_follow DROP FOREIGN KEY FK_7F1044E6E90DE5D3');
        $this->addSql('ALTER TABLE relation_follow DROP FOREIGN KEY FK_7F1044E61DD95082');
        $this->addSql('DROP TABLE compte');
        $this->addSql('DROP TABLE relation_follow');
        $this->addSql('DROP TABLE messenger_messages');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D2FBB81F');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT fk_n FOREIGN KEY (id_panier) REFERENCES panier (id_panier) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commande RENAME INDEX idx_6eeaa67d2fbb81f TO fk_n');
        $this->addSql('ALTER TABLE ligne_panier DROP FOREIGN KEY FK_21691B42FBB81F');
        $this->addSql('ALTER TABLE ligne_panier DROP FOREIGN KEY FK_21691B4F7384557');
        $this->addSql('ALTER TABLE ligne_panier CHANGE nom_produit nom_produit VARCHAR(30) NOT NULL, CHANGE description description TEXT NOT NULL, CHANGE image image VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE ligne_panier ADD CONSTRAINT fk_u FOREIGN KEY (id_panier) REFERENCES panier (id_panier) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE ligne_panier ADD CONSTRAINT fk_y FOREIGN KEY (id_produit) REFERENCES produit (id_prod) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE ligne_panier RENAME INDEX idx_21691b4f7384557 TO fk_y');
        $this->addSql('ALTER TABLE ligne_panier RENAME INDEX idx_21691b42fbb81f TO fk_u');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY FK_24CC0DF26B3CA4B');
        $this->addSql('ALTER TABLE panier CHANGE id_panier id_panier INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT fk_t FOREIGN KEY (id_user) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE panier RENAME INDEX idx_24cc0df26b3ca4b TO fk_t');
        $this->addSql('ALTER TABLE produit CHANGE nom nom VARCHAR(255) NOT NULL');
    }
}
