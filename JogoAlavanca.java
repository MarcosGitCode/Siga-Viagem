import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class JogoAlavanca extends BasePainelComBotao {

    private boolean imagemAlternada = false; // Controle para alternar entre as imagens
    private BufferedImage imagem1; // Imagem inicial
    private BufferedImage imagem2; // Imagem alternada
    private BufferedImage imagemAtual; // Imagem atualmente exibida

    public JogoAlavanca(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/alavanca de comando 3.jpg", layout, painelPrincipal);

        try {
            // Carrega as imagens apenas uma vez
            imagem1 = ImageIO.read(new File("imagens/Fotos editadas/alavanca de comando 3.jpg"));
            imagem2 = ImageIO.read(new File("imagens/Fotos editadas/alavanca de comando 2.jpg"));
            imagemAtual = imagem1; // Define a imagem inicial
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
                    imagemAtual = imagem2;
                } else {
                    imagemAtual = imagem1;
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
