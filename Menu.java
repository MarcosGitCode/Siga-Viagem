// Menu.java
import java.awt.*;
import javax.swing.*;


public class Menu extends JFrame {
    private final CardLayout layout;
    private JPanel painelPrincipal;

    public Menu() {
        setTitle("Siga Viagem");
        setSize(1280, 853);
        setResizable(false); // Impede que a janela seja redimensionável
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);
        painelPrincipal.setOpaque(false); // Permite transparência no painel principal

        painelPrincipal.add(new MenuPrincipal(layout, painelPrincipal), "Menu");
        painelPrincipal.add(new MenuLogin(layout, painelPrincipal), "Login");
        painelPrincipal.add(new MenuOpcoes(layout, painelPrincipal), "Opções");
        painelPrincipal.add(new Parte1(layout, painelPrincipal), "Jogo");

        // Adiciona o painel de administração
        painelPrincipal.add(new MenuAdmin(layout, painelPrincipal), "Admin");

        add(painelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}