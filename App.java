import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/metro"; // conecta no banco 'metro' local
        String user = "root"; // usuário padrão do mysql
        String password = "batata123"; // senha do mysql (altere se precisar)

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("conectado com sucesso :)");
            conn.close();
        } catch (SQLException e) {
            System.out.println("ops, erro na conexão: " + e.getMessage());
        }
    }
}
