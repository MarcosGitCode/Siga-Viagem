import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

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

         // Botão Voltar para Parte1
       JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // Remove o fundo visível
        botaoVoltar.setOpaque(true); // Garante que o botão seja transparente
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

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
