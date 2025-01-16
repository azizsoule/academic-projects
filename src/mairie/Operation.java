package mairie;

import java.sql.SQLException;
import java.util.ArrayList;

public class Operation {
    public static void  insert(String date_op,String libelle,Integer id){
        Database db = new Database();
        db.connect();
        try {
            db.sendQuery("INSERT INTO operation(date_operation,libelle_operation,id_agent) VALUES (?,?,?)",new ArrayList<Object>(){
                {
                    add(date_op);
                    add(libelle);
                    add(id);
                }
            },resultSet -> {});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        db.disconnect();
    }
}
