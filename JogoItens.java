import java.awt.*;
import javax.swing.*;

public class JogoItens extends JPanel {

    private Image imagemFundo;

    public JogoItens(CardLayout layout, JPanel painelPrincipal) {
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/itens2.png").getImage();
        setLayout(null); // Define layout absoluto, se necessário

        // Botão de voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.WHITE); // Define a cor do texto
        botaoVoltar.setBackground(Color.DARK_GRAY); // Define a cor de fundo
        botaoVoltar.setFocusPainted(false); // Remove o destaque ao focar
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "JogoDireita"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

        // Botão quadrado 1
        JButton botaoQuadrado1 = new JButton("Botão 1");
        botaoQuadrado1.setBounds(100, 250, 200, 400); // Define a posição e o tamanho do botão
        botaoQuadrado1.setFont(new Font("Arial", Font.BOLD, 20)); // Define a fonte do texto
        botaoQuadrado1.setBackground(Color.BLUE); // Define a cor de fundo
        botaoQuadrado1.setForeground(Color.WHITE); // Define a cor do texto
        botaoQuadrado1.setFocusPainted(false); // Remove o destaque ao focar
        botaoQuadrado1.addActionListener(e -> {
            System.out.println("Botão 1 clicado!");
        });
        add(botaoQuadrado1); // Adiciona o botão ao painel

        // Botão quadrado 2
        JButton botaoQuadrado2 = new JButton("Botão 2");
        botaoQuadrado2.setBounds(380, 300, 800, 300); // Define a posição e o tamanho do botão
        botaoQuadrado2.setFont(new Font("Arial", Font.BOLD, 20)); // Define a fonte do texto
        botaoQuadrado2.setBackground(Color.RED); // Define a cor de fundo
        botaoQuadrado2.setForeground(Color.WHITE); // Define a cor do texto
        botaoQuadrado2.setFocusPainted(false); // Remove o destaque ao focar
        botaoQuadrado2.addActionListener(e -> {
            System.out.println("Botão 2 clicado!");
        });
        add(botaoQuadrado2); // Adiciona o botão ao painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
