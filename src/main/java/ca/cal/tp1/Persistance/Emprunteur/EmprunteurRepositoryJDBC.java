package ca.cal.tp1.Persistance.Emprunteur;
import ca.cal.tp1.Models.Emprunteur;

import java.sql.*;

public class EmprunteurRepositoryJDBC {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:exercicejdbc;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";
    static Connection conn = null;


    static {
        // STEP 1: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE EMPRUNTEUR" +
                    "(id INTEGER not NULL," +
                    " matricule VARCHAR(255) NOT NULL," +
                    " prenom VARCHAR(255) NOT NULL," +
                    " nom VARCHAR(255) NOT NULL ," +
                    " password VARCHAR(255)NOT NULL ," +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveEmprunteur(Emprunteur emprunteur) {
        String sql = "INSERT INTO EMPRUNTEUR (id, matricule, prenom, nom, password) values (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, emprunteur.getId());
            preparedStatement.setString(3, emprunteur.getPrenom());
            preparedStatement.setString(4, emprunteur.getNom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void readEmprunteur(long id) {
        String sql = "SELECT * FROM EMPRUNTEUR WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getLong("id"));
                System.out.println("Matricule: " + resultSet.getLong("matricule"));
                System.out.println("Prenom: " + resultSet.getString("prenom"));
                System.out.println("Nom: " + resultSet.getString("nom"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
