package mairie;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

class ActeMariage extends Extrait{

     private Integer id_acte_mar;
     private Date date_mar;
     private String lieu;	private String nom_conjoint;
     private String date_divorse;
     private String motif_divorse;

    ActeMariage(Integer id_extrait, String nom, String prenoms, Date date_naiss, Time heure_naiss, String lieu_naiss, String nom_pere, String domicile_pere, String nom_mere, String domicile_mere, Date date_enregistrem, Integer numero_enregistrem, Integer id_mairie, Integer id_agent) {
        super(id_extrait, nom, prenoms, date_naiss, heure_naiss, lieu_naiss, nom_pere, domicile_pere, nom_mere, domicile_mere, date_enregistrem, numero_enregistrem, id_mairie, id_agent);
    }

    ActeMariage(Integer id_ex) throws SQLException {
        super(id_ex);
    }

    ActeMariage(ResultSet result) throws SQLException {
        super(result);
    }

}
