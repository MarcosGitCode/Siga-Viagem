import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SequenciaDecisoes {
    private static SequenciaDecisoes instance;
    private Map<String, Boolean> tarefasCompletadas;
    private Map<String, Integer> idsTarefas;
    private boolean gameOver;
    private String registroUsuario;

    private SequenciaDecisoes() {
        tarefasCompletadas = new HashMap<>();
        idsTarefas = new HashMap<>();
        gameOver = false;
        initializeTarefas();
    }

    public static SequenciaDecisoes getInstance() {
        if (instance == null) {
            instance = new SequenciaDecisoes();
        }
        return instance;
    }

    public void setRegistroUsuario(String registro) {
        this.registroUsuario = registro;
        // Reseta o estado quando um novo usuário começa
        tarefasCompletadas.clear();
        gameOver = false;
    }

    private void initializeTarefas() {
        // IDs das tarefas conforme banco de dados
        idsTarefas.put("REVERSORA_INICIO", 1);
        idsTarefas.put("CBCT_RM", 2);
        idsTarefas.put("PA_INICIAL", 3);
        idsTarefas.put("FECHAR_PORTAS", 4);
        idsTarefas.put("INFORMAR_CCO_INICIAL", 5);
        idsTarefas.put("PA_MEIO", 6);
        idsTarefas.put("PEGAR_EQUIPAMENTOS", 7);
        idsTarefas.put("VERIFICAR_SOLEIRA", 8);
        idsTarefas.put("VERIFICAR_EMERGENCIA", 9);
        idsTarefas.put("INFORMAR_CCO_VERIFICACOES", 10);
        idsTarefas.put("ISOLAR_PORTA", 11);
        idsTarefas.put("FECHAR_TRAVAR_PORTA", 12);
        idsTarefas.put("COLOCAR_ADESIVO", 13);
        idsTarefas.put("VERIFICAR_LUZ", 14);
        idsTarefas.put("INFORMAR_CCO_FINAL", 15);
        idsTarefas.put("PA_FINAL", 16);
        idsTarefas.put("INSERIR_CHAVE", 17);
        idsTarefas.put("CBCT_AM_FINAL", 18);
        idsTarefas.put("REVERSORA_FINAL", 19);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isTarefaCompletada(String tarefa) {
        return tarefasCompletadas.getOrDefault(tarefa, false);
    }

    public void marcarTarefaCompletada(String tarefa) {
        tarefasCompletadas.put(tarefa, true);
    }

    public boolean verificarSequencia(String tarefaAtual, String... prerequisitos) {
        if (gameOver) {
            return false;
        }

        // Verifica se todos os pré-requisitos foram completados
        for (String prereq : prerequisitos) {
            if (!tarefasCompletadas.getOrDefault(prereq, false)) {
                return false;
            }
        }

        return true;
    }

    public void registrarPontuacao(String tarefa, int pontos) {
        if (registroUsuario != null && !registroUsuario.isEmpty() && !gameOver) {
            MetroviarioDAO dao = new MetroviarioDAO();

            // Verifica se a tarefa já foi completada
            if (!dao.verificarTarefaCompletada(registroUsuario, idsTarefas.get(tarefa))) {
                dao.adicionarPontuacao(registroUsuario, pontos);
                dao.registrarTarefaRealizada(registroUsuario, idsTarefas.get(tarefa), pontos);
                marcarTarefaCompletada(tarefa);
            }
        }
    }

    public void resetarEstado() {
        tarefasCompletadas.clear();
        gameOver = false;
    }
}
