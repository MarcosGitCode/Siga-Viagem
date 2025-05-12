import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetroviarioDAO {
    public void inserir(Metroviario m) {
        String sql = "INSERT INTO metroviarios (nome, email, senha, registro, pontuacao_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEmail());
            stmt.setString(3, m.getSenha());
            stmt.setString(4, m.getRegistro());
            stmt.setInt(5, m.getPontuacaoTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public Metroviario buscarPorId(int id) {
        String sql = "SELECT * FROM metroviarios WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Metroviario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("registro"),
                    rs.getInt("pontuacao_total")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }

    public List<Metroviario> listarTodos() {
        List<Metroviario> lista = new ArrayList<>();
        String sql = "SELECT * FROM metroviarios";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Metroviario m = new Metroviario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("registro"),
                    rs.getInt("pontuacao_total")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Metroviario m) {
        String sql = "UPDATE metroviarios SET nome=?, email=?, senha=?, registro=?, pontuacao_total=? WHERE id=?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEmail());
            stmt.setString(3, m.getSenha());
            stmt.setString(4, m.getRegistro());
            stmt.setInt(5, m.getPontuacaoTotal());
            stmt.setInt(6, m.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM metroviarios WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
}