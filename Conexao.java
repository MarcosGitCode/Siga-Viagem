import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/metro";
    private static final String USER = "root";
<<<<<<< HEAD
    private static final String PASSWORD = "Deco2005";
=======
    private static final String PASSWORD = "batata123";
>>>>>>> ff2bfa46714037a853616e224a719989ac45a6ab


    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}