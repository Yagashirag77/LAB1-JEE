
package com.example;

import com.example.model.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import org.h2.tools.Server;

public class App {

    public static void main(String[] args) {

        // Lancement de l'interface web pour consulter notre base H2
        try {
            Server.createWebServer("-web", "-webPort", "8083").start();
            System.out.println("Console H2 accessible sur : http://localhost:8083");
        } catch (Exception e) {
            System.out.println("Impossible de lancer l'interface H2.");
            e.printStackTrace();
        }

        // Préparation de la connexion avec Hibernate
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate-demo");

        // Ajout de nos produits dans la base
        ajouterProduits(emf);

        // Vérification des données enregistrées
        afficherProduits(emf);

        // Libération des ressources Hibernate
        emf.close();
    }

    private static void ajouterProduits(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Quelques articles de notre catalogue informatique
            Produit p1 = new Produit(
                    "ROG Strix G16",
                    new BigDecimal("15999.00"),
                    "PC portable gaming RTX 4060",
                    "ASUS",
                    "Ordinateurs",
                    12
            );

            Produit p2 = new Produit(
                    "Galaxy S24",
                    new BigDecimal("8499.00"),
                    "Smartphone avec écran AMOLED",
                    "Samsung",
                    "Smartphones",
                    25
            );

            Produit p3 = new Produit(
                    "MX Master 3S",
                    new BigDecimal("1099.00"),
                    "Souris sans fil ergonomique",
                    "Logitech",
                    "Accessoires",
                    40
            );

            Produit p4 = new Produit(
                    "iPad Air",
                    new BigDecimal("7299.00"),
                    "Tablette pour le travail et les études",
                    "Apple",
                    "Tablettes",
                    18
            );

            // Enregistrement de chaque article avec JPA
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);

            em.getTransaction().commit();

            System.out.println("Les 4 produits ont été enregistrés !");

        } catch (Exception e) {

            // Annulation des opérations si une erreur survient
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Problème pendant l'ajout des produits.");
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    private static void afficherProduits(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();

        try {
            // Récupération de notre catalogue avec une requête JPQL
            List<Produit> produits = em.createQuery(
                    "SELECT p FROM Produit p", Produit.class
            ).getResultList();

            System.out.println("\n===== CATALOGUE DES PRODUITS =====");

            for (Produit p : produits) {
                System.out.println(p);
            }

            // Test de recherche d'un article via son identifiant
            System.out.println("\n===== RECHERCHE PAR IDENTIFIANT =====");

            Long idRecherche = 2L;
            Produit resultat = em.find(Produit.class, idRecherche);

            if (resultat != null) {
                System.out.println("Article trouvé : " + resultat);
            } else {
                System.out.println("Aucun produit avec l'ID : " + idRecherche);
            }

        } finally {
            // Fermeture de la session après consultation
            em.close();
        }
    }
}
