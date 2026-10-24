package mairie;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Mar {
    private SimpleIntegerProperty id_acte_mar;
    private SimpleStringProperty date_mar;
    private SimpleStringProperty lieu_mariage;
    private SimpleStringProperty nom_conjoint;
    private SimpleStringProperty nom_conjointe;
    private SimpleObjectProperty<Button> visualiser;
    private SimpleObjectProperty<Button> modifier;

    public Mar(Integer id_acte_mar, String date_mar, String lieu_mariage, String nom_conjoint, String nom_conjointe, Button visualiser, Button modifier) {
        this.id_acte_mar = new SimpleIntegerProperty(id_acte_mar);
        this.date_mar = new SimpleStringProperty(date_mar);
        this.lieu_mariage = new SimpleStringProperty(lieu_mariage);
        this.nom_conjoint = new SimpleStringProperty(nom_conjoint);
        this.nom_conjointe = new SimpleStringProperty(nom_conjointe);
        this.visualiser = new SimpleObjectProperty<>(visualiser);
        this.modifier = new SimpleObjectProperty<>(modifier);
    }

    public int getId_acte_mar() {
        return id_acte_mar.get();
    }

    public SimpleIntegerProperty id_acte_marProperty() {
        return id_acte_mar;
    }

    public void setId_acte_mar(int id_acte_mar) {
        this.id_acte_mar.set(id_acte_mar);
    }

    public String getDate_mar() {
        return date_mar.get();
    }

    public SimpleStringProperty date_marProperty() {
        return date_mar;
    }

    public void setDate_mar(String date_mar) {
        this.date_mar.set(date_mar);
    }

    public String getLieu_mariage() {
        return lieu_mariage.get();
    }

    public SimpleStringProperty lieu_mariageProperty() {
        return lieu_mariage;
    }

    public void setLieu_mariage(String lieu_mariage) {
        this.lieu_mariage.set(lieu_mariage);
    }

    public String getNom_conjoint() {
        return nom_conjoint.get();
    }

    public SimpleStringProperty nom_conjointProperty() {
        return nom_conjoint;
    }

    public void setNom_conjoint(String nom_conjoint) {
        this.nom_conjoint.set(nom_conjoint);
    }

    public String getNom_conjointe() {
        return nom_conjointe.get();
    }

    public SimpleStringProperty nom_conjointeProperty() {
        return nom_conjointe;
    }

    public void setNom_conjointe(String nom_conjointe) {
        this.nom_conjointe.set(nom_conjointe);
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
