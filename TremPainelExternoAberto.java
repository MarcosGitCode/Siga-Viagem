import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

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
            layout.show(painelPrincipal, "TremPainelExternoFechado"); // Volta para a tela anterior
        });
        add(botaoVoltar);

        JButton botaoCentral = new JButton();
        int tamanho = 100; // Define o tamanho do botão como um quadrado (100x100)
        botaoCentral.setBounds(590 - (tamanho / 2), 560 - (tamanho / 2), tamanho, tamanho); // Centraliza o botão
        botaoCentral.setContentAreaFilled(false); // Remove o preenchimento do botão
        botaoCentral.setBorderPainted(false); // Remove a borda do botão
        botaoCentral.setFocusPainted(false); // Remove o destaque ao focar no botão
        botaoCentral.setOpaque(false); // Torna o botão completamente invisível
        botaoCentral.addActionListener(e -> {
            System.out.println("Botão central clicado!");
            layout.show(painelPrincipal, "TremPainelExternoPortaIsolada"); // Navega para a tela TremPainelExternoPortaIsolada
        });
        add(botaoCentral);

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
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Desenha o inventário no canto superior direito
    InventarioUI.desenhar((Graphics2D) g, getWidth());
}
}