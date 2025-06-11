import java.awt.*;
import javax.swing.*;

public class JogoEsquerdaOficial extends BasePainelComBotao {
        private String mensagemTemporaria = "";
    public JogoEsquerdaOficial(CardLayout layout, JPanel painelPrincipal) {
        super("imagens\\Fotos editadas\\12 - Porta de cabine lateral esquerda.png", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário
        
        // Remova/adicione painéis apenas no Menu.java para evitar loops!

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 856, 800);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false); // Torna o painel transparente
        add(painelBotoes);

        

        // Botão Voltar para Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // Remove o fundo visível
        botaoVoltar.setOpaque(true); // Garante que o botão seja transparente
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

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
        
        // Botão transparente de 500x500px na metade esquerda da tela
        JButton botaoEsquerda = new JButton();
        botaoEsquerda.setBounds(0, 150, 500, 300); // Posição: do topo (150px) até 500px de altura
        botaoEsquerda.setOpaque(false);
        botaoEsquerda.setContentAreaFilled(false);
        botaoEsquerda.setBorderPainted(false);
        botaoEsquerda.addActionListener(e -> {
            layout.show(painelPrincipal, "JogoItens");
        });
        add(botaoEsquerda);

        JButton botaoPorta = new JButton();
        botaoPorta.setBounds(550, 150, 800, 800); // Posição: do topo (150px) até 500px de altura
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
        botao.setOpaque(true); // Torna o botão transparente
        botao.setContentAreaFilled(false); // Remove a área de fundo
        botao.setBorderPainted(false); // Remove as bordas do botão
        botao.addActionListener(action);
        painel.add(botao);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());

        // Desenha a mensagem temporária, se houver
        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;
            // Borda preta
            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }
            // Texto branco
            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}