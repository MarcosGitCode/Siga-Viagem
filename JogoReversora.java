import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class JogoReversora extends BasePainelComBotao {

    private boolean imagemAlternada = false; // Controle para alternar entre as imagens
    private BufferedImage imagemNeutro; // Imagem inicial
    private BufferedImage imagemFrente; // Imagem alternada
    private BufferedImage imagemAtual; // Imagem atualmente exibida

    public JogoReversora(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/Reversora em neutro.jpg", layout, painelPrincipal);

        try {
            // Carrega as imagens apenas uma vez
            imagemNeutro = ImageIO.read(new File("imagens/Fotos editadas/Reversora em neutro.jpg"));
            imagemFrente = ImageIO.read(new File("imagens/Fotos editadas/08 - Reversora em frente.jpg"));
            imagemAtual = imagemNeutro; // Define a imagem inicial
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adiciona um MouseListener para alternar a imagem ao clicar
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Alterna o estado da imagem
                imagemAlternada = !imagemAlternada;

                // Atualiza a imagem com base no estado
                if (imagemAlternada) {
                    imagemAtual = imagemFrente;
                } else {
                    imagemAtual = imagemNeutro;
                }
                repaint(); // Re-renderiza o painel para exibir a nova imagem
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
