import java.awt.*;
import javax.swing.*;

public class JogoDireita extends BasePainelComBotao {
    private String imagemAtual = "imagens/Fotos editadas/direita3.png";

    public JogoDireita(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/direita3.png", layout, painelPrincipal);
        setLayout(null);

        // Botão seta direita
        JButton botaoSetaDireita = new JButton("");
        botaoSetaDireita.setBounds(900, 300, 290, 300);
        botaoSetaDireita.setContentAreaFilled(false);
        botaoSetaDireita.setBorderPainted(false);
        botaoSetaDireita.setFocusPainted(false);
        botaoSetaDireita.setOpaque(false);
        botaoSetaDireita.setForeground(Color.WHITE);
        botaoSetaDireita.setFont(new Font("Arial", Font.BOLD, 20));
        botaoSetaDireita.addActionListener(e -> {
            System.out.println("Botão seta direita clicado!");
            layout.show(painelPrincipal, "JogoEsquerda");
        });
        add(botaoSetaDireita);

        // Botão invisível para coletar a chave
        JButton botaoVisivel = new JButton();
        botaoVisivel.setBounds(546, 390, 100, 100);
        botaoVisivel.setContentAreaFilled(false);
        botaoVisivel.setOpaque(false);
        botaoVisivel.setBorderPainted(false);
        botaoVisivel.setFocusPainted(false);
        botaoVisivel.setText("");
        botaoVisivel.addActionListener(e -> {
            System.out.println("Botão visível clicado!");
            imagemAtual = "imagens/Fotos editadas/direita2.png";
            Inventario.adicionar("Chave"); // Adiciona a chave ao inventário
            repaint();
            botaoVisivel.setVisible(false); // Esconde o botão após coletar
        });
        add(botaoVisivel);

        // Botão Voltar para Parte1
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

        // Botão para JogoDireitaChaveCBTC
        JButton chaveCBTC = new JButton();
        chaveCBTC.setBounds(550, 650, 75, 75);
        chaveCBTC.setContentAreaFilled(false);
        chaveCBTC.setOpaque(false);
        chaveCBTC.setBorderPainted(false);
        chaveCBTC.setFocusPainted(false);
        chaveCBTC.setText("");
        chaveCBTC.addActionListener(e -> {
            System.out.println("Novo botão clicado!");
            layout.show(painelPrincipal, "JogoDireitaChaveCBTC");
        });
        add(chaveCBTC);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a imagem de fundo atual
        Image imagemFundo = new ImageIcon(imagemAtual).getImage();
        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);

        // NÃO desenhe mais a chave na tela aqui!

        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
