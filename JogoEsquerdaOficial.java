import java.awt.*;
import javax.swing.*;

public class JogoEsquerdaOficial extends BasePainelComBotao {
    private String mensagemTemporaria = "";

    public JogoEsquerdaOficial(CardLayout layout, JPanel painelPrincipal) {
        super("imagens\\Fotos editadas\\12 - Porta de cabine lateral esquerda.png", layout, painelPrincipal);
        setLayout(null);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 856, 800);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false);
        add(painelBotoes);

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
            layout.show(painelPrincipal, "Jogo");
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

        JButton botaoEsquerda = new JButton();
        botaoEsquerda.setBounds(0, 150, 500, 300);
        botaoEsquerda.setOpaque(false);
        botaoEsquerda.setContentAreaFilled(false);
        botaoEsquerda.setBorderPainted(false);
        botaoEsquerda.addActionListener(e -> {
            layout.show(painelPrincipal, "JogoItens");
        });
        add(botaoEsquerda);

        JButton botaoPorta = new JButton();
        botaoPorta.setBounds(550, 150, 800, 800);
        botaoPorta.setOpaque(false);
        botaoPorta.setContentAreaFilled(false);
        botaoPorta.setBorderPainted(false);
        botaoPorta.addActionListener(e -> {
            if (EstadoJogo.chaveColetada == false) {
                mensagemTemporaria = "Você saiu da cabine sem a chave!";
                repaint();
                new javax.swing.Timer(3000, ev -> {
                    layout.show(painelPrincipal, "TelaFinal");
                }) {{
                    setRepeats(false);
                    start();
                }};
            } else {
                layout.show(painelPrincipal, "Trem1");
            }
        });
        add(botaoPorta);
    }

    private void criarBotaoVisivel(JPanel painel, int x, int y, int width, int height, Color color, java.awt.event.ActionListener action) {
        JButton botao = new JButton();
        botao.setBounds(x, y, width, height);
        botao.setOpaque(true);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.addActionListener(action);
        painel.add(botao);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        InventarioUI.desenhar((Graphics2D) g, getWidth());

        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;
            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }
            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}