import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TremPainelExternoAberto extends JPanel {

    public TremPainelExternoAberto(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Carrega a imagem de fundo
        ImageIcon imagemFundo = carregarImagem("imagens/Fotos editadas/18 - Painel externo aberto.jpg"); // Substitua pelo caminho correto da imagem
        JLabel labelImagemFundo = new JLabel(imagemFundo);
        labelImagemFundo.setBounds(0, 0, 1280, 856); // Ajuste o tamanho conforme necessário
        add(labelImagemFundo);

        // Botão para voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "TremPainelExterno"); // Volta para a tela anterior
        });
        add(botaoVoltar);

        // Adiciona um rótulo para identificar a nova tela
        JLabel label = new JLabel("Bem-vindo à Nova Tela!");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(500, 400, 300, 50); // Centraliza o texto
        add(label);

        // Garante que a imagem de fundo fique no fundo
        setComponentZOrder(labelImagemFundo, getComponentCount() - 1);
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