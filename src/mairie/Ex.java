package mairie;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Ex {
    private SimpleIntegerProperty id_extrait;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenoms;
    private SimpleStringProperty date_de_naissance;
    private SimpleObjectProperty<Button> visualiser;
    private SimpleObjectProperty<Button> modifier;

    public Ex(Integer id_extrait, String nom, String prenoms, String date_de_naissance, Button visualiser, Button modifier) {
        this.id_extrait = new SimpleIntegerProperty(id_extrait);
        this.nom = new SimpleStringProperty(nom);
        this.prenoms = new SimpleStringProperty(prenoms);
        this.date_de_naissance = new SimpleStringProperty(date_de_naissance);
        this.visualiser = new SimpleObjectProperty<>(visualiser);
        this.modifier = new SimpleObjectProperty<>(modifier);
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

    public Button getVisualiser() {
        return visualiser.get();
    }

    public SimpleObjectProperty<Button> visualiserProperty() {
        return visualiser;
    }

    public void setVisualiser(Button visualiser) {
        this.visualiser.set(visualiser);
    }

    public Button getModifier() {
        return modifier.get();
    }

    public SimpleObjectProperty<Button> modifierProperty() {
        return modifier;
    }

    public void setModifier(Button modifier) {
        this.modifier.set(modifier);
    }
}
