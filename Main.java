public class Main {
    public static void main(String[] args) {
        MetroviarioDAO dao = new MetroviarioDAO();

        Metroviario novo = new Metroviario("", "", "", "", 0);
        dao.inserir(novo);

        for (Metroviario m : dao.listarTodos()) {
            System.out.println(m.getId() + "" + m.getNome());
        }

        Metroviario buscado = dao.buscarPorId(1);
        if (buscado != null) {
            System.out.println("Buscado: " + buscado.getNome());
        }

        if (buscado != null) {
            buscado.setNome("Maria Silva");
            dao.atualizar(buscado);
        }

        dao.deletar(1);
    }
}