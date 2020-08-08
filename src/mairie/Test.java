package mairie;

import java.sql.SQLException;

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

        Database x = new Database("root", "root", "src/mairie");

        x.connect();

        try {
            x.sendQuery("  UPDATE agent SET nom='SOULE' WHERE id_agent=1  ",func);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        x.disconnect();

        //System.out.println(Agent.agentExist("papitou15", "papitou@mairiepassword99"));

        //Extrait.search("S", func);

    }

}
