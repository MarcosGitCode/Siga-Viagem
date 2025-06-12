import java.sql.*;

public class TestePontuacao {
    public static void main(String[] args) {
        MetroviarioDAO dao = new MetroviarioDAO();

        
        dao.registrarTarefaRealizada("R12345-6", 1, 20); // Nikolas - Verificar porta
        dao.registrarTarefaRealizada("R12345-6", 2, 30); // Nikolas - Isolar porta

        dao.registrarTarefaRealizada("R01758-2", 1, 20); // Guilherme - Verificar porta

        
        dao.debugListarTodos();
    }
}
