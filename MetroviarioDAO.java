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
                        rs.getInt("pontuacao_total"));
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
                        rs.getInt("pontuacao_total"));
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

    public List<Metroviario> listarRanking() {
        List<Metroviario> ranking = new ArrayList<>();
        // Modified query to show all records for debugging
        String sql = "SELECT * FROM metroviarios ORDER BY pontuacao_total DESC";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                System.err.println("Conexão retornou null em listarRanking()");
                return ranking;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                System.out.println("Executando query: " + sql);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // Log each record for debugging
                        System.out.println(String.format(
                                "ID: %d, Nome: %s, Registro: %s, Pontuação: %d",
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("registro"),
                                rs.getInt("pontuacao_total")));

                        Metroviario m = new Metroviario(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("registro"),
                                rs.getInt("pontuacao_total"));
                        ranking.add(m);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ranking: " + e.getMessage());
            e.printStackTrace();
        }
        return ranking;
    }

    public boolean verificarAdmin(String registro, String senha) {
        System.out.println("\n=== Verificando Admin ===");
        System.out.println("Registro informado: [" + registro + "]");
        System.out.println("Senha informada: [" + senha + "]");

        String sql = "SELECT * FROM administradores WHERE registro = ? AND senha = ?";
        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                System.err.println("Conexão retornou null em verificarAdmin()");
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, registro);
                stmt.setString(2, senha);

                try (ResultSet rs = stmt.executeQuery()) {
                    boolean isAdmin = rs.next();
                    System.out.println("É admin? " + isAdmin);
                    return isAdmin;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar admin: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarMetroviario(String registro, String senha) {
        String sql = "SELECT * FROM metroviarios WHERE registro = ? AND senha = ?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registro);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Erro ao verificar metroviário: " + e.getMessage());
            return false;
        }
    }

    public List<Metroviario> listarPontuacoesJogador(String registro) {
        List<Metroviario> pontuacoes = new ArrayList<>();
        String sql = "SELECT * FROM metroviarios WHERE registro = ?";

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registro);
            System.out.println("Buscando pontuações para registro: " + registro);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Encontrado: " + rs.getString("nome") +
                            " - " + rs.getInt("pontuacao_total") + " pontos");

                    Metroviario m = new Metroviario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("registro"),
                            rs.getInt("pontuacao_total"));
                    pontuacoes.add(m);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pontuações: " + e.getMessage());
            e.printStackTrace();
        }
        return pontuacoes;
    }

    public void registrarTarefaRealizada(String registro, int idTarefa, int pontuacaoObtida) {
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            if (conn == null) {
                System.err.println("Erro: Não foi possível conectar ao banco de dados");
                return;
            }

            // Desativa o autocommit para usar transação
            conn.setAutoCommit(false);

            // Primeiro verifica se a tarefa já foi completada
            if (verificarTarefaCompletada(registro, idTarefa)) {
                System.out.println("Tarefa já foi completada anteriormente.");
                return;
            }

            // Registra a tarefa realizada
            String sql = "INSERT INTO tarefas_realizadas (id_metroviario, id_tarefa, pontuacao_obtida) " +
                    "SELECT m.id, ?, ? FROM metroviarios m WHERE m.registro = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idTarefa);
                stmt.setInt(2, pontuacaoObtida);
                stmt.setString(3, registro);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Atualiza a pontuação total chamando a procedure
                    try (CallableStatement cstmt = conn.prepareCall("{CALL atualizar_pontuacao_metroviario(?)}")) {
                        // Primeiro obtém o ID do metroviário
                        int idMetroviario = obterIdMetroviario(conn, registro);
                        if (idMetroviario > 0) {
                            cstmt.setInt(1, idMetroviario);
                            cstmt.execute();

                            // Se tudo deu certo, commit na transação
                            conn.commit();
                            System.out.println("Pontuação registrada e atualizada com sucesso!");
                        }
                    }
                } else {
                    System.err.println("Erro: Metroviário não encontrado");
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao registrar pontuação: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Erro ao fazer rollback: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }

    private int obterIdMetroviario(Connection conn, String registro) throws SQLException {
        String sql = "SELECT id FROM metroviarios WHERE registro = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }

    public boolean verificarTarefaCompletada(String registro, int idTarefa) {
        String sql = "SELECT COUNT(*) FROM tarefas_realizadas tr " +
                "JOIN metroviarios m ON tr.id_metroviario = m.id " +
                "WHERE m.registro = ? AND tr.id_tarefa = ?";

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registro);
            stmt.setInt(2, idTarefa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean completada = rs.getInt(1) > 0;
                    System.out.println("Verificação de tarefa " + idTarefa +
                            " para registro " + registro + ": " +
                            (completada ? "já completada" : "não completada"));
                    return completada;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar tarefa completada: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void testarConexao() {
        try (Connection conn = Conexao.conectar()) {
            if (conn != null) {
                System.out.println("Conexão com banco de dados estabelecida com sucesso!");
                // Testar uma consulta simples
                try (Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT registro, senha FROM metroviarios")) {

                    System.out.println("Registros encontrados:");
                    while (rs.next()) {
                        System.out.println("Registro: " + rs.getString("registro") +
                                ", Senha: " + rs.getString("senha"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao testar conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void debugListarTodos() {
        String sql = "SELECT registro, nome, pontuacao_total FROM metroviarios";

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\n=== Registros no banco ===");
                while (rs.next()) {
                    System.out.println("Registro: " + rs.getString("registro") +
                            ", Nome: " + rs.getString("nome") +
                            ", Pontuação: " + rs.getInt("pontuacao_total"));
                }
                System.out.println("========================");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar registros: " + e.getMessage());
        }
    }

    public void adicionarPontuacao(String registro, int pontos) {
        String sql = "UPDATE metroviarios SET pontuacao_total = pontuacao_total + ? WHERE registro = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pontos);
            stmt.setString(2, registro);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pontuação atualizada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pontuação: " + e.getMessage());
        }
    }

    // === MÉTODO PARA ZERAR PONTUAÇÃO ===
    public void zerarPontuacao(String registro) {
        String sql = "UPDATE metroviarios SET pontuacao_total = 0 WHERE registro = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao zerar pontuação: " + e.getMessage());
        }
    }

    // === MÉTODO PARA LIMPAR TAREFAS REALIZADAS ===
    public void limparTarefasRealizadas(String registro) {
        String sql = "DELETE FROM tarefas_realizadas WHERE id_metroviario = (SELECT id FROM metroviarios WHERE registro = ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao limpar tarefas realizadas: " + e.getMessage());
        }
    }

    
}