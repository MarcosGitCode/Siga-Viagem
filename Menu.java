import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {
    private final CardLayout layout;
    private JPanel painelPrincipal;
    private MenuAdmin menuAdmin; // Torne menuAdmin um atributo para acessar depois

    public Menu() {
        setTitle("Siga Viagem");
        setSize(1280, 853);
        setResizable(false); // nao deixa a janela redimensionavel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);
        painelPrincipal.setOpaque(false);

        // Inicializa apenas as telas do menu principal
        painelPrincipal.add(new MenuPrincipal(layout, painelPrincipal), "Menu");
        painelPrincipal.add(new MenuLogin(layout, painelPrincipal), "Login");
        painelPrincipal.add(new MenuOpcoes(layout, painelPrincipal), "Opções");
        painelPrincipal.add(new MenuPlacar(layout, painelPrincipal), "Placar");
        menuAdmin = new MenuAdmin(layout, painelPrincipal);
        painelPrincipal.add(menuAdmin, "Admin");
        // NÃO chame menuAdmin.atualizarUsuarios() aqui!

        add(painelPrincipal);
        setVisible(true);
    }

    // Método para inicializar as telas do jogo após o login
    public static void inicializarTelasJogo(CardLayout layout, JPanel painelPrincipal) {
        if (!UsuarioLogado.isLogado()) {
            System.out.println("Tentativa de inicializar telas do jogo sem usuário logado!");
            return;
        }

        System.out.println("Inicializando telas do jogo para o usuário: " + UsuarioLogado.getRegistro());

        painelPrincipal.add(new Parte1(layout, painelPrincipal), "Jogo");
        painelPrincipal.add(new TremPortaAberta(layout, painelPrincipal), "PortaAberta");
        painelPrincipal.add(new TremPesOlhando(layout, painelPrincipal), "PesOlhando");
        painelPrincipal.add(new TremEmergencia(layout, painelPrincipal), "TremEmergencia");
        painelPrincipal.add(new Trem1(layout, painelPrincipal), "Trem1");
        painelPrincipal.add(new TremPortaAberta(layout, painelPrincipal), "TremPortaAberta");
        painelPrincipal.add(new JogoItens(layout, painelPrincipal), "JogoItens");
        painelPrincipal.add(new JogoPA(layout, painelPrincipal), "JogoPA");
        painelPrincipal.add(new JogoDireitaChaveCBTC(layout, painelPrincipal), "JogoDireitaChaveCBTC");
        painelPrincipal.add(new TremPainelExterno(layout, painelPrincipal), "TremPainelExterno");
        painelPrincipal.add(new JogoReversoraOficial(layout, painelPrincipal), "JogoReversoraOficial");
        painelPrincipal.add(new TremPortaFechada(), "TremPortaFechada");
    }

    public void mostrarMenuAdmin() {
        menuAdmin.atualizarUsuarios(); // Atualiza a lista de usuários antes de mostrar
        layout.show(painelPrincipal, "Admin");
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        // Exemplo de como mostrar o painel Admin corretamente:
        // menu.mostrarMenuAdmin();
    }
}