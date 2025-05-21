import java.util.HashMap;
import java.util.Map;

public class GerenciadorPontuacao {
    private static final Map<String, Integer> PONTUACOES_TAREFAS = new HashMap<>();

    static {
        // Definir pontuações para cada tarefa do jogo
        PONTUACOES_TAREFAS.put("PortaAberta", 20);
        PONTUACOES_TAREFAS.put("PesOlhando", 15);
        PONTUACOES_TAREFAS.put("TremEmergencia", 25);
        PONTUACOES_TAREFAS.put("JogoItens", 20);
        PONTUACOES_TAREFAS.put("TremPainelExternoPortaIsolada", 20);
    }

    public static void registrarPontuacao(String registro, String tarefa) {
        if (!PONTUACOES_TAREFAS.containsKey(tarefa)) {
            return;
        }

        MetroviarioDAO dao = new MetroviarioDAO();
        int pontos = PONTUACOES_TAREFAS.get(tarefa);
        dao.adicionarPontuacao(registro, pontos);
    }
}
