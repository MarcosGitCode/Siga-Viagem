import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPesOlhando extends JPanel {

    private JLabel labelImagem;

    public TremPesOlhando(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Carrega a imagem inicial
        ImageIcon imagem = carregarImagem("imagens/Fotos editadas/pes_olhando.jpg");

        // Adiciona a imagem inicial ao painel
        labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856);
        add(labelImagem);

        // Botão metade superior (volta para TremPortaAberta)
        JButton botaoSuperior = new JButton();
        botaoSuperior.setBounds(0, 0, 1280, 628);
        botaoSuperior.setOpaque(false);
        botaoSuperior.setContentAreaFilled(false);
        botaoSuperior.setBorderPainted(false);
        botaoSuperior.addActionListener(e -> layout.show(painelPrincipal, "PortaAberta"));
        add(botaoSuperior);

        // Botão metade inferior (recarrega a imagem atual, opcional)
        JButton botaoInferior = new JButton();
        botaoInferior.setBounds(500, 650, 300, 300);
        botaoInferior.setOpaque(false);
        botaoInferior.setContentAreaFilled(false);
        botaoInferior.setBorderPainted(false);
        botaoInferior.addActionListener(e -> trocarImagem("imagens/Fotos editadas/pes_olhando.jpg"));
        add(botaoInferior);

        // Botão Voltar
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(20, 20, 100, 50);
        botaoVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(botaoVoltar);
    }

    private void trocarImagem(String caminhoImagem) {
        ImageIcon novaImagem = carregarImagem(caminhoImagem);
        labelImagem.setIcon(novaImagem);
        revalidate();
        repaint();
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
