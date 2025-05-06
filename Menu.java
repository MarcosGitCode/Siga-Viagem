import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {
    private final CardLayout layout;
    private final JPanel painelPrincipal;

    public Menu() {
        setTitle("Siga");
        setSize(1280, 853);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);

        painelPrincipal.add(new MenuPrincipal(layout, painelPrincipal), "Menu");
        painelPrincipal.add(new MenuOpcoes(layout, painelPrincipal), "Opções");
        painelPrincipal.add(new MenuLogin(layout, painelPrincipal), "Login");

        add(painelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}