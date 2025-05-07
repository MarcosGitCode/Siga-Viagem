// Menu.java
import java.awt.*;
import javax.swing.*;


public class Menu extends JFrame {
    private final CardLayout layout;
    private JPanel painelPrincipal;

    public Menu() {
        setTitle("Siga");
        setSize(1280, 853);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);
        painelPrincipal.setOpaque(false); // Permite transparência no painel principal

        painelPrincipal.add(new MenuPrincipal(layout, painelPrincipal), "Menu");
        painelPrincipal.add(new MenuLogin(layout, painelPrincipal), "Login");
        painelPrincipal.add(new MenuOpcoes(layout, painelPrincipal), "Opções");
        painelPrincipal.add(new MenuJogo(layout, painelPrincipal), "Jogo");

        add(painelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}