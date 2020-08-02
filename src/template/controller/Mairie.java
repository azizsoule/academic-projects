package controller;

class Mairie {
    private final Integer id_mairie;

    private final String communes;

    private String addresse;

    private String nom_officier;

    private String signature;


    public Integer getId_mairie() {
        return id_mairie;
    }

    public String getCommunes() {
        return communes;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getNom_officier() {
        return nom_officier;
    }

    public void setNom_officier(String nom_officier) {
        this.nom_officier = nom_officier;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    Mairie(Integer id_mairie, String commune, String addresse, String nom_officier, String signature) {
        this.id_mairie = id_mairie;
        this.communes = commune;
        this.addresse = addresse;
        this.nom_officier = nom_officier;
        this.signature = signature;
    }
}
