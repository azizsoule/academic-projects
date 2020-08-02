import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

/* NB : N'oubliez surtout pas d'ajouter mysql-connector au projet
sinon vous risquez d'etre confronté à des erreurs un peu chiantes !*/

public class Database {

    //Je crois bien qu'à travers le nom de chacun des attributs suivants, vous comprendrez naturrellement leur utilité
    // Jean-Noel ta mercon en passant . J'espere que tu apprendra un jour à nommer tes variables.
    private  String url = "jdbc:mysql://";

    private final String user;

    private final String host;

    private final String password;

    private final String dbName;

    private final int port;

    //Attribut contenant la connexion creee entre notre logiciel et la base de donnees
    private Connection connection = null;

    //Attribut permettant d'envoyer les requettes à la base de données
    private PreparedStatement statement = null;

    //Attribut ou seront enregistrés les resultats des requettes
    private ResultSet resultSet = null;

    //Je vous laisse le soin de comprendre l'utilité de chacun des constructeurs crees

    //Constructeur 1
    Database(String user, String password, String dbName) {
        host = "localhost";
        port = 3306;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
        url = this.url + host + ":" + port + "/" + this.dbName;
    }

    //Constructeur 2
    Database(String host, int port, String dbName, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
        url = this.url + this.host + ":" + this.port + "/" + this.dbName;
    }

    //Constructeur 3 : Constructeur specifique au projet.
    //Vous remarquerez que le nom de la base de donnéés est déja enregistré dans
    //l'attribut dbName de la classe Database
    Database() {
        host = "localhost";
        port = 3306;
        this.user = "root";
        this.password = "root";
        this.dbName = "mairie";
        url = this.url + host + ":" + port + "/" + this.dbName;
    }

    Database(String user, String password) {
        host = "localhost";
        port = 3306;
        this.user = user;
        this.password = password;
        this.dbName = "mairie";
        url = this.url + host + ":" + port + "/" + this.dbName;
    }

    //Methode permettant de se connecter a la base de donnees
    void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("connexion reussie");
        }
        catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    //Methode permetant d'envoyer des requettes non preparees à la base de données
    void sendQuery(String query,Func func) throws SQLException{
        int res = 1;

        String req = "";

        for (int j = 0; j < 6; j++) {
            req += query.charAt(j);
        }

        try {
            statement = connection.prepareStatement(query);

            req = req.toUpperCase();

            if ("SELECT".equals(req)) {
                resultSet = statement.getResultSet();

                func.wheQuerryComplete(resultSet);

                System.out.println();
            } else {
                res = statement.executeUpdate();
            }

            if (res == 0) {
                throw new SQLException("Query failed");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            // Ici nous liberons les ressources de l'ordinateur apres execution d'une requette
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                statement = null;
            }
        }
    }

    //Surchage de la methode sendQuery qui permet d'envoyer des requettes préparees.
    //le parametre est une instance de la classe ArrayList<Object> qui les valeurs (dans l'ordre d'apparition des ?) qui doivent
    //etre insérées dans la requette préparée
    void sendQuery(String query,ArrayList<Object> arguments,Func function) throws SQLException {
        int res = 1;

        String req = "";

        for (int j = 0; j < 6; j++) {
            req += query.charAt(j);
        }

        try {
            statement = connection.prepareStatement(query);

            if (!arguments.isEmpty()) {
                for (int i = 0; i < arguments.size(); i++) {
                    if(arguments.get(i) instanceof Integer) {
                        statement.setInt(i+1, (Integer) arguments.get(i));
                    }

                    if(arguments.get(i) instanceof String) {
                        statement.setString(i+1, (String) arguments.get(i));

                    }

                    if(arguments.get(i) instanceof Double) {
                        statement.setDouble(i+1, (Double) arguments.get(i));
                    }

                    if(arguments.get(i) instanceof Boolean) {
                        statement.setBoolean(i+1, (Boolean) arguments.get(i));
                    }

                    if(arguments.get(i) instanceof Date) {
                        statement.setDate(i+1, (java.sql.Date) arguments.get(i));
                    }
                }
            }

            System.out.println(statement.toString());

            req = req.toUpperCase();

            if ("SELECT".equals(req)) {
                resultSet = statement.executeQuery();
            } else {
                res = statement.executeUpdate();
            }

            if (res == 0) {
                throw new SQLException("Querry failed");
            }

            if (resultSet != null) {
                function.wheQuerryComplete(resultSet);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            // Ici nous liberons les ressources de l'ordinateur apres execution d'une requette
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                statement = null;
            }
        }
    }

    //Methode permetant de detruire la connexiion existant avec la base de donnees
    void disconnect(){
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        connection = null;
    }
}
