import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        
        try (Connection conn = Conexao.conectar()) {
            if (conn != null) {
                System.out.println("✓ Conexão estabelecida com sucesso!");
            } else {
                System.out.println("✗ Falha na conexão!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("✗ Erro na conexão: " + e.getMessage());
            return;
        }

        
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM administradores")) {

            ResultSet rs = stmt.executeQuery();
            System.out.println("\n=== Administradores cadastrados ===");
            boolean encontrouAlgum = false;

            while (rs.next()) {
                encontrouAlgum = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Registro: [" + rs.getString("registro") + "]");
                System.out.println("Senha: [" + rs.getString("senha") + "]");
                System.out.println("--------------------");
            }

            if (!encontrouAlgum) {
                System.out.println("❌ NENHUM administrador encontrado na tabela!");
                System.out.println("\nExecute os seguintes comandos no MySQL:");
                System.out.println("USE metro;");
                System.out.println("INSERT INTO administradores (nome, email, senha, registro)");
                System.out.println("VALUES ('Demetrius', 'demetrius@metrosp.com.br', 'Adm!9999', 'R99999-9');");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar admins: " + e.getMessage());
            if (e.getMessage().contains("Table") && e.getMessage().contains("doesn't exist")) {
                System.out.println("\n❌ A tabela 'administradores' não existe!");
                System.out.println("\nExecute os seguintes comandos no MySQL:");
                System.out.println("USE metro;");
                System.out.println("CREATE TABLE administradores (");
                System.out.println("    id INT AUTO_INCREMENT PRIMARY KEY,");
                System.out.println("    nome VARCHAR(100) NOT NULL,");
                System.out.println("    email VARCHAR(255) NOT NULL,");
                System.out.println("    senha VARCHAR(30) NOT NULL,");
                System.out.println("    registro VARCHAR(8) NOT NULL");
                System.out.println(");");
            }
        }
    }
}
