public class UsuarioLogado {
    private static String registro;

    public static String getRegistro() {
        System.out.println("=== Debug UsuarioLogado.getRegistro() ===");
        System.out.println("Registro atual: " + registro);
        return registro;
    }

    public static void setRegistro(String novoRegistro) {
        System.out.println("=== Debug UsuarioLogado.setRegistro() ===");
        System.out.println("Registro anterior: " + registro);
        System.out.println("Novo registro: " + novoRegistro);

        if (novoRegistro != null && !novoRegistro.trim().isEmpty()) {
            registro = novoRegistro.trim();
        }
    }

    public static boolean isLogado() {
        return registro != null && !registro.trim().isEmpty();
    }

    public static void logout() {
        registro = null;
    }
}
