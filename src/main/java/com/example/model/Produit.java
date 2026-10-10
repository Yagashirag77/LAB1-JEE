
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produit {

    // Identifiant unique attribué automatiquement à chaque produit
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Détails et caractéristiques du produit
    private String nom;
    private String description;
    private String marque;
    private String categorie;

    // Informations concernant le prix et le stock
    private BigDecimal prix;
    private int quantiteStock;

    // Constructeur vide nécessaire au fonctionnement de JPA
    public Produit() {
    }

    // Initialisation simple d'un produit
    public Produit(String nom, BigDecimal prix) {
        this.nom = nom;
        this.prix = prix;
    }

    // Initialisation avec toutes les informations du produit
    public Produit(String nom, BigDecimal prix, String description,
                   String marque, String categorie, int quantiteStock) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.marque = marque;
        this.categorie = categorie;
        this.quantiteStock = quantiteStock;
    }

    // Méthodes permettant de consulter et modifier les données

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    // Présentation complète du produit lors de l'affichage en console
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", marque='" + marque + '\'' +
                ", categorie='" + categorie + '\'' +
                ", quantiteStock=" + quantiteStock +
                '}';
    }
}
