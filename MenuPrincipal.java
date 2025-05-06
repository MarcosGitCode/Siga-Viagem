import java.awt.*;
import javax.swing.*;

public class MenuPrincipal extends PainelComImagem {
    public MenuPrincipal(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/metro.jpg");
        setLayout(null);

        JButton btnJogar = criarBotao("Jogar");
        JButton btnOpcoes = criarBotao("Opções");
        JButton btnPlacar = criarBotao("Placar");
        JButton btnSair = criarBotao("Sair");

        btnJogar.setBounds(490, 200, 300, 60);
        btnOpcoes.setBounds(490, 280, 300, 60);
        btnPlacar.setBounds(490, 360, 300, 60);
        btnSair.setBounds(490, 440, 300, 60);

        btnJogar.addActionListener(e -> {
            System.out.println("Botão Jogar clicado!");
            layout.show(painelPrincipal, "Login");
        });

        btnOpcoes.addActionListener(e -> {
            System.out.println("Botão Opções clicado!");
            layout.show(painelPrincipal, "Opções");
        });

        btnSair.addActionListener(e -> {
            System.out.println("Botão Sair clicado!");
            System.exit(0);
        });

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