import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    static Func func = resultSet -> {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("nom"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    };

    public static void main(String[] args){

        Database x = new Database("root", "root", "bd_rencontre");

        x.connect();

        try {
            x.sendQuery("UPDATE individu SET nom='Soulé' WHERE id=?", new ArrayList<Object>() {
                {
                    add(1);
                }
            },func);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        x.disconnect();

        System.out.println(Agent.agentExist("papitou15", "papitou@mairiepassword99"));

        Extrait.search("S", func);

    }

}
