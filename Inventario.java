import java.util.HashSet;
import java.util.Set;

public class Inventario {
    // armazena todos os itens únicos do inventário em memória, compartilhado p/ toda a aplicação
    private static final Set<String> itens = new HashSet<>();

    public static void adicionar(String item) {
        itens.add(item);
    }

    public static boolean contem(String item) {
        return itens.contains(item);
    }

    public static void remover(String item) {
        itens.remove(item);
    }

    public static Set<String> getItens() {
        return itens;
    }

    public static void limpar() {
        itens.clear();
    }
}