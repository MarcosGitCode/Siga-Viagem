import java.awt.*;
import javax.swing.*;

public class JogoEsquerda extends BasePainelComBotao {

    public JogoEsquerda(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/06 - Botoeiras e sinaleira lateral.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto para posicionar componentes, se necessário

        // Remova/adicione painéis apenas no Menu.java para evitar loops!

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 856, 800);
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false); // Torna o painel transparente
        add(painelBotoes);

        criarBotaoVisivel(painelBotoes, 0, 200, 500, 500, Color.GREEN, e -> {
            System.out.println("Botão Trem1 clicado!");
            layout.show(painelPrincipal, "Trem1"); // Alterna para o painel Trem1
        });

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
            layout.show(painelPrincipal, "JogoDireita"); // Volta para o painel anterior
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
    }

    
    private void criarBotaoVisivel(JPanel painel, int x, int y, int width, int height, Color color, java.awt.event.ActionListener action) {
        JButton botao = new JButton();
        botao.setBounds(x, y, width, height);
        botao.setOpaque(false); // Torna o botão transparente
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
    }
}