import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteInsercao {
    public static void main(String[] args) {
        // Inserir alguns metroviários com pontuações
        try (Connection conn = Conexao.conectar()) {
            // Primeiro limpar pontuações existentes
            String sqlLimpar = "UPDATE metroviarios SET pontuacao_total = 0";
            try (PreparedStatement stmtLimpar = conn.prepareStatement(sqlLimpar)) {
                stmtLimpar.executeUpdate();
            }

            // Atualizar pontuações
            String sqlUpdate = "UPDATE metroviarios SET pontuacao_total = ? WHERE registro = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
                // Inserir algumas pontuações de teste
                atualizarPontuacao(stmt, "R12345-6", 100); // Nikolas
                atualizarPontuacao(stmt, "R01758-2", 85); // Guilherme Calderan
                atualizarPontuacao(stmt, "R22222-2", 90); // Marcos
                atualizarPontuacao(stmt, "R33333-3", 95); // Guilherme Nunes
            }

            System.out.println("Pontuações inseridas com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir pontuações: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void atualizarPontuacao(PreparedStatement stmt, String registro, int pontuacao)
            throws SQLException {
        stmt.setInt(1, pontuacao);
        stmt.setString(2, registro);
        stmt.executeUpdate();
        System.out.println("Pontuação atualizada: " + registro + " -> " + pontuacao);
    }
}
