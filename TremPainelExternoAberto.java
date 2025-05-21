import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPainelExternoAberto extends JPanel {

    private Image imagemFundo;

    public TremPainelExternoAberto(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Carrega a imagem de fundo
        try {
            BufferedImage imagemOriginal = ImageIO.read(new File("imagens/Fotos editadas/18 - Painel externo aberto.jpg"));
            imagemFundo = imagemOriginal.getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            imagemFundo = null;
        }

        // Botão para voltar
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
            layout.show(painelPrincipal, "TremPainelExternoFechado");
        });
        add(botaoVoltar);

        // Botão central invisível
        JButton botaoCentral = new JButton();
        int tamanho = 100;
        botaoCentral.setBounds(590 - (tamanho / 2), 560 - (tamanho / 2), tamanho, tamanho);
        botaoCentral.setContentAreaFilled(false);
        botaoCentral.setBorderPainted(false);
        botaoCentral.setFocusPainted(false);
        botaoCentral.setOpaque(false);
        botaoCentral.addActionListener(e -> {
            System.out.println("Botão central clicado!");
            layout.show(painelPrincipal, "TremPainelExternoPortaIsolada");
        });
        add(botaoCentral);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, 1280, 856, this);
        }
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}