import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class Agent {
    private final Database db;

    private final Integer id_agent;

    private String nom;

    private String prenoms;

    private String email;

    private String user_name;

    private String mot_d_passe;

    private Integer id_mairie;


    public Integer getId_agent() {
        return id_agent;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMot_d_passe() {
        return mot_d_passe;
    }

    public void setMot_d_passe(String mot_d_passe) {
        this.mot_d_passe = mot_d_passe;
    }

    public Integer getId_mairie() {
        return id_mairie;
    }

    public void setId_mairie(Integer id_mairie) {
        this.id_mairie = id_mairie;
    }

    //Ce constructeur cree un objet agent mairie à partir d'un id puis execute une requtte et recupere les infos depuis la bd
    //Ce contructeur renvoie une exception dans le cas ou aucun agent correspondant à l'id n'a ete trouve dans la bd
    Agent(Integer id_ag) throws SQLException{

        db = new Database();

        AtomicReference<ResultSet> result = new AtomicReference<ResultSet>();

        db.connect();
        db.sendQuery("SELECT * FROM agent WHERE id_agent=?",new ArrayList<Object>(){
            {
                add(id_ag);
            }
        }, resultSet -> {
            result.set(resultSet);
        });

        id_agent = result.get().getInt("id_agent");
        nom = result.get().getString("nom");
        prenoms = result.get().getString("prenoms");
        email = result.get().getString("email");
        user_name = result.get().getString("user_name");
        mot_d_passe = result.get().getString("mot_d_passe");
        id_mairie = result.get().getInt("id_mairie");

        db.disconnect();

    }

    //J'ai pas besoin d'expliquer hein ...
    Agent(int id_agent, String nom, String prenoms, String email,String userName, String password, int id_mairie) {
        db = new Database();
        this.id_agent = id_agent;
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.user_name = userName;
        this.mot_d_passe = password;
        this.id_mairie = id_mairie;
    }

    //Vérifie si un agent existe en à partir de son user_name et de son password
    //On s'en servira pour permettre à un agent de se connecter.
    Boolean agentExist(){

        ArrayList<Object> params = new ArrayList<Object>() {
            {
                add(user_name);
                add(mot_d_passe);
            }
        };

        AtomicBoolean exist = new AtomicBoolean(false);

        db.connect();
        try {
            db.sendQuery("SELECT * FROM agent WHERE user_name = ? AND mot_d_passe = ?",params,resultSet -> {
                try {
                    if (resultSet.next()) {
                        exist.set(true);
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            });
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        db.disconnect();
        return exist.get();
    }

    //Surchage statique de la methode precedente pour permettre de verifier di un agent existe sans instancier la classe Agent
    static Boolean agentExist(String userName, String password){
        Database bd = new Database("root", "root");

        ArrayList<Object> params = new ArrayList<Object>() {
            {
                add(userName);
                add(password);
            }
        };

        AtomicBoolean exist = new AtomicBoolean(false);

        bd.connect();
        try {
            bd.sendQuery("SELECT * FROM agent WHERE user_name = ? AND mot_d_passe = ?",params,resultSet -> {
                try {
                    if (resultSet.next()) {
                        exist.set(true);
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            });
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        bd.disconnect();

        return exist.get();
    }

    //la methode suivante permet d'inscrire un agent
    //Elle renvoie une exception au cas ou l'inscription echoue
    void registerAgent() throws SQLException{
        db.connect();
        db.sendQuery("INSERT INTO agent(nom,prenoms,email,user_name,mot_d_passe,id_mairie) VALUES(?,?,?,?,?)",new ArrayList<Object>() {
            {
                add(nom);
                add(prenoms);
                add(email);
                add(user_name);
                add(mot_d_passe);
                add(id_mairie);
            }
        }, resultSet -> {
        });
        db.disconnect();
    }
}