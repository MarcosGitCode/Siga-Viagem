import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPainelExterno extends JPanel {

    private Image imagemFundo;

    public TremPainelExterno(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

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
            layout.show(painelPrincipal, "TremPortaAberta");
        });
        add(botaoVoltar);

        // Botão no centro da tela
        JButton botaoPainelExterno = new JButton();
        botaoPainelExterno.setBounds(410, 300, 400, 300);
        botaoPainelExterno.setOpaque(false);
        botaoPainelExterno.setContentAreaFilled(false);
        botaoPainelExterno.setBorderPainted(false);
        botaoPainelExterno.addActionListener(e -> {
            System.out.println("Botão do painel externo clicado!");
            
            // Atualiza a imagem de fundo
            try {
                imagemFundo = ImageIO.read(new File("imagens/Fotos editadas/18 - Painel externo aberto.jpg"));
                repaint(); // Redesenha o painel para refletir a nova imagem
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            // Remove o botão da tela
            remove(botaoPainelExterno);
            revalidate(); // Atualiza o layout
            repaint();    // Redesenha o painel

            // Adiciona o botão central
            JButton botaoCentral = new JButton();
            int tamanho = 100;
            botaoCentral.setBounds(590 - (tamanho / 2), 560 - (tamanho / 2), tamanho, tamanho);
            botaoCentral.setContentAreaFilled(false);
            botaoCentral.setBorderPainted(false);
            botaoCentral.setFocusPainted(false);
            botaoCentral.setOpaque(false);
            botaoCentral.addActionListener(event -> {
                System.out.println("Botão central clicado!");

                // Atualiza a imagem de fundo
                try {
                    imagemFundo = ImageIO.read(new File("imagens/Fotos editadas/19 - Painel externo porta 3 isolada.jpg"));
                    repaint(); // Redesenha o painel para refletir a nova imagem
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Remove o botão da tela
                remove(botaoCentral);
                revalidate(); // Atualiza o layout
                repaint();    // Redesenha o painel
            });
            add(botaoCentral);
            revalidate(); // Atualiza o layout novamente
            repaint();    // Redesenha o painel
        });
        add(botaoPainelExterno);

        // Carrega a imagem de fundo
        try {
            BufferedImage imagemOriginal = ImageIO.read(new File("imagens/Fotos editadas/17 - Painel externo fechado.jpg"));
            imagemFundo = imagemOriginal.getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            imagemFundo = null;
        }
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