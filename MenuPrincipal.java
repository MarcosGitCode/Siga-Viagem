import java.awt.*;
import javax.swing.*;

public class MenuPrincipal extends JPanel {
    public MenuPrincipal(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);
        setOpaque(false);

        int buttonWidth = 300;
        int buttonHeight = 60;
        int spacing = 20;

        JButton btnJogar = criarBotao("Jogar");
        JButton btnOpcoes = criarBotao("Opções");
        JButton btnPlacar = criarBotao("Placar");
        JButton btnSair = criarBotao("Sair");

        btnJogar.setBounds(490, 200, buttonWidth, buttonHeight);
        btnOpcoes.setBounds(490, 280, buttonWidth, buttonHeight);
        btnPlacar.setBounds(490, 360, buttonWidth, buttonHeight);
        btnSair.setBounds(490, 440, buttonWidth, buttonHeight);

        btnSair.addActionListener(e -> System.exit(0));
        btnOpcoes.addActionListener(e -> layout.show(painelPrincipal, "Opções"));

        add(btnJogar);
        add(btnOpcoes);
        add(btnPlacar);
        add(btnSair);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(128, 128, 128, 128));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(new RoundedBorder(20));
        return botao;
    }
}