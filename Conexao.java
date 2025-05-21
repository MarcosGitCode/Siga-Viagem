import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/metro";
    private static final String USER = "root";
<<<<<<< HEAD
    private static final String PASSWORD = "imtdb";
=======
    private static final String PASSWORD = "batata123";
>>>>>>> 32f3d22a20ededd2ac6378d5d918811e171fd769


    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}