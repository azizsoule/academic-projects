package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

class Extrait {

    private final Database db;

    private final Integer id_extrait;

    private String nom;

    private String prenoms;

    private final Date date_naiss;

    private final Time heure_naiss;

    private final String lieu_naiss;

    private String nom_pere;

    private String domicile_pere;

    private String nom_mere;

    private String domicile_mere;

    private final Date date_enregistrem;

    private final Integer numero_enregistrem;

    private final Integer id_mairie;

    private final Integer id_agent;

    public Integer getId_extrait() {
        return id_extrait;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public Time getHeure_naiss() {
        return heure_naiss;
    }

    public String getLieu_naiss() {
        return lieu_naiss;
    }

    public Date getDate_enregistrem() {
        return date_enregistrem;
    }

    public Integer getNumero_enregistrem() {
        return numero_enregistrem;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getNom_pere() {
        return nom_pere;
    }

    public void setNom_pere(String nom_pere) {
        this.nom_pere = nom_pere;
    }

    public String getDomicile_pere() {
        return domicile_pere;
    }

    public void setDomicile_pere(String domicile_pere) {
        this.domicile_pere = domicile_pere;
    }

    public String getNom_mere() {
        return nom_mere;
    }

    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }

    public String getDomicile_mere() {
        return domicile_mere;
    }

    public void setDomicile_mere(String domicile_mere) {
        this.domicile_mere = domicile_mere;
    }

    public Integer getId_mairie() {
        return id_mairie;
    }

    public Integer getId_agent() {
        return id_agent;
    }


    Extrait(Integer id_extrait, String nom, String prenoms, Date date_naiss, Time heure_naiss, String lieu_naiss, String nom_pere, String domicile_pere, String nom_mere, String domicile_mere, Date date_enregistrem, Integer numero_enregistrem, Integer id_mairie, Integer id_agent) {
        db = new Database("root", "root");
        this.id_extrait = id_extrait;
        this.nom = nom;
        this.prenoms = prenoms;
        this.date_naiss = date_naiss;
        this.heure_naiss = heure_naiss;
        this.lieu_naiss = lieu_naiss;
        this.nom_pere = nom_pere;
        this.domicile_pere = domicile_pere;
        this.nom_mere = nom_mere;
        this.domicile_mere = domicile_mere;
        this.date_enregistrem = date_enregistrem;
        this.numero_enregistrem = numero_enregistrem;
        this.id_agent = id_agent;
        this.id_mairie = id_mairie;
    }

    Extrait(Integer id_ex) throws SQLException {
        db = new Database();

        AtomicReference<ResultSet> result = new AtomicReference<ResultSet>();

        db.connect();

        db.sendQuery("SELECT * FROM individus WHERE id_extrait=?",new ArrayList<Object>(){
            {
                add(id_ex);
            }
        }, resultSet -> {
            result.set(resultSet);
        });

        id_extrait = result.get().getInt("id_extrait");
        nom = result.get().getString("nom");
        prenoms = result.get().getString("prenoms");
        date_naiss = result.get().getDate("date_naiss");
        heure_naiss = result.get().getTime("heure_naiss");
        lieu_naiss = result.get().getString("lieu_naiss");
        nom_pere = result.get().getString("nom_pere");
        domicile_pere = result.get().getString("domicile_pere");
        nom_mere = result.get().getString("nom_mere");
        domicile_mere = result.get().getString("domicile_mere");
        date_enregistrem = result.get().getDate("date_enregistrem");
        numero_enregistrem = result.get().getInt("numero_enregistrem");
        id_mairie = result.get().getInt("id_mairie");
        id_agent = result.get().getInt("id_agent");

        db.disconnect();

    }

    Extrait(ResultSet result) throws SQLException {
        db = new Database();

        id_extrait = result.getInt("id_extrait");
        nom = result.getString("nom");
        prenoms = result.getString("prenoms");
        date_naiss = result.getDate("date_naiss");
        heure_naiss = result.getTime("heure_naiss");
        lieu_naiss = result.getString("lieu_naiss");
        nom_pere = result.getString("nom_pere");
        domicile_pere = result.getString("domicile_pere");
        nom_mere = result.getString("nom_mere");
        domicile_mere = result.getString("domicile_mere");
        date_enregistrem = result.getDate("date_enregistrem");
        numero_enregistrem = result.getInt("numero_enregistrem");
        id_mairie = result.getInt("id_mairie");
        id_agent = result.getInt("id_agent");
    }

    static void search(String string, Func onResult) {
        Database bd = new Database();

        bd.connect();

        try {
            bd.sendQuery("SELECT * FROM individus WHERE lower(nom) LIKE ? OR lower(prenoms) LIKE ?",new ArrayList<Object>() {
                {
                    add("%"+string.toLowerCase()+"%");
                    add("%"+string.toLowerCase()+"%");
                }
            }, resultSet -> {
                onResult.wheQuerryComplete(resultSet);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.disconnect();
        }

    }

    void save() throws SQLException {

        db.connect();
        db.sendQuery("INSERT INTO extrait(nom,prenoms,date_naiss,heure_naiss,lieu_naiss,nom_pere,domicile_pere,nom_mere,domicile_mere,date_enregistrem,numero_enregistrem,id_mairie,id_agent) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new ArrayList<Object>() {
            {
                add(nom);
                add(prenoms);
                add(date_naiss);
                add(heure_naiss);
                add(lieu_naiss);
                add(nom_pere);
                add(domicile_pere);
                add(nom_mere);
                add(domicile_mere);
                add(date_enregistrem);
                add(numero_enregistrem);
                add(id_mairie);
                add(id_agent);
            }
        }, resultSet -> {

        });

        db.disconnect();

    }

}
