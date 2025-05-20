import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

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
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
