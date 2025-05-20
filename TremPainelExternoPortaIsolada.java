import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPainelExternoPortaIsolada extends JPanel {

    public TremPainelExternoPortaIsolada(CardLayout layout, JPanel painelPrincipal) {
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
            layout.show(painelPrincipal, "TremPainelExternoFechado"); // Substitua "TelaAnterior" pelo nome do painel anterior
        });
        add(botaoVoltar);
        
        // Carrega a imagem de fundo
        JLabel labelImagemFundo = new JLabel();
        labelImagemFundo.setBounds(0, 0, 1280, 856); // Define o tamanho da imagem
        try {
            BufferedImage imagemFundo = ImageIO.read(new File("imagens/Fotos editadas/19 - Painel externo porta 3 isolada.jpg"));
            ImageIcon imagemIcon = new ImageIcon(imagemFundo.getScaledInstance(1280, 856, Image.SCALE_SMOOTH));
            labelImagemFundo.setIcon(imagemIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(labelImagemFundo);
    }
}