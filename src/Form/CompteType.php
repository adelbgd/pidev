<?php

namespace App\Form;

use App\Entity\Compte;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\File;
use Eckinox\TinymceBundle\Form\Type\TinymceType ; 


class CompteType extends AbstractType
{

    // test
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
           // ->add('id_utilisateur') 
            ->add('nom')
         /*   ->add('date_de_creation')
            ->add('nbr_followers')
            ->add('nbr_followings')
            ->add('nbr_produits_publies')
            ->add('banned') */
            ->add('profile_image', FileType::class, [
                'label' => 'Image de profile',
                'mapped' => false,
                'required' => false,
                'attr' => [
                    'accept' => '.jpg, .jpeg, .png',
                ],    'constraints' => [
                    new File([
                        'maxSize' => '2M',
                        'mimeTypes' => [
                            'image/jpeg',
                            'image/png',
                        ],
                        'mimeTypesMessage' => 'Please upload a valid JPG, JPEG or PNG image',
                    ])
                ],
            
            ])
            ->add('bio', TinymceType::class, [
                'attr' => [
                    'toolbar' => 'bold italic underline | bullist numlist emoticons', // Ajouter le plugin 'emoticons' à la barre d'outils
                    'plugins' => 'emoticons', // Activer le plugin 'emoticons'
                    'emoticons_data' => '[{"shortcut": "🙂", "url": "https://example.com/emoji/smile.png"}, {"shortcut": "🙁", "url": "https://example.com/emoji/sad.png"}]' ,// Définir la liste des emojis disponibles
                    'label' => 'bio'
                ],
            ])
            


        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Compte::class,
        ]);
    }
}
