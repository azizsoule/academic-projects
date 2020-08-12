package mairie;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ex {
    private SimpleIntegerProperty id_extrait;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenoms;
    private SimpleStringProperty date_de_naissance;

    public Ex(Integer id_extrait, String nom, String prenoms, String date_de_naissance) {
        this.id_extrait = new SimpleIntegerProperty(id_extrait);
        this.nom = new SimpleStringProperty(nom);
        this.prenoms = new SimpleStringProperty(prenoms);
        this.date_de_naissance = new SimpleStringProperty(date_de_naissance);
    }

    public int getId_extrait() {
        return id_extrait.get();
    }

    public SimpleIntegerProperty id_extraitProperty() {
        return id_extrait;
    }

    public void setId_extrait(int id_extrait) {
        this.id_extrait.set(id_extrait);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenoms() {
        return prenoms.get();
    }

    public SimpleStringProperty prenomsProperty() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms.set(prenoms);
    }

    public String getDate_de_naissance() {
        return date_de_naissance.get();
    }

    public SimpleStringProperty date_de_naissanceProperty() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance.set(date_de_naissance);
    }
}
