package mairie;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class ActeMariage{
    private final Database db;
    private Integer id_agent;
    private Integer id_acte_mar;
    private String date_mar;
    private String lieu_mariage;
    private Integer id_extrait_conjoint;
    private Integer id_extrait_conjointe;
    private String date_enregistrement;



    public Integer getId_acte_mar() {
        return id_acte_mar;
    }
    public void setId_acte_mar(Integer id_acte_mar) {
        this.id_acte_mar = id_acte_mar;
    }
    public String getDate_mar() {
        return date_mar;
    }
    public void setDate_mar(String date_mar) {
        this.date_mar = date_mar;
    }
    public String getLieu_mariage() {
        return lieu_mariage;
    }
    public void setLieu_mariage(String lieu_mariage) {
        this.lieu_mariage = lieu_mariage;
    }
    public Integer getId_extrait_conjoint() {
        return id_extrait_conjoint;
    }
    public void setId_extrait_conjoint(Integer id_extrait_conjoint) {
        this.id_extrait_conjoint = id_extrait_conjoint;
    }

    public Integer getId_extrait_conjointe() {
        return id_extrait_conjointe;
    }

    public void setId_extrait_conjointe(Integer id_extrait_conjointe) {
        this.id_extrait_conjointe = id_extrait_conjointe;
    }
    public String getDate_enregistrement() {
        return date_enregistrement.toString();
    }
    public void setDate_enregistrement(String date_enregistrement) {
        this.date_enregistrement = date_enregistrement;
    }


    public Integer getId_agent() {
        return id_agent;
    }

    public void setId_agent(Integer id_agent) {
        this.id_agent = id_agent;
    }

    public ActeMariage(Integer id_agent, String date_mar, String lieu_mariage, Integer id_extrait_conjoint, Integer id_extrait_conjointe, String date_enregistrement) {
        db = new Database("root","root","mairie");
        this.id_agent = id_agent;
        this.date_mar = date_mar;
        this.lieu_mariage = lieu_mariage;
        this.id_extrait_conjoint = id_extrait_conjoint;
        this.id_extrait_conjointe = id_extrait_conjointe;
        this.date_enregistrement = date_enregistrement;
    }

    ActeMariage(Integer id_acte_mariage) throws SQLException {
        db = new Database();

        AtomicReference<ResultSet> result = new AtomicReference<ResultSet>();

        db.connect();

        db.sendQuery("SELECT * FROM acte_de_mariage WHERE id_acte_mar=?",new ArrayList<Object>(){
            {
                add(id_acte_mariage);
            }
        }, resultSet -> {
            if(resultSet.next())
                result.set(resultSet);
        });
        id_acte_mar = result.get().getInt("id_acte_mar");
        date_mar = result.get().getString("date_mar");
        lieu_mariage = result.get().getString("lieu_mariage");
        id_extrait_conjoint = result.get().getInt("id_extrait_conjoint");
        id_extrait_conjointe = result.get().getInt("id_extrait_conjointe");
        date_enregistrement = result.get().getString("date_enregistrement");
        id_agent = result.get().getInt("id_agent");
    }

//    ActeMariage(ResultSet result, Database db) throws SQLException {
//        super(result);
//        this.db = db;
//    }
    public void save() throws SQLException {

        db.connect();
        db.sendQuery("INSERT INTO acte_de_mariage(date_mar,lieu_mariage,id_extrait_conjoint,id_extrait_conjointe,date_enregistrement,id_agent) VALUES(?,?,?,?,?,?)", new ArrayList<Object>() {
            {
                add(date_mar);
                add(lieu_mariage);
                add(id_extrait_conjoint);
                add(id_extrait_conjointe);
                add(date_enregistrement);
                add(id_agent);
            }
        }, resultSet -> {

        });

        db.disconnect();

    }

}
