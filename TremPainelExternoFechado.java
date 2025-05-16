import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPainelExternoFechado extends JPanel {

    private JLabel labelImagem;

    public TremPainelExternoFechado(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Botão para voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do botão
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "TremPortaAberta"); // Substitua "TelaAnterior" pelo nome do painel anterior
        });
        add(botaoVoltar);

        // Botão no centro da tela
        JButton botaoPainelExterno = new JButton();
        botaoPainelExterno.setBounds(410, 300, 400, 300); // Mantém a posição e o tamanho do botão
        botaoPainelExterno.setOpaque(false); // Remove o fundo opaco
        botaoPainelExterno.setContentAreaFilled(false); // Remove a área de preenchimento
        botaoPainelExterno.setBorderPainted(false); // Remove as bordas visíveis
        botaoPainelExterno.addActionListener(e -> {
            System.out.println("Botão do painel externo clicado!");
            layout.show(painelPrincipal, "TremPainelExternoAberto"); // Troca para a tela TremPainelExternoAberto
        });
        add(botaoPainelExterno);

        // Carrega a imagem de fundo
        ImageIcon imagem = carregarImagem("imagens/Fotos editadas/17 - Painel externo fechado.jpg"); // Substitua pelo caminho correto da imagem
        labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856); // Ajuste o tamanho conforme necessário
        add(labelImagem);
    }

    private ImageIcon carregarImagem(String caminhoImagem) {
        try {
            BufferedImage imagemOriginal = ImageIO.read(new File(caminhoImagem));
            BufferedImage imagemRedimensionada = new BufferedImage(1280, 856, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imagemRedimensionada.createGraphics();
            g2d.drawImage(imagemOriginal, 0, 0, 1280, 856, null);
            g2d.dispose();
            return new ImageIcon(imagemRedimensionada);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}