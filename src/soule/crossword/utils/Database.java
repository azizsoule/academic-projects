package soule.crossword.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Database {

    private Connection connection;
    public static final String WIDTH = "largeur";
    public static final String HEIGTH = "hauteur";
    public static final String PUZZLE_NUMBER = "num_grille";
    public static final String CLUE = "definition";
    public static final String HORIZONTAL = "horizontal";
    public static final String ROW = "ligne";
    public static final String COLUMN = "colonne";
    public static final String SOLUTION = "solution";

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/crossword";
            String user = "root" ;
            String pwd ="";
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException | NullPointerException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getRandomPuzzleNumber() {
        Random random = new Random();
        return random.nextInt(10)+1;
    }

    public HashMap<String,Integer> getDimensions(int puzzleNumber) {
        HashMap<String,Integer> dimensions = new HashMap<>();
        try {
            String getDimensionsSql = "SELECT * FROM tp5_grille WHERE num_grille=?";
            PreparedStatement preparedStatement = connection.prepareStatement(getDimensionsSql);
            preparedStatement.setInt(1, puzzleNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            dimensions.put(HEIGTH, resultSet.getInt(HEIGTH));
            dimensions.put(WIDTH, resultSet.getInt(WIDTH));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dimensions;
    }

    /**
     * @return une liste des définitions pour un jeu de la base
     *
     * Le type Object est nécessaire comme les valeurs sont
     * des entiers, des booléens et des chaînes de caractères.
     */
    public ArrayList<HashMap<String,Object>> getClues(int puzzleNumber) {
        ArrayList<HashMap<String,Object>> clues = new ArrayList<HashMap<String,Object>>();
        try {
            String getCluesSql = "SELECT * FROM tp5_mots WHERE num_grille=?";
            PreparedStatement preparedStatement = connection.prepareStatement(getCluesSql);
            preparedStatement.setInt(1, puzzleNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HashMap<String,Object> map = new HashMap<>();
                map.put(CLUE, resultSet.getString(CLUE));
                map.put(HORIZONTAL, resultSet.getBoolean(HORIZONTAL));
                map.put(ROW, resultSet.getInt(ROW));
                map.put(COLUMN, resultSet.getInt(COLUMN));
                map.put(SOLUTION, resultSet.getString(SOLUTION));
                clues.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clues;
    }

}

