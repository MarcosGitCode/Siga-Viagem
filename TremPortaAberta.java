import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPortaAberta extends JPanel {

    private Image imagemFundo;

    public TremPortaAberta(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Carrega a imagem de fundo uma vez
        try {
            BufferedImage img = ImageIO.read(new File("imagens/Fotos editadas/14 - Porta aberta - externo (2).jpg"));
            imagemFundo = img.getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            imagemFundo = null;
        }

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
            layout.show(painelPrincipal, "Trem1");
        });
        add(botaoVoltar);

        // Botão metade inferior (troca para TremPesOlhando)
        JButton botaoInferior = new JButton();
        botaoInferior.setBounds(500, 650, 300, 300);
        botaoInferior.setOpaque(false);
        botaoInferior.setContentAreaFilled(false);
        botaoInferior.setBorderPainted(false);
        botaoInferior.addActionListener(e -> layout.show(painelPrincipal, "PesOlhando"));
        add(botaoInferior);

        // Botão "Emergência" invisível no canto superior esquerdo
        JButton botaoEmergencia = new JButton("Emergência");
        botaoEmergencia.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoEmergencia.setForeground(Color.WHITE);
        botaoEmergencia.setBackground(new Color(220, 53, 69));
        botaoEmergencia.setFocusPainted(false);
        botaoEmergencia.setBounds(300, 400, 180, 200);
        botaoEmergencia.setVisible(false);
        botaoEmergencia.addActionListener(e -> {
            System.out.println("Cliquei em Emergência!");
            layout.show(painelPrincipal, "TremEmergencia");
        });
        add(botaoEmergencia);

        // Botão representando a seta apontando para cima
        JButton botaoSetaCima = new JButton("↑");
        botaoSetaCima.setBounds(610, 575, 80, 50);
        botaoSetaCima.addActionListener(e -> layout.show(painelPrincipal, "TremEmergencia"));
        botaoSetaCima.setOpaque(false);
        botaoSetaCima.setContentAreaFilled(false);
        botaoSetaCima.setBorderPainted(false);
        add(botaoSetaCima);

        // Botão da seta da direita
        JButton botaoSetaDireita = new JButton();
        botaoSetaDireita.setBounds(1150, 680, 80, 40);
        botaoSetaDireita.setOpaque(false);
        botaoSetaDireita.setContentAreaFilled(false);
        botaoSetaDireita.setBorderPainted(false);
        botaoSetaDireita.addActionListener(e -> {
            System.out.println("Botão seta direita clicado!");
            layout.show(painelPrincipal, "TremPainelExternoFechado");
        });
        add(botaoSetaDireita);
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
