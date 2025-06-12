import java.awt.*;
import javax.swing.*;

public class Trem1 extends BasePainelComBotao {

    public Trem1(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/13 - Visão geral do carro 5.jpg", layout, painelPrincipal);
        setLayout(null);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 1280, 856);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false);
        add(painelBotoes);

        criarBotaoVisivel(painelBotoes, 1000, 200, 150, 300, Color.GREEN, e -> {
            System.out.println("Botão TremPortaAberta clicado!");
            layout.show(painelPrincipal, "TremPortaAberta");
        });

        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "JogoEsquerdaOficial");
        });
        add(botaoVoltar);

        JButton botaoMenu = new JButton("Menu");
        botaoMenu.setBounds(580, 10, 100, 60);
        botaoMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoMenu.setForeground(Color.white);
        botaoMenu.setBackground(Color.BLACK);
        botaoMenu.setContentAreaFilled(true);
        botaoMenu.setOpaque(true);
        botaoMenu.setBorderPainted(false);
        botaoMenu.setLayout(null);
        botaoMenu.addActionListener(e -> {
            System.out.println("Botão Menu clicado!");
            layout.show(painelPrincipal, "Menu");
        });
        add(botaoMenu);
    }

    private void criarBotaoVisivel(JPanel painel, int x, int y, int width, int height, Color color, java.awt.event.ActionListener action) {
        JButton botao = new JButton();
        botao.setBounds(x, y, width, height);
        botao.setOpaque(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.addActionListener(action);
        painel.add(botao);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
