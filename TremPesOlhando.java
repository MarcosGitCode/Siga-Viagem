import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPesOlhando extends JPanel {

    private Image imagemFundo;

    public TremPesOlhando(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Carrega a imagem de fundo uma vez
        try {
            BufferedImage img = ImageIO.read(new File("imagens/Fotos editadas/pes_olhando.jpg"));
            imagemFundo = img.getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            imagemFundo = null;
        }

        // Botão metade superior (volta para TremPortaAberta)
        JButton botaoSuperior = new JButton();
        botaoSuperior.setBounds(0, 0, 1280, 628);
        botaoSuperior.setOpaque(false);
        botaoSuperior.setContentAreaFilled(false);
        botaoSuperior.setBorderPainted(false);
        botaoSuperior.addActionListener(e -> layout.show(painelPrincipal, "PortaAberta"));
        add(botaoSuperior);

        // Botão metade inferior (ação opcional)
        JButton botaoInferior = new JButton();
        botaoInferior.setBounds(500, 650, 300, 300);
        botaoInferior.setOpaque(false);
        botaoInferior.setContentAreaFilled(false);
        botaoInferior.setBorderPainted(false);
        // Se quiser trocar a imagem, implemente aqui
        add(botaoInferior);

        // Botão Voltar
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
            layout.show(painelPrincipal, "TremPortaAberta"); // Volta para o painel anterior
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
