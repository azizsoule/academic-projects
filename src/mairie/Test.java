package mairie;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    static Func func = resultSet -> {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_agent") + " " + resultSet.getString("nom"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    };

    public static void main(String[] args){

        Database db = new Database("root", "root", "mairie");
//
//        x.connect();
//
//        try {
//            x.sendQuery("  UPDATE agent SET nom='SOULE' WHERE id_agent=1  ",func);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        x.disconnect();
//
//        //System.out.println(Agent.agentExist("papitou15", "papitou@mairiepassword99"));
//
//        //Extrait.search("S", func);
//
        db.connect();
        try{
            db.sendQuery("SELECT id_extrait FROM individus WHERE numero_enregistrem=? AND date_enregistrem=?",new ArrayList<Object>()
            {
                {
                    add(1);
                    add("2020-08-13");
                }
            },resultSet -> {
                try {
                    if(resultSet.next()){
                        System.out.println(resultSet.getInt("id_extrait"));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
//            db.sendQuery("SELECT id_extrait FROM individus WHERE numero_enregistrem=? AND date_enregistrem=?",new ArrayList<Object>()
//            {
//                {
//                    add(1);
//                    add("2020-08-12");
//                }
//            },resultSet -> {
//                if(resultSet.next()){
//                    int id_conjointe = resultSet.getInt("id_extrait");
//                    System.out.println("id_conjoint: "+id_conjointe);
//                }
//            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
