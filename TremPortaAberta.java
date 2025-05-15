import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPortaAberta extends JPanel {

    private JLabel labelImagem;

    public TremPortaAberta(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Carrega a imagem inicial
        ImageIcon imagem = carregarImagem("imagens/Fotos editadas/14 - Porta aberta - externo (2).jpg");

        // Adiciona a imagem inicial ao painel
        labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856);
        add(labelImagem);

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
        botaoEmergencia.setBackground(new Color(220, 53, 69)); // vermelho suave
        botaoEmergencia.setFocusPainted(false);
        botaoEmergencia.setBounds(300, 400, 180, 200);
        // Torna o botão invisível:
        botaoEmergencia.setVisible(false);
        botaoEmergencia.addActionListener(e -> {
            System.out.println("Cliquei em Emergência!"); // Para depuração
            layout.show(painelPrincipal, "TremEmergencia");
        });
        add(botaoEmergencia);

        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setBackground(Color.RED);
         botaoVoltar.setFocusPainted(false);
        botaoVoltar.setBorderPainted(true);
        botaoVoltar.addActionListener(e -> layout.show(painelPrincipal, "Trem1"));
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
