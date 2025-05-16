import java.awt.*;
import javax.swing.*;


public class Menu extends JFrame {
    private final CardLayout layout;
    private JPanel painelPrincipal;
    

    public Menu() {
        setTitle("Siga Viagem");
        setSize(1280, 853);
        setResizable(false); // nao deixa a janela redimensionavel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);
        painelPrincipal.setOpaque(false); 

        painelPrincipal.add(new MenuPrincipal(layout, painelPrincipal), "Menu");
        painelPrincipal.add(new MenuLogin(layout, painelPrincipal), "Login");
        painelPrincipal.add(new MenuOpcoes(layout, painelPrincipal), "Opções");
        painelPrincipal.add(new Parte1(layout, painelPrincipal), "Jogo");

        // painel admin
        painelPrincipal.add(new MenuAdmin(layout, painelPrincipal), "Admin");

        
        painelPrincipal.add(new TremPortaAberta(layout, painelPrincipal), "PortaAberta");
        painelPrincipal.add(new TremPesOlhando(layout, painelPrincipal), "PesOlhando");
        painelPrincipal.add(new TremEmergencia(layout, painelPrincipal), "TremEmergencia");
        painelPrincipal.add(new Trem1(layout, painelPrincipal), "Trem1");
        painelPrincipal.add(new TremPortaAberta(layout, painelPrincipal), "TremPortaAberta");
        painelPrincipal.add(new JogoItens(layout, painelPrincipal), "JogoItens");
        add(painelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}