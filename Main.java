public class Main {
    public static void main(String[] args) {
        MetroviarioDAO dao = new MetroviarioDAO();

        // Criar novo metroviário
        Metroviario novo = new Metroviario("", "", "", "", 0);
        dao.inserir(novo);

        // Listar todos
        for (Metroviario m : dao.listarTodos()) {
            System.out.println(m.getId() + "" + m.getNome());
        }

        // Buscar por ID
        Metroviario buscado = dao.buscarPorId(1);
        if (buscado != null) {
            System.out.println("Buscado: " + buscado.getNome());
        }

        // Atualizar
        if (buscado != null) {
            buscado.setNome("Maria Silva");
            dao.atualizar(buscado);
        }

        // Deletar
        dao.deletar(1);
    }
}