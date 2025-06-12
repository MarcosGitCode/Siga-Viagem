import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteInsercao {
    public static void main(String[] args) {
        try (Connection conn = Conexao.conectar()) {
            String sqlLimpar = "UPDATE metroviarios SET pontuacao_total = 0";
            try (PreparedStatement stmtLimpar = conn.prepareStatement(sqlLimpar)) {
                stmtLimpar.executeUpdate();
            }

            String sqlUpdate = "UPDATE metroviarios SET pontuacao_total = ? WHERE registro = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
                atualizarPontuacao(stmt, "R12345-6", 100);
                atualizarPontuacao(stmt, "R01758-2", 85);
                atualizarPontuacao(stmt, "R22222-2", 90);
                atualizarPontuacao(stmt, "R33333-3", 95);
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
